package ca.javajesus.game.entities.particles.pickups;

import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class VestPickup extends Pickup {
	
	public VestPickup(Level level, int x, int y) {
		super(level, x, y, Item.vest, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 0, 9);
	}

}
