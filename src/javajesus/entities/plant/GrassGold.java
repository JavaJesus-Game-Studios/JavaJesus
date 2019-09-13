package javajesus.entities.plant;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class GrassGold extends Grass {

	public GrassGold(Level level, int x, int y) {
		super(level, x, y, 8, 8, Sprite.GRASS_GOLD);
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Entity.GRASS_GOLD;
	}

}
