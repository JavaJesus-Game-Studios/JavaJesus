package ca.javajesus.game.entities.structures.trees;

import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;

public class SmallSequoia extends Tree {

	private static final long serialVersionUID = 2784752755620913724L;

	public SmallSequoia(Level level, int x, int y) {
		super(level, x, y, 32, Sprite.SMALL_SEQUOIA);
	}

}
