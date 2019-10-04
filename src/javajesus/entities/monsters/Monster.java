package javajesus.entities.monsters;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javajesus.JavaJesus;
import javajesus.MessageHandler;
import javajesus.dataIO.EntityData;
import javajesus.entities.LongRange;
import javajesus.entities.Mob;
import javajesus.entities.Pickup;
import javajesus.entities.Player;
import javajesus.entities.npcs.NPC;
import javajesus.entities.strategies.MonsterCollisionStrategy;
import javajesus.graphics.SpriteSheet;
import javajesus.items.Item;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A Monster is a mob that attacks NPCs and the player
 */
public abstract class Monster extends Mob {

	// Range that the monster can target another
	private final Ellipse2D.Double aggroRadius;

	// the global attack range radius
	protected static final int RADIUS = 32;

	// range of damage
	private static final int DAMAGE_RANGE = 5;

	// cooldown from attacks
	protected boolean cooldown = true;

	// internal timer for attack cooldown
	private int attackTickCount;

	// the row on the monster spreadsheet
	protected int yTile;

	// the amount of ticks between attacks
	private int attackDelay;

	// how long the attack position is rendered in ticks
	private static final int attackAnimationLength = 20;

	// whether or not the attack animation should update
	protected boolean flipAttack;

	// makes npcs move every other tick
	private int moveTick;

	// whether or not the monster is moving
	protected boolean isMoving;
	
	// 
	protected static int[] color;
	

	/**
	 * Creates a Monster that attacks other mobs
	 * 
	 * @param level - the level the monster is on
	 * @param name - the name of the monster
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param speed - the base speed of the monster
	 * @param width - the width of the monster
	 * @param height - the height of the monster
	 * @param yTile - the row on the spritesheet
	 * @param health - the base health of the monster
	 * @param attackDelay - the amount of ticks between attacks
	 */
	public Monster(Level level, String name, int x, int y, int speed, int width, int height, int yTile, int health,
	        int attackDelay) {
		super(level, name, x, y, speed, width, height, SpriteSheet.mobEnemies,
		        Math.round(health * JavaJesus.difficulty));
		this.aggroRadius = new Ellipse2D.Double(x - (RADIUS * 8) / 2, y - (RADIUS * 8) / 2, RADIUS * 8, RADIUS * 8);
		this.yTile = yTile;
		this.attackDelay = attackDelay;
		this.collisionStrategy = new MonsterCollisionStrategy(this);
		this.setColor(color);
		
		// initialize a few things
		if (level != null) {
			createHealthBar();
		}

	}

	/**
	 * Updates the targeted mob
	 */
	protected void checkRadius() {

		// if the target is dead or out of range, reset the target
		if (target != null && (target.isDead() || !(aggroRadius.intersects(target.getBounds())))) {
			target.setTargeted(false);
			target = null;
			clearPath();
		}

		// assign a new target
		if (target == null) {

			// grow from small radius to large radius
			for (int i = 1; i < RADIUS; i++) {
				
				// update the radius
				Ellipse2D.Double perimeter = new Ellipse2D.Double(getX() - (i * 8) / 2, getY() - (i * 8) / 2, i * 8, i * 8);

				// last mob in case no targetable mob
				Mob last = null;
				for (Mob mob : getLevel().getMobs()) {
					if ((mob instanceof Player || mob instanceof NPC) && perimeter.intersects(mob.getBounds())
					        && !mob.isDead()) {

						// target the mob if it is not being targeted already
						if (!mob.isTargeted()) {
							target = mob;
							mob.setTargeted(true);
							return;

							// mob already being targetted
						} else {
							last = mob;
						}
					}
				}

				// at this point, no target has been selected
				if (last != null) {
					target = last;
					return;
				}
			}
		}

	}

	/**
	 * Internal update clock
	 */
	public void tick() {
		super.tick();
		checkRadius();

		// attacking cooldown loop
		if (cooldown) {
			attackTickCount++;
			isShooting = attackTickCount < attackAnimationLength;
			flipAttack = attackTickCount > 10 && isShooting;
			if (attackTickCount > attackDelay) {
				attackTickCount = 0;
				cooldown = false;
			}
		}

		// attack the target if given a chance
		if (!cooldown && target != null && (getOuterBounds().intersects(target.getBounds())
		        || (this instanceof LongRange && (((LongRange) this).getRange().intersects(target.getBounds()))))) {
			cooldown = true;
			checkDirection();
			this.attack(DAMAGE_RANGE, target);
		}

		// change in x and y
		int dx = 0, dy = 0;

		// check the bounds if the monster prefers long range or not
		if (target != null) {
			
			// simple path finding for long range shooters
			if (this instanceof LongRange) {
				if ( !((LongRange) this).getRange().intersects(target.getBounds())) {

					// move towards the target horizontally
					if (target.getX() > getX()) {
						dx++;
						dy = 0;
					} else if (target.getX() < getX()) {
						dx--;
						dy = 0;
					}

					// move towards the target vertically
					if (target.getY() > getY()) {
						dy++;
						dx = 0;
					} else if (target.getY() < getY()) {
						dy--;
						dx = 0;
					}

				}

				// move the monster towards the target
				if ((dx != 0 || dy != 0) && !collisionStrategy.isMobCollision(dx, dy)) {
					isMoving = true;
					move(dx, dy);
				} else {
					isMoving = false;
				}
				
			// close combat has pathfinding
			} else {
				isMoving = !getBounds().intersects(target.getBounds());
				if (isMoving && (getPath() == null || !getPath().isNotEmpty())) {
					collisionImmune = false;
					setPath(target);
				} else if (!isMoving && getPath() != null){
					path.clear();
					if (!getBounds().intersects(target.getBounds())) {
						if (target == JavaJesus.getPlayer()) {
							collisionImmune = !JavaJesus.getPlayer().isMoving();
						} else {
							collisionImmune = true;
						}
					} else {
						collisionImmune = false;
					}
					
				}
			}
		}

	}

