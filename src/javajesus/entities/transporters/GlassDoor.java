package javajesus.entities.transporters;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * A glass transporter
 */
public class GlassDoor extends Transporter {

	// colorset
	private static final int[] color = { 0xFF111111, 0xFF00BAFF, 0xFFFFDE00 };
	
	// dimensions of the transporter
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates a Transporter to change the level
	 * 
	 * @param currentLevel - the level it is on
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param outside - the new level
	 */
	public GlassDoor(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, WIDTH, HEIGHT, nextLevel);
	}

	/**
	 * Updates the transporter on the screen
	 */
	public void render(Screen screen) {
		screen.render16bit(getX(), getY(), 0, 5, SpriteSheet.tiles, color);
	}

}
