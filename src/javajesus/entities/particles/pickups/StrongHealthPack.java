package javajesus.entities.particles.pickups;

import javajesus.items.Item;
import javajesus.level.Level;

public class StrongHealthPack extends Pickup {

	private static final long serialVersionUID = -6418003889272143276L;
	
	/**
	 * Strong health Pickup (An Item)
	 * @param level the level it is on
	 * @param x the x y coordinate
	 * @param y the y coordinate
	 */
	public StrongHealthPack(Level level, int x, int y) {
		super(level, x, y, Item.strongHealthPack, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 1, 5, 1);
	}

}
