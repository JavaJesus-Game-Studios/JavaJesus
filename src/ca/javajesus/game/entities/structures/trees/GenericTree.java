package ca.javajesus.game.entities.structures.trees;

import ca.javajesus.level.Level;

public class GenericTree extends Tree {
	
	public GenericTree(Level level, int x, int y) {
		super(level, x, y, 0, Tree.SMALL_DECIDUOUS);
	}

}
