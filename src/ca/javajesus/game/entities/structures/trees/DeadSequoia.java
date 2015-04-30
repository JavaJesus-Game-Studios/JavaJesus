package ca.javajesus.game.entities.structures.trees;

import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;

public class DeadSequoia extends Tree {

	private static final long serialVersionUID = -7727072271070247898L;

	public DeadSequoia(Level level, int x, int y) {
		super(level, x, y, 58, Sprite.DEAD_SEQUOIA);
	}

}
