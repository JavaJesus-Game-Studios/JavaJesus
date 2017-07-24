package javajesus.entities.solid.buildings.sanjuan;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
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
	 */
	public SanJuanCityHall(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF919192, 0xFF4D4DFF }, Sprite.sanJuan_City_Hall);

		// change the bounds
		setBounds(getBounds().x, getBounds().y, getBounds().width - 33, getBounds().height);

		if (level != null)
		level.add(new Transporter(level, x + 67, y + 104, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}

	@Override
    public byte getId(){
        return Entity.SAN_JUAN_CITY_HALL;
    }
}
