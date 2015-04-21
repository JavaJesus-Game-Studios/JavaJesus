package ca.javajesus.game.entities.structures.trees;

import ca.javajesus.level.Level;

public class DeadSequoia extends Tree {

	private static final long serialVersionUID = -7727072271070247898L;

	public DeadSequoia(Level level, int x, int y) {
		super(level, x, y, 58, Tree.DEAD_SEQUOIA);
	}

}
