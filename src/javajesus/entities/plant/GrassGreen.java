package javajesus.entities.plant;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class GrassGreen extends Grass {

	public GrassGreen(Level level, int x, int y) {
		super(level, x, y, 8, 8, Sprite.GRASS_GREEN);
	}

	@Override
	public byte getId() {
		return Entity.GRASS_GREEN;
	}

}
