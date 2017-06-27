package javajesus.entities.structures.furniture;

import javajesus.level.Level;

/*
 * Creates a bed
 */
public class Bed extends Furniture {

	private static final long serialVersionUID = -846007811659906473L;

	public Bed(Level level, int x, int y) {
		super(level, x, y, Furniture.bed, new int[] { 444, 123, 323 });
	}

}
