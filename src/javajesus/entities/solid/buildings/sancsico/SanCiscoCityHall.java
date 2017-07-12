package javajesus.entities.solid.buildings.sancsico;

import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * City hall of San Cisco!
 */
public class SanCiscoCityHall extends Building {

	// serialization
	private static final long serialVersionUID = -3512083834295154394L;

	/**
	 * Creates san cisco city hall
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public SanCiscoCityHall(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFBC02 }, Sprite.san_cisco_city_hall);

		level.add(new Transporter(level, x + 90, y + 104, level));
	}

}