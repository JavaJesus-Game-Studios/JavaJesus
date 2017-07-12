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
	
}
