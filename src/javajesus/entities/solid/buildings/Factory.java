package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * A large factory
 */
public class Factory extends Building {

	/**
	 * Creates a factory
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public Factory(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF3B312A, 0xFF002244 }, Sprite.factory);

		level.add(new Transporter(level, x + 45, y + 69, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}
	
	@Override
    public byte getId(){
        return Entity.FACTORY ;
    }

}
