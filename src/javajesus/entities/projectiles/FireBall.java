package javajesus.entities.projectiles;

import javajesus.SoundHandler;
import javajesus.entities.Mob;
import level.Level;
import utility.Direction;

/*
 * A fireball projectile usually made by demons
 */
public class FireBall extends Projectile {

	private static final long serialVersionUID = -4145206809321456052L;

	// the colorset for all fireballs
	private static final int[] color = { 0xFFFFFF00, 0xFFF7790A, 0xFF880000 };

	// pixel size of a fireball
	private static final int SIZE = 6;

	/**
	 * Creates a fireball
	 * @param level the level it is on
	 * @param x the x coord
	 * @param y the y coord
	 * @param xPos the x coord to move  to
	 * @param yPos the y coord to move to
	 * @param mob the mob that is firing
	 * @param damage the damage of this fireball
	 */
	public FireBall(Level level, int x, int y, int xPos, int yPos, Mob mob, int damage) {
		super(level, x, y, SIZE, SIZE, 0, 2, xPos, yPos, mob, damage, SoundHandler.fireball);
	}

	/**
	 * Creates a fireball with a simple direction
	 * @param level the level it is on
	 * @param x the x coord
	 * @param y the y coord
	 * @param direction the direction the fireball should move
	 * @param mob the mob that is firing
	 * @param damage the damage of this fireball
	 */
	public FireBall(Level level, int x, int y, Direction direction, Mob mob, int damage) {
		super(level, x, y, SIZE, SIZE, 0, 2, direction, mob, damage, SoundHandler.fireball);
	}

	@Override
	protected int[] getColor() {
		return color;
	}

}
