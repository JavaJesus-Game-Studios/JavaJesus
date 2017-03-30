package javajesus.entities.structures.trees;

import javajesus.graphics.Sprite;
import level.Level;

public class GenericTree extends Tree {
	
	private static final long serialVersionUID = -824593418941127086L;

	public GenericTree(Level level, int x, int y) {
		super(level, x, y, Sprite.SMALL_DECIDUOUS);
	}

}
