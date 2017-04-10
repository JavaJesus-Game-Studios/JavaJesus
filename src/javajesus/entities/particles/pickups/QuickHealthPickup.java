package javajesus.entities.particles.pickups;

import javajesus.Game;
import level.Level;

/*
 * Creates a pickup that heals the player
 */
public class QuickHealthPickup extends Pickup {
	
	// amount to heal the player
	private static final int HEALTH = 20;

	private static final long serialVersionUID = -5640816578680329613L;

	/**
	 * Creates a quick heal for the player
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public QuickHealthPickup(Level level, int x, int y) {
		super(level, x, y, null, new int[] { 0xFFFFFFFF, 0xFF990000, 0xFFFF0000 }, 0, 5, 1);

	}

	/**
	 * Updates the pickup
	 */
	public void tick() {
		if (getBounds().intersects(getLevel().getPlayer(Game.PLAYER_NAME).getBounds())) {
			getLevel().getPlayer(Game.PLAYER_NAME).changeHealth(HEALTH);
			getLevel().remove(this);
		}
	}

}