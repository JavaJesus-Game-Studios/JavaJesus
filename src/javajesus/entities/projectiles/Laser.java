package javajesus.entities.projectiles;

import javajesus.SoundHandler;
import javajesus.entities.Mob;
import level.Level;
import utility.Direction;

/*
 * high tech laser projectile
 */
public class Laser extends Projectile {

	private static final long serialVersionUID = -8622210634865875442L;

	// the colorset for all lasers
	private static final int[] color = { 0xFF000000, 0xFF000000, 0xFFD80202 };

	/**
	 * Creates a laser
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param xPos
	 *            the x coord to move to
	 * @param yPos
	 *            the y coord to move to
	 * @param mob
	 *            the mob that is firing
	 * @param damage
	 *            the damage of this laser
	 */
	public Laser(Level level, int x, int y, int xPos, int yPos, Mob mob, int damage) {
		super(level, x, y, 2, 1, 1, 6, xPos, yPos, mob, damage, SoundHandler.laser);
	}

	/**
	 * Creates a laser with a simple direction
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param direction
	 *            the direction the laser should move
	 * @param mob
	 *            the mob that is firing
	 * @param damage
	 *            the damage of this laser
	 */
	public Laser(Level level, int x, int y, Direction direction, Mob mob, int damage) {
		super(level, x, y, 2, 1, 1, 6, direction, mob, damage, SoundHandler.laser);
	}

	@Override
	protected int[] getColor() {
		return color;
	}

}