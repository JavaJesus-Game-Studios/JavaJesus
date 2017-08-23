package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Large Sequoia Tree
 */
public class LargeSequoia extends Tree {

	/**
	 * LargeSequoia ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public LargeSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.LARGE_SEQUOIA, 8, 11, 12);

	}

	@Override
    public byte getId(){
        return Entity.LARGE_SEQUOIA;
    }
}
