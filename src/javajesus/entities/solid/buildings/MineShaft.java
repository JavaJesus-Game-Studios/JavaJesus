package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * Entrance to the mineshaft
 */
public class MineShaft extends Building {

	/**
	 * Creates a mineshaft
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public MineShaft(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF3B312A, 0xFFFFEA5D }, Sprite.mineshaft);

		if (level != null)
		level.add(new Transporter(level, x + 79, y + 48, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}
	
	@Override
    public byte getId(){
        return Entity.MINESHAFT;
    }

}