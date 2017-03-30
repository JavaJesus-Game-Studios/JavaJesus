package javajesus.entities.structures.furniture;

import level.Level;

public class DiningTable extends Furniture {

	private static final long serialVersionUID = -3792764626397054858L;

	public DiningTable(Level level, int x, int y) {
		super(level, x, y, Furniture.diningTableSprite, new int[] { 444, 123, 323 });

	}

}
