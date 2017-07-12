package javajesus.entities.solid.trees;

import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Generic Deciduous Tree
 */
public class GenericTree extends Tree {

	// serialization
	private static final long serialVersionUID = -824593418941127086L;

	/**
	 * GenericTree ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public GenericTree(Level level, int x, int y) {
		super(level, x, y, Sprite.SMALL_DECIDUOUS, 5, 15, 6);
	}

}
