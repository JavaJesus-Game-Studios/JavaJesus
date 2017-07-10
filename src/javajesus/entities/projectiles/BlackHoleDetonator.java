package javajesus.entities.projectiles;

import javajesus.SoundHandler;
import javajesus.entities.Mob;
import javajesus.entities.effects.BlackHole;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * BlackHoleDetonators create black holes!
 */
public class BlackHoleDetonator extends Projectile {

	// serialization
	private static final long serialVersionUID = 2546446873996072596L;

	// the colorset for all detonators
	private static final int[] color = { 0xFF000001, 0xFF000000, 0xFF000000 };

	/**
	 * Creates a black hole detonator with a single direction
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
	public BlackHoleDetonator(Level level, int x, int y, Direction direction, Mob mob, int damage) {
		super(level, x, y, 5, 5, 5, 5, 5, 0, 3, direction, mob, damage, color, SoundHandler.explosion);
	}

	/**
	 * Creates a black hole detonator with complex direction
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
	public BlackHoleDetonator(Level level, int x, int y, int xPos, int yPos, Mob mob, int damage) {
		super(level, x, y, 5, 5, 5, 5, 5, 0, 3, xPos, yPos, mob, damage, color, SoundHandler.explosion);
	}

	/**
	 * Adds a black hole when destroyed
	 */
	@Override
	protected void destroy() {
		
		getLevel().add(new BlackHole(getLevel(), getX(), getY()));
		super.destroy();

	}

}
