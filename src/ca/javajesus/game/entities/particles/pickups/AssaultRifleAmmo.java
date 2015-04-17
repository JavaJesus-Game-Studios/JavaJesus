package ca.javajesus.game.entities.particles.pickups;

import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class AssaultRifleAmmo extends Pickup {
	
	public AssaultRifleAmmo(Level level, int x, int y) {
		super(level, x, y, Item.assaultRifleAmmo, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 0, 6, 30);
	}

}
