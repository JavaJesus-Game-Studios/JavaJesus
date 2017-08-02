package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Creates a bed
 */
public class Bed extends Furniture {

	// sprite used by bed
	private final static Sprite bed_south = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/bed_south.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.NORTH/SOUTH/EAST/WEST
	 */
	public Bed(Level level, int x, int y) {
		super(level, x, y, Furniture.SOUTH);
	}

	@Override
	protected Sprite getSprite(byte orientation) {
		switch (orientation) {
		default:
			return bed_south;
		}
	}

	@Override
	public byte getId() {
		return Entity.BED;
	}
}
