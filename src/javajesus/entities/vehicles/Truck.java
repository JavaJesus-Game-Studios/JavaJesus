package javajesus.entities.vehicles;

import javajesus.level.Level;

public class Truck extends Car {
	
	private static final long serialVersionUID = 8707099465563372728L;

	public Truck(Level level, int x, int y) {
		super(level, "Truck", x, y, 5);
	}

}
