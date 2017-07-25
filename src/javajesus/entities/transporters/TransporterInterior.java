package javajesus.entities.transporters;

import javajesus.graphics.Screen;
import javajesus.level.Level;

/*
 * An invisible transporter that is usually used in interiors
 */
public class TransporterInterior extends Transporter {
	
	/**
	 * Creates a Transporter to change the level
	 * 
	 * @param currentLevel - the level it is on
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param outside - the new level
	 * 
	 * Default width and height is 16
	 */
	public TransporterInterior(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, 16, 16, nextLevel);
	}
	
	/**
	 * Creates a Transporter to change the level
	 * 
	 * @param currentLevel - the level it is on
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param outside - the new level
	 */
	public TransporterInterior(Level currentLevel, int x, int y, int width, int height, Level nextLevel) {
		super(currentLevel, x, y, width, height, nextLevel);
	}

	/**
	 * Blank because there is nothing to show
	 */
	public void render(Screen screen) {}

}
