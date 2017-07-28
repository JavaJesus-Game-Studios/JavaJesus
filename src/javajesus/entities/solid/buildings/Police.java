package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * A typical police station
 */
public class Police extends Building {

	/**
	 * Creates a police station
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public Police(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFFC9C9C9, 0xFF496787 }, Sprite.police_building);

		if (level != null)
		level.add(new Door(level, x + 25, y + 69, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

	@Override
    public byte getId(){
        return Entity.POLICE;
    }
}