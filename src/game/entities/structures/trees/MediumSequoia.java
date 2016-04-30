package game.entities.structures.trees;

import level.Level;
import game.graphics.Sprite;

public class MediumSequoia extends Tree {

	private static final long serialVersionUID = 4508786800750502286L;

	public MediumSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.MEDIUM_SEQUOIA);
	}

}
