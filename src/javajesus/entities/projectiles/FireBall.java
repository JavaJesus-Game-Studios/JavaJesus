package javajesus.entities.projectiles;

import javajesus.SoundHandler;
import javajesus.entities.Mob;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A fireball projectile usually made by demons
 */
public class FireBall extends Projectile {

	// the color set for all fireballs
	private static final int[] color = { 0xFFFFFF00, 0xFFF7790A, 0xFF880000 };

	/**
	 * Creates a fire ball with a single direction
	 * 
	 * @param level -  What level it renders on
	 * @param x - The X position it will spawn at
	 * @param y - The Y position it will spawn at
	 * @param xTile - the x tile on the spritesheet
	 * @param yTile - the FIRST y tile on the spritesheet
	 * @param direction -  The direction it will move; NORTH, SOUTH, EAST, or WEST
	 * @param mob - the mob that fired the projectile
	 * @param damage - the damage this projectile should do on impact
	 */
	public FireBall(Level level, int x, int y, Direction direction, Mob mob, int damage) {
		super(level, x, y, 6, 6, 6, 6, 0, 0, 3, direction, mob, damage, color, SoundHandler.fireball);
	}

	/**
	 * Creates a fire ball with complex direction
	 * 
	 * @param level -  What level it renders on
	 * @param x - The X position it will spawn at
	 * @param y - The Y position it will spawn at
	 * @param xTile - the x tile on the spritesheet
	 * @param yTile - the FIRST y tile on the spritesheet
	 * @param xPos - the x coordinate it will travel to
	 * @param yPos -  the y coordinate it will travel to
	 * @param mob - the mob that fired the projectile
	 * @param damage - the damage this projectile should do on impact
	 */
	public FireBall(Level level, int x, int y, int xPos, int yPos, Mob mob, int damage) {
		super(level, x, y, 6, 6, 6, 6, 0, 0, 3, xPos, yPos, mob, damage, color, SoundHandler.fireball);

	}

}
