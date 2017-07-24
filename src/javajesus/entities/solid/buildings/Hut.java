package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
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
	 */
	public Hut(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF654000, 0xFF814700, 0xFFFFEA00 }, Sprite.hut_exterior);

		if (level != null)
		level.add(new Transporter(level, x + 18, y + 32, new HutInterior(new Point(x + 24, y + 43), getLevel())));
	}
	
	@Override
    public byte getId(){
        return Entity.HUT;
    }

}
