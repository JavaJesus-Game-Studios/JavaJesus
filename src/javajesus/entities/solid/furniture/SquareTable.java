package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class SquareTable extends Furniture {

	public SquareTable(Level level, int x, int y) {
		super(level, x, y, Furniture.squareTable, new int[] { -1, 444, 123, 323 });

	}

	@Override
    public byte getId(){
        return Entity.SQUARE_TABLE;
    }
}
