package javajesus.entities.transporters;

import java.awt.Point;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * A glass transporter
 */
public class TransporterGlass extends Transporter {

	// colorset
	private static final int[] color = { 0xFF111111, 0xFF00BAFF, 0xFFFFDE00 };

	/**
	 * Creates a Transporter to change the level
	 * 
	 * @param currentLevel
	 *            the level it is on
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param nextLevel
	 *            the new level
	 */
	public TransporterGlass(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, nextLevel);
	}

	/**
	 * Creates a Transporter to change the level but also updates that new
	 * level's spawnpoint
	 * 
	 * @param currentLevel
	 *            the level it is on
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param nextLevel
	 *            the new level
	 * @param point
	 *            the spawnpoint for the new level
	 */
	public TransporterGlass(Level currentLevel, int x, int y, Level nextLevel, Point point) {
		super(currentLevel, x, y, nextLevel, point);
	}

	/**
	 * Updates the transporter on the screen
	 */
	public void render(Screen screen) {
		screen.render16bit(getX(), getY(), 0, 5, SpriteSheet.tiles, color);
	}

}
