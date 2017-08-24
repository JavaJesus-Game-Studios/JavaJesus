package javajesus.entities.transporters;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

public class Door extends Transporter {
	
	// dimensions of the door
	private static final int WIDTH = 12, HEIGHT = 16;
	
	//Xtile of Door
	private int x;
	private int y;
	
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
	public Door(Level currentLevel, int x, int y, Level nextLevel, int xTile, int yTile) {
		super(currentLevel, x, y, WIDTH, HEIGHT, nextLevel);
		this.x = xTile;
		this.y = yTile;
	}

	/**
	 * Renders the door on the screen
	 */
	@Override
	public void render(Screen screen) {
		screen.render16bit(getX(), getY(), x, y, SpriteSheet.doors, color);
	}

}
