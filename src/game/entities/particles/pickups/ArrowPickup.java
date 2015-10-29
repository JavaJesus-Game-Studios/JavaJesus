package game.entities.particles.pickups;

import level.Level;
import items.Item;

public class ArrowPickup extends Pickup {
	
	private static final long serialVersionUID = -823041990401413711L;

	public ArrowPickup(Level level, int x, int y) {
		super(level, x, y, Item.arrowAmmo, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 1, 7, 5);
	}

}
