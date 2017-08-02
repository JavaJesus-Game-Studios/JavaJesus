package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class Nightstand extends Furniture {
	
	// sprite used
	protected final static Sprite nightstand_south = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/nightstand_south.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.NORTH/SOUTH/EAST/WEST
	 */
	public Nightstand(Level level, int x, int y) {
		super(level, x, y, Furniture.SOUTH);

	}
	
	@Override
    public byte getId(){
        return Entity.NIGHTSTAND;
    }

	@Override
	protected Sprite getSprite(byte orientation) {
		return nightstand_south;
	}
}
