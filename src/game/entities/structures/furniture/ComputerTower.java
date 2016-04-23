package game.entities.structures.furniture;

import level.Level;

public class ComputerTower extends Furniture {

	private static final long serialVersionUID = -6405982464822713154L;

	public ComputerTower(Level level, int x, int y) {
		super(level, x, y, Furniture.computerTower, new int[] { 444, 123, 323 });

	}
}
