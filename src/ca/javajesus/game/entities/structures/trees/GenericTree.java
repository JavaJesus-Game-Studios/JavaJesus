package ca.javajesus.game.entities.structures.trees;

import ca.javajesus.level.Level;

public class GenericTree extends Tree {
	
	private static final long serialVersionUID = -824593418941127086L;

	public GenericTree(Level level, int x, int y) {
		super(level, x, y, 0, Tree.SMALL_DECIDUOUS);
	}

}
