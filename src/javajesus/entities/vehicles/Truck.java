package javajesus.entities.vehicles;

import javajesus.level.Level;

/*
 * A car for driving
 */
public class Truck extends Car {
	
	// serialization
	private static final long serialVersionUID = 8707099465563372728L;
	
	// used for offsetting the bounds based on direction
	private static final int SHORT_SIDE = 32, LONG_SIDE = 40;

	/**
	 * Creates a Truck
	 * 
	 * @param level - level it is on
	 * @param x - x position
	 * @param y - y position
	 */
	public Truck(Level level, int x, int y) {
		super(level, "Truck", x, y, SHORT_SIDE, LONG_SIDE, 5);
	}

}
