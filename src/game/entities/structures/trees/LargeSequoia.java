package game.entities.structures.trees;

import level.Level;
import game.graphics.Sprite;

public class LargeSequoia extends Tree {

	private static final long serialVersionUID = -6542198444757793732L;

	public LargeSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.LARGE_SEQUOIA);
	}

}
