package javajesus.entities.structures.furniture;

import level.Level;

public class Stool extends Furniture {

	private static final long serialVersionUID = 3348669655379231466L;

	public Stool(Level level, int x, int y) {
		super(level, x, y, Furniture.stool, new int[] { 444, 123, 323 });

	}
}
