package javajesus.entities.animals;

import javajesus.entities.Entity;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 *	A Cat Mob 
 */
public class Cat extends Animal {
	
	// color of the fox
	private static final int[] color = { 0xFF111111, 0xFFFFFFFF, 0xFFEDC5AB, 0xFFEDC5AB };

	/**
	 * @param level - level it is on
	 * @param x - x coord
	 * @param y - y coord
	 */
	public Cat(Level level, int x, int y) {
		super(level, "Cat", x, y, 16, 16, SpriteSheet.quadrapeds, 10, color);
	}

	@Override
	public byte getId() {
		return Entity.CAT;
	}

}
