package ca.javajesus.game.entities.particles.pickups;

import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class LaserAmmoPickup extends Pickup {
	
	public LaserAmmoPickup(Level level, int x, int y) {
		super(level, x, y, Item.laserAmmo, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 4, 6);
	}

}
