package javajesus.entities.structures.furniture;

import javajesus.level.Level;

public class LongTable extends Furniture {

	private static final long serialVersionUID = 7567316402555116152L;

	public LongTable(Level level, int x, int y) {
		super(level, x, y, Furniture.longTable, new int[] { 444, 123, 323 });

	}
}
