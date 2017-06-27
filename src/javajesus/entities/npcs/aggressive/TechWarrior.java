package javajesus.entities.npcs.aggressive;

import javajesus.ChatHandler;
import javajesus.SoundHandler;
import javajesus.entities.Mob;
import javajesus.entities.projectiles.Bullet;
import javajesus.level.Level;

import java.awt.Color;

public class TechWarrior extends Shooter {

	private static final long serialVersionUID = 2154670871278842088L;

	// dimensions the tech warrior
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates a tech warrior
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
	public TechWarrior(Level level, int x, int y, int defaultHealth, String walkPath, int walkDistance) {
		super(level, "Tech Warrior", x, y, 1, WIDTH, HEIGHT, defaultHealth,
				new int[] { 0xFF111111, 0xFF42FF00, 0xFFEDC5AB }, 0, 12, walkPath, walkDistance);
	}

	/**
	 * Creates a default tech warrior
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public TechWarrior(Level level, int x, int y) {
		this(level, x, y, 220, "", 0);
	}

	/**
	 * Shoots a bullet at a target Uses dummy parameters to conform to Mob class
	 */
	@Override
	public void attack(int fake, int fake2, Mob other) {

		getLevel().add(new Bullet(getLevel(), getX(), getY(), target.getX(), target.getY(), this, getStrength(),
				SoundHandler.shotgun));
	}

	/**
	 * Dialogue options for Tech Warrior
	 */
	public void doDialogue() {
		ChatHandler.displayText(getName() + ": Have you tried the latest IPear?", Color.white);
		return;
	}

	@Override
	public int getStrength() {
		return 6;
	}

}
