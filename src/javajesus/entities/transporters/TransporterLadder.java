package javajesus.entities.transporters;

import java.awt.Point;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * A ladder for in caves
 */
public class TransporterLadder extends Transporter {

	private static final long serialVersionUID = 2030814150855374528L;

	// colorset
	private static final int[] color = { 0xFF663300, 0xFF663300, 0xFFFFDE00 };

	/**
	 * Creates a Transporter to change the level
	 * @param currentLevel the level it is on
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param nextLevel the new level
	 */
	public TransporterLadder(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, nextLevel);
	}

	/**
	 * Creates a Transporter to change the level but also
	 * updates that new level's spawnpoint
	 * @param currentLevel the level it is on
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param nextLevel the new level
	 * @param point the spawnpoint for the new level
	 */
	public TransporterLadder(Level currentLevel, int x, int y, Level nextLevel, Point point) {
		super(currentLevel, x, y, nextLevel, point);
	}

	/**
	 * Displays the ladder to the screen
	 */
	public void render(Screen screen) {

		screen.render(getX() + 0, getY() - 8, 6 + 5 * 32, color, SpriteSheet.tiles);
		screen.render(getX() + 0, getY() + 0, 6 + 6 * 32, color, SpriteSheet.tiles);
	}
}