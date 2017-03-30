package javajesus.entities;

import java.awt.Rectangle;

/*
 * A SolidEntity cannot be clipped by the player
 */
public interface SolidEntity {

	/**
	 * The area where the player can walk behind the building
	 * @return the clip through back part of the building
	 */
	public abstract Rectangle getShadow();
	
	// Ratios for the shadow height
	public static final double QUARTER = 0.25, HALF = 0.5, TWO_THIRDS = .66, FIVE_SIXTHS = .83, SEVEN_EIGTHS = .88;

}
