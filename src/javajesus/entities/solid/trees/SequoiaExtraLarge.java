package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class SequoiaExtraLarge extends Tree {
	
	/**
	 * Extra Large Sequoia ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public SequoiaExtraLarge(Level level, int x, int y) {
		super(level, x, y, Sprite.SEQUOIA_EXTRA_LARGE, 10, 8, 15);
	}

	// need to add in the entity ID
	@Override
	public byte getId() {
		return Entity.SEQUOIA_EXTRA_LARGE;
	}

}
