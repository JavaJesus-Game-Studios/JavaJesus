package javajesus.entities.plant;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class GrassGold extends Grass {

	public GrassGold(Level level, int x, int y) {
		super(level, x, y, 8, 8, Sprite.GRASS_GOLD);

		this.col1 = 0xFFa7a951;
		this.col2 = 0xFF777b3a;
	}

	@Override
	public byte getId() {
		return Entity.GRASS_GOLD;
	}

}
