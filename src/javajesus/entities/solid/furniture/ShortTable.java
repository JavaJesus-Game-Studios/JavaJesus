package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class ShortTable extends Furniture {
	
	// sprites used
	protected final static Sprite diningTable_horizontal = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/short_horizontal.png");
	protected final static Sprite diningTable_vertical = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/short_vertical.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.HORIZONTAL/VERTICAL
	 */
	public ShortTable(Level level, int x, int y) {
		super(level, x, y, Furniture.VERTICAL);

	}

	@Override
    public byte getId(){
        return Entity.DINING_TABLE;
    }

	@Override
	protected Sprite getSprite(byte orientation) {
		switch (orientation) {
		case Furniture.HORIZONTAL:
			return diningTable_horizontal;
		default:
			return diningTable_vertical;
		}
	}
}
