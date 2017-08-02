package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * The Bar for when the player gets thirsty
 */
public class Bar extends Furniture {

	// sprites the bar uses
	private final static Sprite bar_horizontal = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Bar_Horizontal.png");
	private final static Sprite bar_vertical = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Bar_Vertical.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.HORIZONTAL/VERTICAL
	 */
	public Bar(Level level, int x, int y, byte orientation) {
		super(level, x, y, orientation);
	}
	
	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.HORIZONTAL/VERTICAL
	 */
	public Bar(Level level, int x, int y) {
		this(level, x, y, Furniture.VERTICAL);
	}

	@Override
	protected Sprite getSprite(byte orientation) {

		// return the right sprite
		switch (orientation) {
		case Furniture.HORIZONTAL:
			return bar_horizontal;
		default:
			return bar_vertical;
		}
	}

	/**
	 * @return the ID of the bar
	 */
	@Override
	public byte getId() {
		return Entity.BAR;
	}

}
