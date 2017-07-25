package javajesus.entities.solid.buildings;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * A typical hospital
 */
public class GenericHospital extends Building {

	/**
	 * Creates a hospital
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public GenericHospital(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFF496787 }, Sprite.generic_hospital);

		if (level != null)
		level.add(new Door(level, x + 45, y + 64, getLevel()));
	}

	@Override
    public byte getId(){
        return Entity.GENERIC_HOSPITAL;
    }
}
