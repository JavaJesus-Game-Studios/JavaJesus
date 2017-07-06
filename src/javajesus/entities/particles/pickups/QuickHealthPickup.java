package javajesus.entities.particles.pickups;

import javajesus.SoundHandler;
import javajesus.level.Level;

/*
 * Creates a pickup that heals the player
 */
public class QuickHealthPickup extends Pickup {
	
	// serialization
	private static final long serialVersionUID = -5640816578680329613L;
	
	// amount to heal the player
	private final int health ;

	/**
	 * Creates a quick heal for the player
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 */
	public QuickHealthPickup(Level level, int x, int y, int health) {
		super(level, x, y, null, new int[] { 0xFFFFFFFF, 0xFF990000, 0xFFFF0000 }, 0, 5, 1);
		this.health = health;

	}

	/**
	 * use()
	 * Removes the item from the game
	 * Plays a click sound
	 * 
	 * @return the health from this pickup
	 */
	public int use() {
		
		SoundHandler.play(SoundHandler.click);
		
		getLevel().remove(this);
		return health;
	}

}
