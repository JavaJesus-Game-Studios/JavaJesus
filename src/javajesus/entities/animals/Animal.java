package javajesus.entities.animals;

import javajesus.entities.Mob;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * An animal is just a little creature that has a unique movement pattern
 */
public abstract class Animal extends Mob {

	public Animal(Level level,int x, int y, String name) {
		super(level, name, x, y, 1, 16, 16, SpriteSheet.quadrapeds, 100);
	}

	@Override
	public int getStrength() {
		return 0;
	}

	@Override
	public int getDefense() {
		return 0;
	}

}
