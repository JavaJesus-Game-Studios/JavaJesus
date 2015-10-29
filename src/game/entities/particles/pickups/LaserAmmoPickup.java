package game.entities.particles.pickups;

import level.Level;
import items.Item;

public class LaserAmmoPickup extends Pickup {
	
	private static final long serialVersionUID = -2396683467550666711L;

	public LaserAmmoPickup(Level level, int x, int y) {
		super(level, x, y, Item.laserAmmo, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 4, 6, 10);
	}

}
