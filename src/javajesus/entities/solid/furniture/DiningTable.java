package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class DiningTable extends Furniture {

	public DiningTable(Level level, int x, int y) {
		super(level, x, y, Furniture.diningTableSprite, new int[] { 444, 123, 323 });

	}

	@Override
    public byte getId(){
        return Entity.DINING_TABLE;
    }
}
