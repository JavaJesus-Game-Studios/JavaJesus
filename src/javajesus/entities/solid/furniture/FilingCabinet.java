package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class FilingCabinet extends Furniture {
	
	// sprites used
	protected final static Sprite filingCabinet = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/filingCabinet_south.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.NORTH/SOUTH/EAST/WEST
	 */
	public FilingCabinet(Level level, int x, int y) {
		super(level, x, y, Furniture.SOUTH);

	}
	
	@Override
    public byte getId(){
        return Entity.FILING_CABINET;
    }

	@Override
	protected Sprite getSprite(byte orientation) {
		return filingCabinet;
	}
}
