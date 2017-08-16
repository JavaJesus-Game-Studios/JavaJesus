package javajesus.entities.animals;

import javajesus.entities.Entity;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 *	A Dog Mob 
 */
public class Dog extends Animal {

	// color of the fox
	private static final int[] color = { 0xFF111111, 0xFFc1aa6b, 0xFFf2eabb, 0xFFab975f, 0xFF544835 };

	/**
	 * @param level - level it is on
	 * @param x - x coord
	 * @param y - y coord
	 */
	public Dog(Level level, int x, int y) {
		super(level, "Dog", x, y, 16, 16, SpriteSheet.quadrapeds, 8, color);
	}

	@Override
	public byte getId() {
		return Entity.DOG;
	}

}
