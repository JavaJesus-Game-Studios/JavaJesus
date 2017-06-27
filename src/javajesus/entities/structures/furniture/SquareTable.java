package javajesus.entities.structures.furniture;

import javajesus.level.Level;

public class SquareTable extends Furniture {

	private static final long serialVersionUID = -6826176742963495395L;

	public SquareTable(Level level, int x, int y) {
		super(level, x, y, Furniture.squareTable, new int[] { -1, 444, 123, 323 });

	}

}
