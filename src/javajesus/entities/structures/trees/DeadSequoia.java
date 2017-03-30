package javajesus.entities.structures.trees;

import javajesus.graphics.Sprite;
import level.Level;

public class DeadSequoia extends Tree {

	private static final long serialVersionUID = -7727072271070247898L;

	public DeadSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.DEAD_SEQUOIA);
		
		setBounds(x + 9, (int) getBounds().getY(), 1, (int) getBounds().getHeight());
	}

}
