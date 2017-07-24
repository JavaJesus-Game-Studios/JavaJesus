package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * The prison
 */
public class Prison extends Building {

	/**
	 * Creates a prison
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public Prison(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFABD3FF }, Sprite.prison);

		if (level != null)
		level.add(new Transporter(level, x + 77, y + 64, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

	@Override
    public byte getId(){
        return Entity.PRISON;
    }
}
