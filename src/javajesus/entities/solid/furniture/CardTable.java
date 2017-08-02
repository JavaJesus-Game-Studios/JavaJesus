/**
 * 
 */
package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Creates a card table for gamblers
 */
public class CardTable extends Furniture {

	// sprites used
	protected final static Sprite cardTable = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Card_Table_vertical.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.HORIZONTAL/VERTICAL
	 */
	public CardTable(Level level, int x, int y) {
		super(level, x, y, Furniture.VERTICAL);
	}

	@Override
	protected Sprite getSprite(byte orientation) {
		return cardTable;
	}

	@Override
	public byte getId() {
		return Entity.CARD_TABLE;
	}

}
