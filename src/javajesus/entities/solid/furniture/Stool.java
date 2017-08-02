package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class Stool extends Furniture {
	
	// sprites used
	protected final static Sprite stool = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/stool_horizontal.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.HORIZONTAL/VERTICAL
	 */
	public Stool(Level level, int x, int y) {
		super(level, x, y, Furniture.HORIZONTAL);

	}
	
	@Override
    public byte getId(){
        return Entity.STOOL;
    }

	@Override
	protected Sprite getSprite(byte orientation) {
		return stool;
	}
}
