package ca.javajesus.game.entities.structures.trees;

import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;

public class MediumSequoia extends Tree {

	private static final long serialVersionUID = 4508786800750502286L;

	public MediumSequoia(Level level, int x, int y) {
		super(level, x, y, 44, Sprite.MEDIUM_SEQUOIA);
	}

}
