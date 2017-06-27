package javajesus.entities.particles.pickups;

import javajesus.items.Item;
import javajesus.level.Level;

public class KnightPickup extends Pickup {
	
	private static final long serialVersionUID = 5962810094632160250L;

	/**
	 * Knight Armor Pickup
	 * @param level the level it is on
	 * @param x the x y coordinate
	 * @param y the y coordinate
	 */
	public KnightPickup(Level level, int x, int y) {
		super(level, x, y, Item.knight, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 1, 9, 1);
	}

}
