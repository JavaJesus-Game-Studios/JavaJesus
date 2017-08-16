package javajesus.entities.animals;

import javajesus.entities.Entity;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 *	A Chicken Mob 
 */
public class Chicken extends Animal {
	
	// color of the Chicken
	private static final int[] color = {0xFF111111, 0xFF966529, 0xFFffdd03, 0xFFffb703};

	/**
	 * @param level - level it is on
	 * @param x - x coord
	 * @param y - y coord
	 */
	public Chicken(Level level, int x, int y) {
		super(level, "Chicken", x, y, 8, 8, SpriteSheet.bipeds, 3, color);
	}

	@Override
	public byte getId() {
		return Entity.CHICKEN;
	}

}