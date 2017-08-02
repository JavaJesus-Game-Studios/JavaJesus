package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class ComputerTower extends Furniture {
	
	// sprite used
	protected final static Sprite computerTower = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/computerTower_south.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.NORTH/SOUTH/EAST/WEST
	 */
	public ComputerTower(Level level, int x, int y) {
		super(level, x, y, Furniture.SOUTH);

	}
	
	@Override
    public byte getId(){
        return Entity.COMPUTER_TOWER;
    }

	@Override
	protected Sprite getSprite(byte orientation) {
		return computerTower;
	}
}
