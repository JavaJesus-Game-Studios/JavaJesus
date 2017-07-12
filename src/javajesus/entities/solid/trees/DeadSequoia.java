package javajesus.entities.solid.trees;

import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * A Dead Sequoia Tree
 */
public class DeadSequoia extends Tree {

	// serialization
	private static final long serialVersionUID = -7727072271070247898L;

	/**
	 * DeadSequoia ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public DeadSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.DEAD_SEQUOIA, 7, 9, 5);

	}

}
