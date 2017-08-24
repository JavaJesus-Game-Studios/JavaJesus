package javajesus.entities.solid.buildings.techtopia;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.RadarDishInterior;

/*
 * A large round dish
 */
public class RadarDish extends Building {

	/**
	 * Creates a radar dish
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public RadarDish(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFFA3A3C2, 0xFF75758C }, Sprite.radardish);

		// change the  bounds
		setBounds(getBounds().x + 6, getBounds().y, getBounds().width - 12, getBounds().height);

		if (level != null)
		level.add(new Door(level, x + 37, y + 117, new RadarDishInterior(new Point(x + 43, y + 125), level),0,0));
	}

	@Override
    public byte getId(){
        return Entity.RADAR_DISH;
    }
}
