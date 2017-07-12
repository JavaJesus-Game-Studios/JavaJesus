package javajesus.entities.solid.furniture;

import javajesus.level.Level;

public class ChairSide extends Furniture {

	private static final long serialVersionUID = -276946332093939606L;

	public ChairSide(Level level, int x, int y) {
		super(level, x, y, Furniture.chairSide, new int[] { 444, 123, 323 });

	}
}
