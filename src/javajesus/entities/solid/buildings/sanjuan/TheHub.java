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
 * Who does not know what TheHub is?
 */
public class TheHub extends Building {

	/**
	 * Creates a hub
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public TheHub(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFF86868D, 0xFF4D4DFF }, Sprite.theHub);

		if (level != null)
		level.add(new Door(level, x + 46, y + 148, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}

	@Override
    public byte getId(){
        return Entity.THE_HUB;
    }
}
