package javajesus.entities.vehicles;

import javajesus.entities.Entity;
import javajesus.level.Level;

/*
 * A car for driving
 */
public class SportsCar extends Car {

	// used for offsetting the bounds based on direction
	private static final int SHORT_SIDE = 25, LONG_SIDE = 40;

	/**
	 * Creates a Sports Car
	 * 
	 * @param level - level it is on
	 * @param x - x position
	 * @param y - y position
	 */
	public SportsCar(Level level,int x, int y) {
		super(level, "Sports Car", x, y, SHORT_SIDE, LONG_SIDE, 10);
	} 
	
	@Override
	public byte getId() {
		return Entity.SPORTS_CAR;
	}

}
