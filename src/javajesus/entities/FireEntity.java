package javajesus.entities;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.level.tile.Tile;

/*
 * A pseudo animated tile that damages the player over time
 */
public class FireEntity extends Entity  {

	// last iteration time in milliseconds
	private static long lastIterationTime;
	
	// the delay between animations
	private static final int delay = 100;
	
	// horizontal x position on sprite sheet
	public static int xTile;
	
	// vertical y position on sprite sheet
	public static final int yTile = 15;
	
	// colors of the flames
	private static final int[] color = { 0xFFF7790A, 0xFFF72808, 0xFF000000 };
	
	// the number of animated tiles on spritesheet
	private static final int NUM_TILES = 4;
	
	/**
	 * Creates a fire entity that damages the player
	 * 
	 * @param - level the current level
	 * @param x - the x coord on the map
	 * @param y - the y coord on the map
	 * 
	 * The x and y coordiantes will be rounded to the upper
	 * left corner up a tile to snap into place
	 */
	public FireEntity(Level level, int x, int y) {
		super(level, Tile.snapToCorner(x), Tile.snapToCorner(y));
		lastIterationTime = System.currentTimeMillis();
		
		// set the bounds
		setBounds(getX(), getY(), Tile.SIZE, Tile.SIZE);
		
	}

	/**
	 * update fire logic
	 */
	public void tick() {
		
		update();
		
		// check for collisions
		for (int i = 0; i < getLevel().getMobs().size(); i++) {
			
			// collision if on the same tile
			if (getLevel().getMobs().get(i).getBounds().intersects(this.getBounds())) {
				getLevel().getMobs().get(i).ignite();
			}
			
		}
	}
	
	/**
	 * Updates the fire animation
	 */
	public static final void update() {
		// update the animation
		if ((System.currentTimeMillis() - lastIterationTime) >= (delay)) {
			lastIterationTime = System.currentTimeMillis();
			xTile = ++xTile % NUM_TILES;
		}
	}

	/**
	 * Displays the pixels on the screen
	 */
	public void render(Screen screen) {
		
		screen.render(getX(), getY(), xTile, yTile, SpriteSheet.tiles, false, color);
	}

	@Override
	public byte getId() {
		return Entity.FIRE_ENTITY;
	}

}
