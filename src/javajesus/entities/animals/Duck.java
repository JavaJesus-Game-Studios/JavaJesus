package javajesus.entities.animals;

import javajesus.entities.Entity;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 *	A Duck Mob 
 */
public class Duck extends Animal {
	
	// color of the Duck
	private static final int[] color = { 0xFF111111, 0xFFFFFFFF, 0xFFffdd03, 0xFFff9203};

	/**
	 * @param level - level it is on
	 * @param x - x coord
	 * @param y - y coord
	 */
	public Duck(Level level, int x, int y) {
		super(level, "Duck", x, y, 8, 16, SpriteSheet.bipeds, 1, color);
	}

	@Override
	public byte getId() {
		return Entity.DUCK;
	}

}