package game.entities.structures.transporters;

import java.awt.Point;

import game.graphics.Screen;
import game.graphics.SpriteSheet;
import level.Level;

/*
 * A glass transporter
 */
public class TransporterGlass extends Transporter {

	private static final long serialVersionUID = -3300095979447928983L;

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

		screen.render(getX() + 0, getY() + 0, 0 + 5 * 32, color, SpriteSheet.tiles);
		screen.render(getX() + 8, getY() + 0, 1 + 5 * 32, color, SpriteSheet.tiles);
		screen.render(getX() + 0, getY() + 8, 0 + 6 * 32, color, SpriteSheet.tiles);
		screen.render(getX() + 8, getY() + 8, 1 + 6 * 32, color, SpriteSheet.tiles);
	}

}
