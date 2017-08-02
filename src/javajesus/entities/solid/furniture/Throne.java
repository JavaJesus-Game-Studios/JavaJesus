package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class Throne extends Furniture {
	
	// sprite used
	protected final static Sprite throne = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/throne.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Throne(Level level, int x, int y) {
		super(level, x, y, Furniture.SOUTH);
	}
	
	@Override
    public byte getId(){
        return Entity.THRONE;
    }

	@Override
	protected Sprite getSprite(byte orientation) {
		return throne;
	}
}
