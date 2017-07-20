package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class LongTable extends Furniture {

	public LongTable(Level level, int x, int y) {
		super(level, x, y, Furniture.longTable, new int[] { 444, 123, 323 });

	}
	
	@Override
    public byte getId(){
        return Entity.LONG_TABLE;
    }
}
