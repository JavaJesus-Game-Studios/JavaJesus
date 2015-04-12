package ca.javajesus.game.entities.particles.pickups;

import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class ShotgunAmmoPickup extends Pickup {
	
	public ShotgunAmmoPickup(Level level, int x, int y) {
		super(level, x, y, Item.shotgunAmmo, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 3, 6);
	}

}
