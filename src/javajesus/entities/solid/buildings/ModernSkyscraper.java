package javajesus.entities.solid.buildings;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * A large skyscraper
 */
public class ModernSkyscraper extends Building {

	/**
	 * Creates a skyscraper
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public ModernSkyscraper(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF182D42, 0xFF3E89D5 }, Sprite.modern_skyscraper);

		if (level != null)
		level.add(new Door(level, x + 43, y + 235, getLevel(),0,0));
	}

	@Override
    public byte getId(){
        return Entity.MODERN_SKYSCRAPER;
    }
}
