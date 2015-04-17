package ca.javajesus.game.entities.particles.pickups;

import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class RevolverAmmoPickup extends Pickup {
	
	public RevolverAmmoPickup(Level level, int x, int y) {
		super(level, x, y, Item.revolverAmmo, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 2, 6, 20);
	}

}
