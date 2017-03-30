package javajesus.entities.structures.furniture;

import level.Level;

public class FilingCabinet extends Furniture {

	private static final long serialVersionUID = -943600791892734306L;

	public FilingCabinet(Level level, int x, int y) {
		super(level, x, y, Furniture.filingCabinet, new int[] { 444, 123, 323 });

	}
}
