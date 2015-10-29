package game.entities.structures.trees;

import level.Level;
import game.graphics.Sprite;

public class SmallSequoia extends Tree {

	private static final long serialVersionUID = 2784752755620913724L;

	public SmallSequoia(Level level, int x, int y) {
		super(level, x, y, 32, Sprite.SMALL_SEQUOIA);
	}

}