	/**
	 * Text to player
	 */
	public void speak(Player player) {
		isTalking = true;
		switch (random.nextInt(6)) {
		case 0: {
			MessageHandler.displayText("Rawr!", Color.white);
			return;
		}
		case 1: {
			MessageHandler.displayText("Arghhh", Color.white);
			return;
		}
		case 2: {
			MessageHandler.displayText("OOOHH", Color.white);
			return;
		}
		case 3: {
			MessageHandler.displayText("ROAAR", Color.white);
			return;
		}
		case 4: {
			MessageHandler.displayText("HHRRRRSSHSHH", Color.white);
			return;
		}
		default: {
			MessageHandler.displayText("RRRRRRRRRHHHH", Color.white);
			return;
		}
		}
	}

	/**
	 * Updates the direction the mob is shooting
	 */
	private void checkDirection() {

		// move towards the target horizontally
		if (target.getX() > getX()) {
			setDirection(Direction.EAST);
		} else if (target.getX() < getX()) {
			setDirection(Direction.WEST);
		}

		// move towards the target vertically
		if (target.getY() > getY()) {
			setDirection(Direction.SOUTH);
		} else if (target.getY() < getY()) {
			setDirection(Direction.NORTH);
		}
	}

	/**
	 * Moves a monster on the level
	 * 
	 * @param dx the total change in x
	 * @param dy the total change in y
	 */
	public void move(int dx, int dy) {

		aggroRadius.setFrame(getX() - (RADIUS * 8) / 2, getY() - (RADIUS * 8) / 2, RADIUS * 8, RADIUS * 8);

		if (++moveTick % 2 == 0) {
			super.move(dx * (int) getSpeed(), dy * (int) getSpeed());
			moveTick = 0;
		}
	}
	
	public void moveSmoothly(int dx, int dy){
		super.move(dx * (int) getSpeed(), dy * (int) getSpeed());
	}

	/**
	 * Monsters can drop stuff on death
	 */
	public void remove() {
		super.remove();

		// reset data
		flipAttack = false;

		// drop loot on death
		dropLoot();

	}

	/**
	 * Adds specific items to loot on death
	 */
	protected void dropLoot() {

		// randomly drop an item of any time
		int value = (new Random()).nextInt(7);
		switch (value) {
		case 0:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8),
			        getY() + JavaJesus.getRandomOffset(8), Item.arrowAmmo, false, 4));
			break;
		case 1:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8),
			        getY() + JavaJesus.getRandomOffset(8), Item.assaultRifleAmmo, false, 60));
			break;
		case 2:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8),
			        getY() + JavaJesus.getRandomOffset(8), Item.laserAmmo, false, 30));
			break;
		case 3:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8),
			        getY() + JavaJesus.getRandomOffset(8), Item.revolverAmmo, false, 24));
			break;
		case 4:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8),
			        getY() + JavaJesus.getRandomOffset(8), Item.revolverAmmo, false, 24));
			break;
		case 5:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8),
			        getY() + JavaJesus.getRandomOffset(8), Item.revolverAmmo, false, 24));
			break;
		case 6:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8),
			        getY() + JavaJesus.getRandomOffset(8), Item.shotgunAmmo, false, 8));
			break;
		case 7:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8),
			        getY() + JavaJesus.getRandomOffset(8), Item.quickHealthPack, true, 1));
			break;

		// drop nothing
		default:
			break;
		}
	}

	@Override
	public long getData() {
		return EntityData.type2(getX(), getY(), getMaxHealth());
	}
	
	protected void setColor(int[] color) {
		if( color == null )
			color = new int[] {0,0,0,0,0};
		if (color.length < 5)
			color = new int[] { color[0], color[1], color[2], 0, 0 };
		Monster.color = color;
	}
	

}
