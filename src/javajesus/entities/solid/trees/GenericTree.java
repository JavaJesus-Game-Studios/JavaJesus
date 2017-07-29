package javajesus.entities.solid.trees;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Generic Deciduous Tree
 */
public class GenericTree extends Tree {

	/**
	 * GenericTree ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public GenericTree(Level level, int x, int y) {
		super(level, x, y, Sprite.SMALL_DECIDUOUS, 6, 12, 6);
	}

	@Override
    public byte getId(){
        return Entity.GENERIC_TREE;
    }
}
