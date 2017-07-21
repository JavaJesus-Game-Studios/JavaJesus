package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.RancheroHouseInterior;

/*
 * Typical ranchero house
 */
public class RancheroHouse extends Building {

	/**
	 * Creates a ranchero house
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public RancheroHouse(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFD30000 }, Sprite.ranchero_house);

		level.add(new Transporter(level, x + 44, y + 45, new RancheroHouseInterior(new Point(x + 50, y + 56), level)));
	}

	@Override
    public byte getId(){
        return Entity.RANCHERO_HOUSE;
    }
}
