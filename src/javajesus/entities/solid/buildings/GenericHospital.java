package javajesus.entities.solid.buildings;

import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * A typical hospital
 */
public class GenericHospital extends Building {

	// serialization
	private static final long serialVersionUID = 1190467540624136913L;

	/**
	 * Creates a hospital
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public GenericHospital(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFF496787 }, Sprite.generic_hospital);

		level.add(new Transporter(level, x + 45, y + 64, getLevel()));
	}

}
