package game.entities.structures.furniture;

import level.Level;

public class Throne extends Furniture {

	private static final long serialVersionUID = 7753468424652337159L;

	public Throne(Level level, int x, int y) {
		super(level, x, y, Furniture.throne, new int[] { 444, 123, 323 });

	}
}
