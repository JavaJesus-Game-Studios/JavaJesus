package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.ShantyHouseInterior;

/*
 * a small run down house
 */
public class ShantyHouse extends Building {

	public ShantyHouse(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFFADADAD, 0xFF000000 }, Sprite.shantyhouse);

		if (level != null)
		level.add(new Door(level, x + 12, y + 31, new ShantyHouseInterior(new Point(x + 18, y + 42), level)));
	}
	
	@Override
    public byte getId(){
        return Entity.SHANTY_HOUSE;
    }
}
