package javajesus.entities.solid.buildings.techtopia;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * Tech headquarters
 */
public class PearHQ extends Building {

	/**
	 * Creates a pear hq
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public PearHQ(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { -1, 111, 0xFF648ca4, 0xFFf87a36 }, Sprite.pear_hq);

		if (level != null)
		level.add(
		        new Transporter(level, x + 46, y + 176, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}
	
	@Override
    public byte getId(){
        return Entity.PEAR_HQ;
    }
}
