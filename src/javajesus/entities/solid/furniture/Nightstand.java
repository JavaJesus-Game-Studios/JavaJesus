package javajesus.entities.solid.furniture;

import javajesus.level.Level;

public class Nightstand extends Furniture {

	private static final long serialVersionUID = -5410146361211084516L;

	public Nightstand(Level level, int x, int y) {
		super(level, x, y, Furniture.nightstand, new int[] { 444, 123, 323 });

	}
}
