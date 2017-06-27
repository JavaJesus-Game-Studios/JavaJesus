package javajesus.entities.particles.pickups;

import javajesus.items.Item;
import javajesus.level.Level;

public class VestPickup extends Pickup {
	
	private static final long serialVersionUID = 8303777973165520265L;

	/**
	 * Vest armor Pickup
	 * @param level the level it is on
	 * @param x the x y coordinate
	 * @param y the y coordinate
	 */
	public VestPickup(Level level, int x, int y) {
		super(level, x, y, Item.vest, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 0, 9, 1);
	}

}
