package game.entities.particles.pickups;

import level.Level;
import items.Item;

public class KnightPickup extends Pickup {
	
	private static final long serialVersionUID = 5962810094632160250L;

	public KnightPickup(Level level, int x, int y) {
		super(level, x, y, Item.knight, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 1, 9, 1);
	}

}
