package game.entities.vehicles;

import java.awt.Rectangle;

import game.entities.Player;

public interface Ridable {
	
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
	public int getX();
	
	/**
	 * Enforces the ridable object is an entity
	 * @return the y coord
	 */
	public int getY();
	
	/**
	 * Enforces the ridable object is an entity
	 * @return the bounds of this object
	 */
	public Rectangle getBounds();
	
	/**
	 * @return true if in use
	 */
	public boolean isUsed();

}
