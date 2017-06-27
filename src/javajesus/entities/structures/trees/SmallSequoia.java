package javajesus.entities.structures.trees;

import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class SmallSequoia extends Tree {

	private static final long serialVersionUID = 2784752755620913724L;

	public SmallSequoia(Level level, int x, int y) {
		super(level, x, y, Sprite.SMALL_SEQUOIA);
		
		setBounds(x + 8, (int) getBounds().getY(), 1, (int) getBounds().getHeight());
	}

}
