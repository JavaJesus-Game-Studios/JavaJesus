package javajesus.entities.transporters;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

public class Door extends Transporter {
	
	// dimensions of the door
	private static final int WIDTH = 16, HEIGHT = 16;
	
	// color set of this door
	private static final int[] color = { 0xFF111111, 0xFF704200, 0xFFFFDE00 };

	/**
	 * Creates a door transporter
	 * 
	 * @param currentLevel - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param nextLevel - the level it leads to
	 */
	public Door(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, WIDTH, HEIGHT, nextLevel);
	}

	/**
	 * Renders the door on the screen
	 */
	@Override
	public void render(Screen screen) {
		screen.render16bit(getX(), getY(), 0, 5, SpriteSheet.tiles, color);
	}

}
