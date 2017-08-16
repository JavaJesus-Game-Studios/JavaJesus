package javajesus.entities.bosses;

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
import javajesus.graphics.SpriteSheet;
import javajesus.items.Item;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A Boss is a mob that attacks NPCs and the player
 */
public abstract class Boss extends Mob {

	// the target of this mob
	protected Mob target;

	// Range that the Boss can target another
	private Ellipse2D.Double aggroRadius;

	// the global attack range radius, 32 (number of units) * 8 (units) = 256
	protected static final int RADIUS = 256;
	
	// range of damage
	private static final int DAMAGE_RANGE = 5;

	// cooldown from attacks
	protected boolean cooldown = true;

	// internal timer for attack cooldown
	private int attackTickCount;

	// the row on the Boss spreadsheet
	protected int yTile;

	// the amount of ticks between attacks
	private int attackDelay;

	// how long the attack position is rendered in ticks
	private static final int attackAnimationLength = 20;
	
	// whether or not the attack animation should update
	protected boolean flipAttack;

	// makes npcs move every other tick
	private int moveTick;
	
	// whether or not the Boss is moving
	protected boolean isMoving;

	/**
	 * Creates a Boss that attacks other mobs
	 * 
	 * @param level - the level the Boss is on
	 * @param name - the name of the Boss
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param speed - the base speed of the Boss
	 * @param width - the width of the Boss
	 * @param height - the height of the Boss
	 * @param yTile - the row on the spritesheet
	 * @param health - the base health of the Boss
	 * @param attackDelay - the amount of ticks between attacks
	 */
	public Boss(Level level, String name, int x, int y, int speed, int width, int height, int yTile, int health,
			int attackDelay) {
		super(level, name, x, y, speed, width, height, SpriteSheet.bosses, Math.round(health * JavaJesus.difficulty));
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2, RADIUS, RADIUS);
		this.yTile = yTile;
		this.attackDelay = attackDelay;

		// initialize a few things
		if (level != null) {
			createHealthBar();

			// find a target to attack
			checkRadius();
		}
		
	}

	/**
	 * Updates the targeted mob
	 */
	protected void checkRadius() {

		// if the target is dead or out of range, reset the target
		if (target != null && (target.isDead() || !(aggroRadius.intersects(target.getBounds())))) {
			target = null;
		}

		// assign a new target
		if (target == null) {
			for (Mob mob : getLevel().getMobs()) {
				if ((mob instanceof Player || mob instanceof NPC) && aggroRadius.intersects(mob.getBounds()) && !mob.isDead()) {
					target = mob;
					mob.setTargeted(true);
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
		if (!cooldown && target != null && (getOuterBounds().intersects(target.getBounds()) || (this instanceof LongRange
		        && (((LongRange) this).getRange().intersects(target.getBounds()))))) {
			cooldown = true;
			checkDirection();
			this.attack(DAMAGE_RANGE, target);
		}

		// change in x and y
		int dx = 0, dy = 0;

		// whether or not the Boss should move
		boolean shouldMove = false;
		
		// check the bounds if the Boss prefers long range or not
		if (target != null) {
			if (this instanceof LongRange) {
				shouldMove = !((LongRange) this).getRange().intersects(target.getBounds());
			} else {
				shouldMove = !getOuterBounds().intersects(target.getBounds());
			}
		}

		if (shouldMove) {

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

		// move the Boss towards the target
		if ((dx != 0 || dy != 0) && !isMobCollision(dx, dy)) {
			isMoving = true;
			move(dx, dy);
		} else {
			isMoving = false;
		}
	}

	/**
	 * Text to player
	 */
	public void speak(Player player) {
		isTalking = true;
		switch (random.nextInt(6)) {
		case 0: {
			MessageHandler.displayText("DIE HUMAN!", Color.white);
			return;
		}
		case 1: {
			MessageHandler.displayText("FACE MY WRATH", Color.white);
			return;
		}
		case 2: {
			MessageHandler.displayText("I AM A GOD", Color.white);
			return;
		}
		case 3: {
			MessageHandler.displayText("GGGGGAAAAAHHHHHHH", Color.white);
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
	 * Moves a Boss on the level
	 * 
	 * @param dx
	 *            the total change in x
	 * @param dy
	 *            the total change in y
	 */
	public void move(int dx, int dy) {

		aggroRadius.setFrame(getX() - RADIUS / 2, getY() - RADIUS / 2, RADIUS, RADIUS);

		if (moveTick++ % 2 == 0) {
			super.move(dx * (int) getSpeed(), dy * (int) getSpeed());
		}
	}
	
	/**
	 * Boss can drop stuff on death
	 */
	public void remove() {
		super.remove();
		
		// drop loot on death
		dropLoot();
		
	}
	
	/**
	 * Adds specific items to loot on death
	 */
	protected void dropLoot() {
		
		// randomly drop an item of any time
		int value = (new Random()).nextInt(10);
		switch (value) {
		case 0:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8), getY() + JavaJesus.getRandomOffset(8), Item.arrowAmmo, 3));
			break;
		case 1:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8), getY() + JavaJesus.getRandomOffset(8), Item.assaultRifleAmmo, 15));
			break;
		case 2:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8), getY() + JavaJesus.getRandomOffset(8), Item.laserAmmo, 6));
			break;
		case 3:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8), getY() + JavaJesus.getRandomOffset(8), Item.revolverAmmo, 6));
			break;
		case 4:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8), getY() + JavaJesus.getRandomOffset(8), Item.shotgunAmmo, 5));
			break;
		case 5:
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8), getY() + JavaJesus.getRandomOffset(8), Item.quickHealthPack, true));
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

}