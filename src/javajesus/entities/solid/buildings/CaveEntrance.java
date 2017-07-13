package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.transporters.TransporterCave;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * The cave structure front
 */
public class CaveEntrance extends Building {

	// serialization
	private static final long serialVersionUID = -6709208432522451198L;

	// color set
	private static final int[] color = { 0xFF301E01, 0xFF474645, 0xFF000000 };

	/**
	 * Creates a cave entrance
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public CaveEntrance(Level level, int x, int y) {
		super(level, x, y, color, Sprite.cave_entrance);

		level.add(new TransporterCave(level, x + 18, y + 20));
	}

	/**
	 * Creates a cave entrance
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @param spawn - the spawn point in the cave interior
	 */
	public CaveEntrance(Level level, int x, int y, Point spawn) {
		super(level, x, y, color, Sprite.cave_entrance);

		level.add(new TransporterCave(level, x + 18, y + 20, spawn));
	}

	/**
	 * Creates a cave entrance
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @param nextLevel - the level it leads to
	 */
	public CaveEntrance(Level level, int x, int y, Level nextLevel) {
		super(level, x, y, color, Sprite.cave_entrance);

		level.add(new TransporterCave(level, x + 18, y + 20, nextLevel));
	}

}