package game.entities.particles.pickups;

import level.Level;
import items.Item;

public class VestPickup extends Pickup {
	
	private static final long serialVersionUID = 8303777973165520265L;

	public VestPickup(Level level, int x, int y) {
		super(level, x, y, Item.vest, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 0, 9, 1);
	}

}
