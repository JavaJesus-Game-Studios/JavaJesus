package ca.javajesus.game.entities.particles.pickups;

import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class StrongHealthPack extends Pickup {

	public StrongHealthPack(Level level, int x, int y) {
		super(level, x, y, Item.strongHealthPack, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 1, 5);
	}

}
