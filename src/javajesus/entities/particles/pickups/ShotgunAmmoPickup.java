package javajesus.entities.particles.pickups;

import level.Level;
import items.Item;

public class ShotgunAmmoPickup extends Pickup {
	
	private static final long serialVersionUID = -7621331872395658074L;

	/**
	 * Shotgun ammo Pickup
	 * @param level the level it is on
	 * @param x the x y coordinate
	 * @param y the y coordinate
	 */
	public ShotgunAmmoPickup(Level level, int x, int y) {
		super(level, x, y, Item.shotgunAmmo, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 3, 6, 20);
	}

}
