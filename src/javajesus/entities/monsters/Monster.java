package javajesus.entities.monsters;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javajesus.ChatHandler;
import javajesus.Game;
import javajesus.Game.GameMode;
import javajesus.entities.LongRange;
import javajesus.entities.Mob;
import javajesus.entities.Player;
import javajesus.entities.Skills;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.particles.pickups.ArrowPickup;
import javajesus.entities.particles.pickups.AssaultRifleAmmo;
import javajesus.entities.particles.pickups.LaserAmmoPickup;
import javajesus.entities.particles.pickups.QuickHealthPickup;
import javajesus.entities.particles.pickups.RevolverAmmoPickup;
import javajesus.entities.particles.pickups.ShotgunAmmoPickup;
import javajesus.graphics.SpriteSheet;
import level.Level;
import utility.Direction;

/*
 * A Monster is a mob that attacks NPCs and the player
 */
public class Monster extends Mob implements Skills {

	private static final long serialVersionUID = 4156279188503056448L;

	// the target of this mob
	protected Mob target;

	// Range that the monster can target another
	private Ellipse2D.Double aggroRadius;

	// the global attack range radius, 32 (number of units) * 8 (units) = 256
	protected static final int RADIUS = 256;

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

	// makes npcs move every other tick
	private int moveTick;

	/**
	 * Creates a Monster that attacks other mobs
	 * 
	 * @param level
	 *            the level the monster is on
	 * @param name
	 *            the name of the monster
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param speed
	 *            the base speed of the monster
	 * @param width
	 *            the width of the monster
	 * @param height
	 *            the height of the monster
	 * @param yTile
	 *            the row on the spritesheet
	 * @param health
	 *            the base health of the monster
	 * @param attackDelay
	 *            the amount of ticks between attacks
	 */
	public Monster(Level level, String name, int x, int y, int speed, int width, int height, int yTile, int health,
			int attackDelay) {
		super(level, name, x, y, speed, width, height, SpriteSheet.enemies, health);
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2, RADIUS, RADIUS);
		this.yTile = yTile;
		this.attackDelay = attackDelay;

		createHealthBar();

		// find a target to attack
		checkRadius();
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
			if (attackTickCount > attackDelay) {
				attackTickCount = 0;
				cooldown = false;
			}
		}

		// attack the target if given a chance
		if (!cooldown && target != null && 
				(getOuterBounds().intersects(target.getOuterBounds()) || 
						(this instanceof LongRange && 
								(((LongRange) this).getRange().intersects(target.getOuterBounds()))))) {
			cooldown = true;
			checkDirection();
			this.attack(getStrength(), getStrength() * 2, target);
		}

		// change in x and y
		int dx = 0, dy = 0;

		// whether or not the monster should move
		boolean shouldMove = false;
		
		// check the bounds if the monster prefers long range or not
		if (target != null) {
			if (this instanceof LongRange) {
				shouldMove = !((LongRange) this).getRange().intersects(target.getOuterBounds());
			} else {
				shouldMove = !getOuterBounds().intersects(target.getOuterBounds());
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

		// move the monster towards the target
		if ((dx != 0 || dy != 0) && !isMobCollision(dx, dy)) {
			move(dx, dy);
		}
	}

	/**
	 * Text to player
	 */
	public void speak(Player player) {
		isTalking = true;
		switch (random.nextInt(6)) {
		case 0: {
			ChatHandler.displayText("Rawr!", Color.white);
			return;
		}
		case 1: {
			ChatHandler.displayText("Arghhh", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText("OOOHH", Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText("ROAAR", Color.white);
			return;
		}
		case 4: {
			ChatHandler.displayText("HHRRRRSSHSHH", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText("RRRRRRRRRHHHH", Color.white);
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
	 * Monsters can drop stuff on death
	 */
	public void remove() {
		super.remove();
		
		if (Game.mode == GameMode.SURVIVAL) {
			survivalDrops();
		}
		
	}
	
	/**
	 * Unique drops to survival mode
	 */
	private void survivalDrops() {
		
		// every kill makes the game harder
		getLevel().add(new Spawner(getLevel(), getX(), getY(), this.getName()));
		Game.score++;
		
		// drop monster specific loot
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
			getLevel().add(new ArrowPickup(getLevel(), getX() + Game.getRandomOffset(8), getY() + Game.getRandomOffset(8)));
			break;
		case 1:
			getLevel().add(new AssaultRifleAmmo(getLevel(), getX() + Game.getRandomOffset(8), getY() + Game.getRandomOffset(8)));
			break;
		case 2:
			getLevel().add(new LaserAmmoPickup(getLevel(), getX() + Game.getRandomOffset(8), getY() + Game.getRandomOffset(8)));
			break;
		case 3:
			getLevel().add(new RevolverAmmoPickup(getLevel(), getX() + Game.getRandomOffset(8), getY() + Game.getRandomOffset(8), 6));
			break;
		case 4:
			getLevel().add(new ShotgunAmmoPickup(getLevel(), getX() + Game.getRandomOffset(8), getY() + Game.getRandomOffset(8)));
			break;
		case 5:
			getLevel().add(new QuickHealthPickup(getLevel(), getX() + Game.getRandomOffset(8), getY() + Game.getRandomOffset(8)));
			break;

		// drop nothing
		default:
			break;
		}
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 0;
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
