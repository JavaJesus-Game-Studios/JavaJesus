package javajesus.entities.transporters;

import java.awt.Point;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.level.RandomCave;

/*
 * Transporter to a cave
 */
public class CaveDoor extends Transporter {

	// colorset for the cave door
	private static final int[] color = { 0xFF663300, 0xFF472400, 0xFFFFDE00 };
	
	// dimensions of the door
	private static final int WIDTH = 12, HEIGHT = 16;

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
	public CaveDoor(Level currentLevel, int x, int y) {
		super(currentLevel, x, y, WIDTH, HEIGHT, new RandomCave(5, currentLevel, new Point(x + 1, y + 10)));
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
	public CaveDoor(Level currentLevel, int x, int y, Point point) {
		super(currentLevel, x, y, WIDTH, HEIGHT, new RandomCave(5, currentLevel, new Point(x + 1, y + 10)));
		
		//TODO something with point
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
	public CaveDoor(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, WIDTH, HEIGHT, nextLevel);
	}

	/**
	 * Displays the cave transporter on the screen
	 */
	public void render(Screen screen) {
		screen.render16bit(getX(), getY(), 4, 0, SpriteSheet.doors, color);
	}
}