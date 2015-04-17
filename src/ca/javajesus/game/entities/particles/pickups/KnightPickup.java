package ca.javajesus.game.entities.particles.pickups;

import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class KnightPickup extends Pickup {
	
	public KnightPickup(Level level, int x, int y) {
		super(level, x, y, Item.knight, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 1, 9, 1);
	}

}
