package javajesus.entities.solid.buildings.oakwood;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * City hall of Oakwood
 */
public class OakwoodCityHall extends Building {

	/**
	 * Creates oakwood city hall
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public OakwoodCityHall(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFFF99 }, Sprite.oakwoodcityhall);

		if (level != null)
		level.add(
		        new Transporter(level, x + 48, y + 104, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}
	
	@Override
    public byte getId(){
        return Entity.OAKWOOD_CITY_HALL;
    }

}
