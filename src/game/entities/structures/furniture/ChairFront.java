package game.entities.structures.furniture;

import level.Level;

public class ChairFront extends Furniture {

	private static final long serialVersionUID = -8672768299698721334L;

	public ChairFront(Level level, int x, int y) {
		super(level, x, y, Furniture.chairFront, new int[] { 444, 123, 323 });

	}

}
