package javajesus.level.tile;

import java.util.ArrayList;

import javajesus.graphics.SpriteSheet;

/*
 * A Tile that changes its appearance every tick
 */
public class AnimatedTile extends Tile {
	
	// list of all animated tiles
	public static final ArrayList<AnimatedTile> tileList = new ArrayList<AnimatedTile>();
	
	// last time changed
	private long lastIterationTime;

	// delay between switching animations
	private int animationSwitchDelay;

	// number of tiles in the sequence
	private int length;

	/**
	 * Creates a Tile that changes its appearance every tick
	 * 
	 * @param id - unique id of this tile
	 * @param solid - whether or not entities can clip
	 * @param pixelColor - pixel color in png file
	 * @param yTile - the y coordinate on the sheet
	 * @param sheet - spritesheet to use
	 * @param color - color set of the tile
	 * @param numTiles - the number of subsequent tiles in the spritesheet
	 * @param animationSwitchDelay - delay between texture updates
	 */
	public AnimatedTile(int id, boolean solid, int pixelColor, int yTile, SpriteSheet sheet, int[] color, int numTiles,
	        int animationSwitchDelay) {
		super(id, solid, pixelColor, 0, yTile, sheet, color);

		// instance data
		this.lastIterationTime = System.currentTimeMillis();
		this.animationSwitchDelay = animationSwitchDelay;
		this.length = numTiles;
		
		// set to tile list
		tileList.add(this);
	}

	/**
	 * Handles the animation
	 */
	public void tick() {
		
		// update the xTile
		if ((System.currentTimeMillis() - lastIterationTime) >= (animationSwitchDelay)) {
			lastIterationTime = System.currentTimeMillis();
			xTile = (xTile + 1) % length;
		}
	}
}