package game.entities.npcs.aggressive;

import game.ChatHandler;
import game.entities.Mob;
import game.entities.Skills;
import game.entities.monsters.Monster;
import game.entities.npcs.NPC;
import game.graphics.Screen;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import level.Level;
import utility.Direction;

public class Gorilla extends NPC implements Skills {

	private static final long serialVersionUID = 5350816667470294053L;

	// dimensions of the Gorilla
	private static final int WIDTH = 24, HEIGHT = 24;

	// the target of this mob
	private Mob target;

	// Range that the monster can target another
	private Ellipse2D.Double aggroRadius;

	// the attack range radius, 32 (number of units) * 8 (units) = 256
	private static final int RADIUS = 256;

	// cooldown from attacks
	private boolean cooldown = true;

	// internal timer for attack cooldown
	private int attackTickCount;

	// the amount of ticks between attacks
	private static final int attackDelay = 20;

	// how long the attack position is rendered in ticks
	private static final int attackAnimationLength = 20;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	/**
	 * Creates a generic gorilla with different abilities
	 * @param level the level it is on
	 * @param x the x coord
	 * @param y the y coord
	 * @param defaultHealth the base health
	 * @param walkPath the walk pattern
	 * @param walkDistance the walk distance
	 */
	public Gorilla(Level level, int x, int y, int defaultHealth, String walkPath, int walkDistance) {
		super(level, "Gorilla", x, y, 1, WIDTH, HEIGHT, defaultHealth, new int[] { 0xFF111111, 0xFF000046, 0xFFEDC5AB },
				0, 22, walkPath, walkDistance);

		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2, RADIUS, RADIUS);

		checkRadius();
	}

	/**
	 * Default gorilla
	 * @param level the level it is on
	 * @param x the x coord
	 * @param y the y coord
	 */
	public Gorilla(Level level, int x, int y) {
		this(level, x, y, 200, "", 0);
	}

	/**
	 * Updates the targeted mob
	 */
	private void checkRadius() {

		// if the target is dead or out of range, reset the target
		if (target != null && (target.isDead() || !(aggroRadius.intersects(target.getBounds())))) {
			target = null;
		}

		// assign a new target
		if (target == null) {
			for (Mob mob : getLevel().getMobs()) {
				if ((mob instanceof Monster) && aggroRadius.intersects(mob.getBounds())) {
					target = mob;
					mob.setTargeted(true);
					return;
				}
			}
		}

	}

	/**
	 * Updates the Gorilla
	 */
	public void tick() {
		super.tick();
		checkRadius();

		// attacking cooldown loop
		if (cooldown) {
			attackTickCount++;
			isShooting = attackTickCount < attackAnimationLength;
			if (attackTickCount > attackDelay) {
				attackTickCount = 0;
				cooldown = false;
			}
		}

		// attack the target if given a chance
		if (!cooldown && target != null && getOuterBounds().intersects(target.getOuterBounds())) {
			cooldown = true;
			this.attack(getStrength(), getStrength() * 2, target);
		}

		// change in x and y
		int dx = 0, dy = 0;

		// whether or not the monster should move
		// check the bounds if the monster prefers long range or not
		if (target != null && !(getOuterBounds().intersects(target.getOuterBounds()))) {

			// move towards the target horizontally
			if (target.getX() > getX()) {
				dx++;
			} else if (target.getX() < getX()) {
				dx--;
			}

			// move towards the target vertically
			if (target.getY() > getY()) {
				dy++;
			} else if (target.getY() < getY()) {
				dy--;
			}

		}

		// move the monster towards the target
		if ((dx != 0 || dy != 0) && !isMobCollision(dx, dy)) {
			move(dx, dy);
		}
	}

	/**
	 * Displays the gorilla on the screen
	 */
	public void render(Screen screen) {

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE * getScale();

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal position on the spritesheet
		int xTile = 0;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// get spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile += 15;
		} else if (getDirection() == Direction.SOUTH) {
			xTile += 3;
		} else {
			xTile += 6 + (flip ? 3 : 0);
			flip = getDirection() == Direction.WEST;
		}

		// attacking animation
		if (isShooting) {
			if (getDirection() == Direction.NORTH) {
				xTile = 27;
			} else if (getDirection() == Direction.SOUTH) {
				xTile = 21;
			} else {
				xTile = 24 + (flip ? 3 : 0);
				flip = getDirection() == Direction.WEST;
			}
		}

		// absolute x tile for dead sprite
		if (isDead())
			xTile = 18;

		// render by row
		for (int i = 0; i < 3; i++) {

			screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + (modifier * i),
					xTile + (yTile + i) * getSpriteSheet().boxes, getColor(), flip, getScale(), getSpriteSheet());

			screen.render(xOffset + modifier, yOffset + (modifier * i),
					(xTile + 1) + (yTile + i) * getSpriteSheet().boxes, getColor(), flip, getScale(), getSpriteSheet());

			screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + (modifier * i),
					(xTile + 2) + (yTile + i) * getSpriteSheet().boxes, getColor(), flip, getScale(), getSpriteSheet());
		}
	}

	/**
	 * Moves a monster on the level
	 * 
	 * @param dx
	 *            the total change in x
	 * @param dy
	 *            the total change in y
	 */
	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);

		aggroRadius.setFrame(getX() - RADIUS / 2, getY() - RADIUS / 2, RADIUS, RADIUS);
	}

	/**
	 * Speech options for gorilla
	 */
	public void doDialogue() {
		ChatHandler.displayText(getName() + ": Gorilla like human.", Color.white);
		return;
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 30;
	}

	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAccuracy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEvasion() {
		// TODO Auto-generated method stub
		return 0;
	}

}
