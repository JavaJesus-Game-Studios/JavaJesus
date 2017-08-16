package javajesus.entities.animals;

import javajesus.entities.Entity;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 *	A Pigeon Mob 
 */
public class Pigeon extends Animal {
	
	// color of the Pigeon
	private static final int[] color = { 0xFF111111, 0xFF86889e, 0xFF34353a, 0xFF34353a, 0xFF636581};

	/**
	 * @param level - level it is on
	 * @param x - x coord
	 * @param y - y coord
	 */
	public Pigeon(Level level, int x, int y) {
		super(level, "Pigeon", x, y, 8, 8, SpriteSheet.bipeds, 0, color);
	}

	@Override
	public byte getId() {
		return Entity.PIGEON;
	}

}