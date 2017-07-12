package javajesus.entities.solid.trees;

import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Large Sequoia Tree
 */
public class LargeSequoia extends Tree {

	// serialization
	private static final long serialVersionUID = -6542198444757793732L;

	/**
	 * LargeSequoia ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public LargeSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.LARGE_SEQUOIA, 7, 11, 5);

	}

}
