package game.entities.structures.trees;

import level.Level;
import game.graphics.Sprite;

public class DeadSequoia extends Tree {

	private static final long serialVersionUID = -7727072271070247898L;

	public DeadSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.DEAD_SEQUOIA);
		
		setBounds(x + 9, (int) getBounds().getY(), 1, (int) getBounds().getHeight());
	}

}
