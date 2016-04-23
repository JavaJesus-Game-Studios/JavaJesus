package game.entities.structures.furniture;

import level.Level;

public class Television extends Furniture {

	private static final long serialVersionUID = 5070821919781684303L;

	public Television(Level level, int x, int y) {
		super(level, x, y, Furniture.television, new int[] { 444, 123, 323 });

	}

}
