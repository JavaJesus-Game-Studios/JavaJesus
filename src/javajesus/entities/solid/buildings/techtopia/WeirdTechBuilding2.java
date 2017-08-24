package javajesus.entities.solid.buildings.techtopia;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * Exactly what its name is
 */
public class WeirdTechBuilding2 extends Building {

	/**
	 * Creates another weird building
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public WeirdTechBuilding2(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFF919192, 0xFF4D4DFF }, Sprite.weirdTechBuilding2);

		if (level != null)
		level.add(new Door(level, x + 17, y + 80, new PoorHouseInterior(new Point(x + 40, y + 67), level),0,0));
	}

	@Override
    public byte getId(){
        return Entity.WEIRD_TECH_BUILDING2;
    }
}
