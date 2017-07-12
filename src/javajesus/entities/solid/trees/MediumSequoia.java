package javajesus.entities.solid.trees;

import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Medium sized sequoia tree
 */
public class MediumSequoia extends Tree {

	// serialization
	private static final long serialVersionUID = 4508786800750502286L;

	/**
	 * Medium Sequoia ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public MediumSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.MEDIUM_SEQUOIA, 5, 8, 6);

	}

}
