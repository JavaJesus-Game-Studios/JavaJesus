package javajesus.entities.solid.furniture;

import javajesus.level.Level;

public class Sofa extends Furniture {

	private static final long serialVersionUID = 488869657618846635L;

	public Sofa(Level level, int x, int y) {
		super(level, x, y, Furniture.sofa, new int[] { 444, 123, 323 });

	}
}
