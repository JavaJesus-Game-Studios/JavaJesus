package javajesus.entities.npcs.aggressive;

import java.awt.geom.Ellipse2D;

import javajesus.entities.Entity;
import javajesus.entities.Mob;
import javajesus.entities.monsters.Monster;
import javajesus.entities.npcs.NPC;
import javajesus.graphics.Screen;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class Knight extends NPC {

	// dimensions of the knight
	private static final int WIDTH = 16, HEIGHT = 16;

	// the target of this mob
	private Mob target;

	// Range that the monster can target another
	private final Ellipse2D.Double aggroRadius;

	// the attack range radius, 32 (number of units) * 8 (units) = 256
	private static final int RADIUS = 20;
	
	// range for damage
	private static final int DAMAGE_RANGE = 5;

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

	/**
	 * Knight ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param health - health of the knight
	 * @param walkPath - the walking pattern
	 * @param walkDistance - the length of the walking pattern
	 */
	public Knight(Level level, int x, int y, int health, String walkPath, int walkDistance) {
		super(level, "Knight", x, y, 1, WIDTH, HEIGHT, health, new int[] { 0xFF111111, 0xFF888888, 0xFFe8e8e8, 0xFF111111,0xFF7E7E7E},
				0, 0, walkPath, walkDistance);
		
		// expand the outer range a little bit
		setOuterBoundsRange(4);
		
		// create the radius
		aggroRadius = new Ellipse2D.Double(x - (RADIUS * 8) / 2, y - (RADIUS * 8) / 2, RADIUS * 8, RADIUS * 8);

		// find a target
		if (level != null) {
			findTarget();
		}
	}
	
	/**
	 * Knight ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Knight(Level level, int x, int y) {
		this(level, x, y, 100, "stand", 0);
	}

	/**
	 * Updates the targeted mob
	 */
	protected void findTarget() {

		// if the target is dead or out of range, reset the target
		if (target != null && (target.isDead() || !(aggroRadius.intersects(target.getBounds())))) {
			target.setTargeted(false);
			target = null;
			clearPath();
		}

		// assign a new target
		if (target == null) {
			
			// closest targetted mob
			Mob closest = null;
			
			// grow from small radius to large radius
			for (int i = 1; i < RADIUS; i++) {
				
				// update the radius
				Ellipse2D.Double perimeter = new Ellipse2D.Double(getX() - (i * 8) / 2, getY() - (i * 8) / 2, i * 8, i * 8);
				
				for (Mob mob : getLevel().getMobs()) {
					if ((mob instanceof Monster) && perimeter.intersects(mob.getBounds()) && !mob.isDead()) {
						
						// target the mob if it is not being targeted already
						if (!mob.isTargeted()) {
							target = mob;
							mob.setTargeted(true);
							return;

							// mob already being targetted
						} else if (closest == null){
							closest = mob;
						}
					}
				}
			}
			
			// at this point, no target has been selected so default to closest
			if (closest != null) {
				target = closest;
				return;
			}
			
		}

	}

	/**
	 * Updates the Knight
	 */
	public void tick() {
		
		// make sure the knight has a valid target
		findTarget();
		
		// do basic npc logic
		if (target == null) {
			super.tick();
			return;
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
			this.attack(DAMAGE_RANGE, target);
		}
		
		// path find to the target
		if (!getOuterBounds().intersects(target.getBounds()) && (getPath() == null || !getPath().exists())) {
			setPath(target);
		}
		
		// automate movement with a script
		if (path != null && path.exists()) {

			// first update the path
			path.update();

			// now make sure it exists after updating
			if (path.exists()) {
				
				// change in x and y
				int dx = 0, dy = 0;

				if (path.next().getDestination().getX() > getX()) {
					dx++;
				} else if (path.next().getDestination().getX() < getX()) {
					dx--;
				}

				if (path.next().getDestination().getY() > getY()) {
					dy++;
				} else if (path.next().getDestination().getY() < getY()) {
					dy--;
				}
				if (dx != 0 || dy != 0) {
					move(dx, dy);
					isMoving = true;
					return;
				}
			}

		}
		
		// force the mob to move around the mob collision
		if (isCollidingWithMob()) {
			moveAroundMobCollision();
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
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal position on the spritesheet
		int xTile = 14;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 5;
		} else if (getDirection() == Direction.SOUTH) {
			xTile += 3;
		} else {
			flip = getDirection() == Direction.WEST;
		}

		// attacking left right
		if (isLatitudinal()) {
			// iterate by rows
			for (int i = 0; i < 2; i++) {

				screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + (modifier * i), xTile, yTile + i,
				        getSpriteSheet(), flip, getColor());

				screen.render(xOffset + modifier, yOffset + (modifier * i), xTile + 1, yTile + i, getSpriteSheet(),
				        flip, getColor());

				screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + (modifier * i), xTile + 2,
				        yTile + i, getSpriteSheet(), flip, getColor());
			}

			// attacking up down
		} else {

			// iterate by rows
			for (int i = 0; i < 3; i++) {

				// Left body 1
				screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier * i, xTile, yTile + i,
				        getSpriteSheet(), flip, getColor());

				// Right body 2
				screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier * i, xTile + 1,
				        yTile + i, getSpriteSheet(), flip, getColor());
			}

		}

	}

	/**
	 * Moves the knight on the level
	 * 
	 * @param dx - the total change in x
	 * @param dy - the total change in y
	 */
	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);

		aggroRadius.setFrame(getX() - RADIUS * 8 / 2, getY() - RADIUS * 8 / 2, RADIUS * 8, RADIUS * 8);
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
	public byte getId() {
		// TODO Auto-generated method stub
		return Entity.KNIGHT;
	}

}
