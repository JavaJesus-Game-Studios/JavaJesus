package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * For when the player is tired
 */
public class ChairSide extends Furniture {
	
	// sprites used by the chair
	protected final static Sprite chair_south = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/chair_south.png");
	protected final static Sprite chair_east = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/chair_east.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.HORIZONTAL/VERTICAL
	 */
	public ChairSide(Level level, int x, int y) {
		super(level, x, y, Furniture.NORTH);

	}
	
	@Override
    public byte getId(){
        return Entity.CHAIR_SIDE;
    }

	@Override
	protected Sprite getSprite(byte orientation) {
		switch (orientation) {
		case Furniture.EAST:
			return chair_east;
		default:
			return chair_south;
		}
	}
}
