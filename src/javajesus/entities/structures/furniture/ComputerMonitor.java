package javajesus.entities.structures.furniture;

import javajesus.level.Level;

public class ComputerMonitor extends Furniture {

	private static final long serialVersionUID = -105890562962442808L;

	public ComputerMonitor(Level level, int x, int y) {
		super(level, x, y, Furniture.computerMonitor, new int[] { 444, 123, 323 });

	}
}
