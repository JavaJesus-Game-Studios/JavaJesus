package javajesus.entities.npcs.aggressive;

import java.awt.Color;

import javajesus.MessageHandler;
import javajesus.SoundHandler;
import javajesus.entities.Damageable;
import javajesus.entities.Entity;
import javajesus.entities.projectiles.Bullet;
import javajesus.level.Level;

public class TechWarrior extends Shooter {

	// dimensions the tech warrior
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates a tech warrior
	 * 
	 * @param level - the level to place it on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param defaultHealth - the default health
	 * @param color - the colorset
	 * @param xTile - the xtile on the spritesheet
	 * @param yTile - the y tile on the spritesheet
	 * @param player - the player to follow
	 */
	public TechWarrior(Level level, int x, int y, int defaultHealth, String walkPath, int walkDistance) {
		super(level, "Tech Warrior", x, y, 1, WIDTH, HEIGHT, defaultHealth,
<<<<<<< HEAD
				new int[] { 0xFF111111, 0xFF42FF00, 0xFFEDC5AB }, 0, 11, walkPath, walkDistance);
=======
				new int[] { 0xFF111111, 0xFF42FF00, 0xFFEDC5AB, 0, 0}, 0, 12, walkPath, walkDistance);
>>>>>>> origin/master
	}

	/**
	 * Creates a default tech warrior
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 */
	public TechWarrior(Level level, int x, int y) {
		this(level, x, y, 220, "", 0);
	}

	/**
	 * Deals damage to another mob
	 * Calculated by getStrength() +
	 * a random number in the range
	 * 
	 * @param range - random offset to add to strength
	 * @param other - the other mob to attack
	 */
	@Override
	public void attack(int range, Damageable other) {

		getLevel().add(new Bullet(getLevel(), getX(), getY(), target.getX(), target.getY(), this, getStrength() + random.nextInt(range),
				SoundHandler.shotgun));
	}

	/**
	 * Dialogue options for Tech Warrior
	 */
	public void doDialogue() {
		MessageHandler.displayText(getName() + ": We're making the world a better place.", Color.white);
		return;
	}

	@Override
	public int getStrength() {
		return 6;
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Entity.TECH_WARRIOR;
	}

}
