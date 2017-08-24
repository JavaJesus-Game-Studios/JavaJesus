package javajesus.entities.solid.buildings.sanjuan;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * San Juan City Hall!
 */
public class SanJuanCityHall extends Building {

	/**
	 * Creates San Juan city hall
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public SanJuanCityHall(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFF919192, 0xFF4D4DFF }, Sprite.sanJuan_City_Hall);

		// change the bounds
		setBounds(getBounds().x, getBounds().y, getBounds().width - 33, getBounds().height);

		if (level != null)
		level.add(new Door(level, x + 67, y + 104, new PoorHouseInterior(new Point(x + 40, y + 67), level),0,0));
	}

	@Override
    public byte getId(){
        return Entity.SAN_JUAN_CITY_HALL;
    }
}
