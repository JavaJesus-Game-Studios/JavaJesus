package javajesus.entities.vehicles;

import java.awt.Rectangle;

import engine.Window;
import javajesus.entities.Player;

public interface Rideable {
	
	/**
	 * 
	 * @return true if player can ride this vehicle
	 */
	public boolean canRide();
	
	/**
	 * Handles what happens when the player enters
	 */
	public void drive(Player player);
	
	/**
	 * Handles what happens when the player leaves
	 */
	public void exit();
	
	/**
	 * Enforces the ridable object is an entity
	 * @return the x coord
	 */
	public short getX();
	
	/**
	 * Enforces the ridable object is an entity
	 * @return the y coord
	 */
	public short getY();
	
	/**
	 * Enforces the ridable object is an entity
	 * @return the bounds of this object
	 */
	public Rectangle getBounds();
	
	/**
	 * @return true if in use
	 */
	public boolean isUsed();

	/**
	 * Input listener for the new rideable
	 * @param window - window to check for input
	 */
	public void input(Window window);
	

}
