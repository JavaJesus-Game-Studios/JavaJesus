package game.entities.npcs;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import game.ChatHandler;
import game.Game;
import game.entities.Mob;
import game.entities.Skills;
import game.entities.monsters.Monster;
import game.graphics.Screen;
import level.Level;
import utility.Direction;

public class Knight extends NPC implements Skills {

	private static final long serialVersionUID = -990363786904488287L;

	// dimensions of the knight
	private static final int WIDTH = 16, HEIGHT = 16;

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
	private static final int attackDelay = 40;

	// how long the attack position is rendered in ticks
	private static final int attackAnimationLength = 30;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	public Knight(Level level, int x, int y, int health, String walkPath, int walkDistance) {
		super(level, "Knight", x, y, 1, WIDTH, HEIGHT, health, new int[] { 0xFF111111, 0xFF7E7E7E, 0xFFFFFFFF }, 0, 2,
				walkPath, walkDistance);

		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2, RADIUS, RADIUS);

		checkRadius();
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
				if ((mob instanceof Monster) && aggroRadius.intersects(mob.getBounds()) && !mob.isDead()) {
					target = mob;
					mob.setTargeted(true);
					return;
				}
			}
		}

	}

	/**
	 * Updates the Knight
	 */
	public void tick() {
		
		checkRadius();
		
		if (target == null) {
			super.tick();
		} else {
			getHealthBar().tick();
		}
		
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
	 * Displays the Knight to the screen
	 */
	public void render(Screen screen) {

		// if not attacking, render normally
		if (!isShooting || isSwimming) {
			super.render(screen);
			return;
		}

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE * getScale();

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal position on the spritesheet
		int xTile = 14;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 14;
		} else if (getDirection() == Direction.SOUTH) {
			xTile += 12;
		} else {
			flip = getDirection() == Direction.WEST;
		}

		// attacking left right
		if (isLatitudinal()) {
			// iterate by rows
			for (int i = 0; i < 2; i++) {

				screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + (modifier * i),
						xTile + (yTile + i) * getSpriteSheet().boxes, getColor(), flip, getScale(), getSpriteSheet());

				screen.render(xOffset + modifier, yOffset + (modifier * i),
						(xTile + 1) + (yTile + i) * getSpriteSheet().boxes, getColor(), flip, getScale(),
						getSpriteSheet());

				screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + (modifier * i),
						(xTile + 2) + (yTile + i) * getSpriteSheet().boxes, getColor(), flip, getScale(),
						getSpriteSheet());
			}

			// attacking up down
		} else {

			// iterate by rows
			for (int i = 0; i < 3; i++) {

				// Left body 1
				screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier * i,
						xTile + (yTile + i) * getSpriteSheet().boxes, getColor(), flip, getScale(), getSpriteSheet());

				// Right body 2
				screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier * i,
						(xTile + 1) + (yTile + i) * getSpriteSheet().boxes, getColor(), flip, getScale(),
						getSpriteSheet());
			}

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
	 * Dialogue options for the Knight
	 */
	public void doDialogue() {
		ChatHandler.displayText(getName() + ": Hello " + Game.player.getName(), Color.white);
		return;
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 10;
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
