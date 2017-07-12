package javajesus.entities.solid.trees;

import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Small Sequoia Tree
 */
public class SmallSequoia extends Tree {

	// serialization
	private static final long serialVersionUID = 2784752755620913724L;

	/**
	 * Small Sequoia ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public SmallSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.SMALL_SEQUOIA, 5, 8, 4);

	}

}
