package javajesus.entities.projectiles;

import javajesus.SoundHandler;
import javajesus.entities.Mob;
import javajesus.entities.effects.Explosion;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A missile explodes on impact
 */
public class Missile extends Projectile {

	// the color set for all missiles
	private static final int[] color = { 0xFFa1a1a1, 0xFF2b6e0f, 0xFFffcd03 };

	/**
	 * Creates a missile with a single direction
	 * 
	 * @param level - What level it renders on
	 * @param x - The X position it will spawn at
	 * @param y - The Y position it will spawn at
	 * @param xTile - the x tile on the spritesheet
	 * @param yTile - the FIRST y tile on the spritesheet
	 * @param direction - The direction it will move; NORTH, SOUTH, EAST, or WEST
	 * @param mob - the mob that fired the projectile
	 * @param damage - the damage this projectile should do on impact
	 */
	public Missile(Level level, int x, int y, Direction direction, Mob mob, int damage) {
		super(level, x, y, 8, 5, 5, 8, 3, 0, 3, direction, mob, damage, color, SoundHandler.fireball);
	}

	/**
	 * Creates a missile with complex direction
	 * 
	 * @param level - What level it renders on
	 * @param x - The X position it will spawn at
	 * @param y - The Y position it will spawn at
	 * @param xTile - the x tile on the spritesheet
	 * @param yTile - the FIRST y tile on the spritesheet
	 * @param xPos - the x coordinate it will travel to
	 * @param yPos - the y coordinate it will travel to
	 * @param mob - the mob that fired the projectile
	 * @param damage - the damage this projectile should do on impact
	 */
	public Missile(Level level, int x, int y, int xPos, int yPos, Mob mob, int damage) {
		super(level, x, y, 8, 5, 5, 8, 3, 0, 3, xPos, yPos, mob, damage, color, SoundHandler.fireball);
	}

	/**
	 * Adds an explosion when destroyed
	 */
	@Override
	protected void destroy() {

		getLevel().add(new Explosion(getLevel(), getX() + 4, getY() + 4));
		super.destroy();

	}
	
}
