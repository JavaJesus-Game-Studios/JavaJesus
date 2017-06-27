package javajesus.entities.structures.trees;

import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class MediumSequoia extends Tree {

	private static final long serialVersionUID = 4508786800750502286L;

	public MediumSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.MEDIUM_SEQUOIA);
		
		setBounds(x + 10, (int) getBounds().getY(), 1, (int) getBounds().getHeight());
	}

}
