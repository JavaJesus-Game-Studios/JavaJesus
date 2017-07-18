package javajesus.entities.npcs.aggressive;

import java.awt.Color;

import javajesus.MessageHandler;
import javajesus.SoundHandler;
import javajesus.entities.Damageable;
import javajesus.entities.projectiles.Bullet;
import javajesus.level.Level;

public class SWATOfficer extends Shooter {

	private static final long serialVersionUID = -2320584920776635420L;

	// dimensions the SWAT officer
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates a Swat Officer
	 * 
	 * @param level
	 *            the level to place it on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param defaultHealth
	 *            the default health
	 * @param color
	 *            the colorset
	 * @param xTile
	 *            the xtile on the spritesheet
	 * @param yTile
	 *            the y tile on the spritesheet
	 * @param player
	 *            the player to follow
	 */
	public SWATOfficer(Level level, int x, int y, int defaultHealth, String walkPath, int walkDistance) {
		super(level, "Swat Officer", x, y, 1, WIDTH, HEIGHT, defaultHealth,
				new int[] { 0xFF111111, 0xFF000046, 0xFFEDC5AB }, 0, 10, walkPath, walkDistance);
	}

	/**
	 * Creates a default SWAT officer
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public SWATOfficer(Level level, int x, int y) {
		this(level, x, y, 250, "", 0);
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
				SoundHandler.assaultRifle));
	}

	/**
	 * Dialogue options for swat officers
	 */
	public void doDialogue() {
		MessageHandler.displayText(getName() + ": Stop right there, Criminal Scum!", Color.red);
		return;
	}

	@Override
	public int getStrength() {
		return 7;
	}
}
