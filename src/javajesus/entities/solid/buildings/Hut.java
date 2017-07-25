package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.HutInterior;

/*
 * A small hut
 */
public class Hut extends Building {

	/**
	 * Creates a hut
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public Hut(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF654000, 0xFF814700, 0xFFFFEA00 }, Sprite.hut_exterior);

		if (level != null)
		level.add(new Door(level, x + 18, y + 32, new HutInterior(new Point(x + 24, y + 43), getLevel())));
	}
	
	@Override
    public byte getId(){
        return Entity.HUT;
    }

}
