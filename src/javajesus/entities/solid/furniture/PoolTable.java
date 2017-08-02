/**
 * 
 */
package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Pool Table	
 */
public class PoolTable extends Furniture {

	// sprites used
	protected final static Sprite poolTable_horizontal = new Sprite(
	        "/VISUAL_DATA/STATICS/FURNITURE/Pool_Table_Horizontal.png");
	protected final static Sprite poolTable_vertical = new Sprite(
	        "/VISUAL_DATA/STATICS/FURNITURE/Pool_Table_Vertical.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.HORIZONTAL/VERTICAL
	 */
	public PoolTable(Level level, int x, int y) {
		super(level, x, y, Furniture.VERTICAL);
	}

	@Override
	public byte getId() {
		return Entity.POOL_TABLE;
	}

	@Override
	protected Sprite getSprite(byte orientation) {
		switch (orientation) {
		case Furniture.HORIZONTAL:
			return poolTable_horizontal;
		default:
			return poolTable_vertical;
		}
	}

}
