package javajesus.entities.structures.transporters;

import java.awt.Point;

import javajesus.graphics.Screen;
import level.Level;

/*
 * An invisible transporter that is usually used in interiors
 */
public class TransporterInterior extends Transporter {

	private static final long serialVersionUID = 2329949542471763366L;

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
	public TransporterInterior(Level currentLevel, int x, int y, Level nextLevel) {
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
	public TransporterInterior(Level currentLevel, int x, int y, Level nextLevel, Point point) {
		super(currentLevel, x, y, nextLevel, point);
	}

	/**
	 * Blank because there is nothing to show
	 */
	public void render(Screen screen) {
	}

}
