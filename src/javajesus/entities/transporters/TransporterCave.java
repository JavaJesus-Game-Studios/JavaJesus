package javajesus.entities.transporters;

import java.awt.Point;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.level.RandomCave;

/*
 * Transporter to a cave
 */
public class TransporterCave extends Transporter {

	private static final long serialVersionUID = 3445699890339737978L;

	// colorset for the cave
	private static final int[] color = { 0xFF663300, 0xFF472400, 0xFFFFDE00 };

	/**
	 * Creates a transporter that leads to a Random Cave
	 * 
	 * @param currentLevel
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public TransporterCave(Level currentLevel, int x, int y) {
		super(currentLevel, x, y, new RandomCave(3000, 3000, 5, currentLevel, new Point(x + 1, y + 10)));
	}

	/**
	 * Creates a transporter that leads to a a Random Cave at a specified point
	 * 
	 * @param currentLevel
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param point
	 *            the spawnpoint of the RandomCave
	 */
	public TransporterCave(Level currentLevel, int x, int y, Point point) {
		super(currentLevel, x, y, new RandomCave(3000, 3000, 5, currentLevel, new Point(x + 1, y + 10)), point);
	}

	/**
	 * Creates a transporter that leads to specified level
	 * 
	 * @param currentLevel
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public TransporterCave(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, nextLevel);
	}

	/**
	 * Displays the cave transporter on the screen
	 */
	public void render(Screen screen) {

		screen.render(getX() + 0, getY() + 0, 4 + 5 * 32, color, SpriteSheet.tiles);
		screen.render(getX() + 0, getY() + 8, 4 + 6 * 32, color, SpriteSheet.tiles);
		screen.render(getX() + 8, getY() + 0, 5 + 5 * 32, color, SpriteSheet.tiles);
		screen.render(getX() + 8, getY() + 8, 5 + 6 * 32, color, SpriteSheet.tiles);
	}
}