package ca.javajesus.game.entities.particles.pickups;

import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class ArrowPickup extends Pickup {
	
	private static final long serialVersionUID = -823041990401413711L;

	public ArrowPickup(Level level, int x, int y) {
		super(level, x, y, Item.arrowAmmo, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 1, 7, 5);
	}

}
