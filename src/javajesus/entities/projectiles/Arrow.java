package javajesus.entities.projectiles;

import javajesus.SoundHandler;
import javajesus.entities.Mob;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * An arrow shot by natives
 */
public class Arrow extends Projectile {

	// the colorset for all arrows
	private static final int[] color = { 0xFF000001, 0xFF000001, 0xFFFFFF00 };
	
	/**
	 * Creates an arrow with a single direction
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
	public Arrow(Level level, int x, int y, Direction direction, Mob mob, int damage) {
		super(level, x, y, 8, 5, 5, 8, 2, 0, 3, direction, mob, damage, color, SoundHandler.laser);

	}

	/**
	 * Creates an arrow with complex direction
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
	public Arrow(Level level, int x, int y, int xPos, int yPos, Mob mob, int damage) {
		super(level, x, y, 8, 5, 5, 8, 2, 0, 3, xPos, yPos, mob, damage, color, SoundHandler.laser);

	}

}
