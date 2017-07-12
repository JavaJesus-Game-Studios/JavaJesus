package javajesus.entities.solid.buildings.oakwood;

import java.awt.Point;

import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * City hall of Oakwood
 */
public class OakwoodCityHall extends Building {

	// serialization
	private static final long serialVersionUID = 1092805299412039212L;

	/**
	 * Creates oakwood city hall
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public OakwoodCityHall(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFFF99 }, Sprite.oakwoodcityhall);

		level.add(
		        new Transporter(level, x + 48, y + 104, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

}
