package game.entities.monsters;

import game.ChatHandler;
import game.entities.Mob;
import game.entities.Player;
import game.entities.Skills;
import game.entities.npcs.NPC;
import game.graphics.SpriteSheet;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import level.Level;

/*
 * A Monster is a mob that attacks NPCs and the player
 */
public class Monster extends Mob implements Skills {

	private static final long serialVersionUID = 4156279188503056448L;

	// the target of this mob
	protected Mob target;

	// Range that the monster can target another
	private Ellipse2D.Double aggroRadius;

	// the attack range radius, 32 (number of units) * 8 (units) = 256
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
				if ((mob instanceof Player || mob instanceof NPC) && aggroRadius.intersects(mob.getBounds())) {
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
		if (!cooldown && target != null && getOuterBounds().intersects(target.getOuterBounds())) {
			cooldown = true;
			this.attack(getStrength(), getStrength() * 2, target);
		}

		// change in x and y
		int dx = 0, dy = 0;

		if (target != null && !getOuterBounds().intersects(target.getOuterBounds())) {

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
	 * Moves a monster on the level
	 * 
	 * @param dx
	 *            the total change in x
	 * @param dy
	 *            the total change in y
	 */
	protected void move(int dx, int dy) {
		super.move(dx, dy);

		aggroRadius.setFrame(getX() - RADIUS / 2, getY() - RADIUS / 2, RADIUS, RADIUS);
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
