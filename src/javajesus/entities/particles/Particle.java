package javajesus.entities.particles;

import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * A Particle is an entity that cannot interact with mobs
 * A particle is purely aesthetic
 */
public class Particle extends Entity {

	// spritesheet the particle is on
	private final SpriteSheet sheet;

	// spritesheet coordinates
	private int xTile, yTile;

	// colorset for the particle
	private final int[] color;

	/**
	 * Creates a particle
	 * 
	 * @param level -  the level it is on
	 * @param x - the x coord it is on
	 * @param y - the y coord it is on
	 * @param xTile - the x tile on the spritesheet
	 * @param yTile - the y tile on the spritesheet
	 * @param color - the color set
	 */
	public Particle(final Level level, int x, int y, int xTile, int yTile, final int[] color, SpriteSheet sheet) {
		super(level, x, y);
		
		// instance data
		this.xTile = xTile;
		this.yTile = yTile;
		this.color = color;
		this.sheet = sheet;
	}

	/**
	 * No real update function
	 */
	public void tick() {
	}

	/**
	 * Displays the particle to the screen
	 */
	public void render(Screen screen) {
		screen.render(getX(), getY(), xTile, yTile, sheet, false, color);
	}

	/**
	 * Returns the name of the particle
	 */
	public String toString() {
		return "Particle";
	}

	/**
	 * Particles won't be saved
	 */
	@Override
	public byte getId() {
		return -1;
	}

	@Override
	public long getData() {
		return 0;
	}
	
	@Override
	public void onCollisionWithEntity(Entity e) {
		return;
		
	}

	@Override
	public void onRemovedCollisionWithEntity(Entity e) {
		return;
	}

}
