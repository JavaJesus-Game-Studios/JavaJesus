package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class SquareTable extends Furniture {
	
	// sprites used
	protected final static Sprite squareTable = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/squareTable.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public SquareTable(Level level, int x, int y) {
		super(level, x, y, Furniture.NORTH);

	}

	@Override
    public byte getId(){
        return Entity.SQUARE_TABLE;
    }

	@Override
	protected Sprite getSprite(byte orientation) {
		return squareTable;
	}
}
