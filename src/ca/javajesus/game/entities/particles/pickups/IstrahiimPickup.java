package ca.javajesus.game.entities.particles.pickups;

import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class IstrahiimPickup extends Pickup {
	
	private static final long serialVersionUID = -8920813691304678304L;

	public IstrahiimPickup(Level level, int x, int y) {
		super(level, x, y, Item.owl, new int[] { 0xFFFFFFFF,
				0xFF990000, 0xFFFF0000 }, 4, 9, 1);
	}

}
