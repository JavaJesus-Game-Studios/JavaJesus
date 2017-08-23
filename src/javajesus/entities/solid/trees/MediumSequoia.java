package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Medium sized sequoia tree
 */
public class MediumSequoia extends Tree {

	/**
	 * Medium Sequoia ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public MediumSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.MEDIUM_SEQUOIA, 6, 8, 9);

	}
	
	@Override
    public byte getId(){
        return Entity.MEDIUM_SEQUOIA;
    }
}
