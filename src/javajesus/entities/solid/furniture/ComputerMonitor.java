package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class ComputerMonitor extends Furniture {
	
	// sprite used
	protected final static Sprite computer_south = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/computer_south.png");

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param orientation - Furniture.NORTH/SOUTH/EAST/WEST
	 */
	public ComputerMonitor(Level level, int x, int y) {
		super(level, x, y, Furniture.SOUTH);

	}
	
	@Override
    public byte getId(){
        return Entity.COMPUTER_MONITOR;
    }

	@Override
	protected Sprite getSprite(byte orientation) {
		return computer_south;
	}
}
