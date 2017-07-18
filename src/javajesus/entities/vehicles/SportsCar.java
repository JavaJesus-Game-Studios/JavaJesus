package javajesus.entities.vehicles;

import javajesus.level.Level;

/*
 * A car for driving
 */
public class SportsCar extends Car {

	// serialization
	private static final long serialVersionUID = -3313236618756200877L;
	
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
	
	

}
