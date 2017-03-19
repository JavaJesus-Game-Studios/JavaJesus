package game.entities.particles.pickups;

import level.Level;
import items.Item;

public class AssaultRifleAmmo extends Pickup {
	
	private static final long serialVersionUID = 4831602260854923312L;

	/**
	 * Assault Rifle Ammo Pickup
	 * @param level the level it is on
	 * @param x the x y coordinate
	 * @param y the y coordinate
	 */
	public AssaultRifleAmmo(Level level, int x, int y) {
		super(level, x, y, Item.assaultRifleAmmo, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 0, 6, 30);
	}

}
