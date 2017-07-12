package javajesus.entities.solid.furniture;

import javajesus.level.Level;

public class Bench extends Furniture {

	private static final long serialVersionUID = -7533560997053567366L;

	public Bench(Level level, int x, int y) {
		super(level, x, y, Furniture.bench, new int[] { 444, 123, 323 });
	}
}
