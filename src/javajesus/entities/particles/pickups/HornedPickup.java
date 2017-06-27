package javajesus.entities.particles.pickups;

import javajesus.items.Item;
import javajesus.level.Level;

public class HornedPickup extends Pickup {
	
	private static final long serialVersionUID = 2925048197267329226L;

	/**
	 * Horned Armor Pickup
	 * @param level the level it is on
	 * @param x the x y coordinate
	 * @param y the y coordinate
	 */
	public HornedPickup(Level level, int x, int y) {
		super(level, x, y, Item.horned, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 2, 9, 1);
	}

}
