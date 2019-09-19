package javajesus.entities.plant;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class GrassGold extends Grass {

	private static int[] color = {0xFFa7a951, 0xFF777b3a};
	public GrassGold(Level level, int x, int y) {
		super(level, x, y, 8, 8, Sprite.GRASS_GOLD, color);

	}

	@Override
	public byte getId() {
		return Entity.GRASS_GOLD;
	}
	

}
