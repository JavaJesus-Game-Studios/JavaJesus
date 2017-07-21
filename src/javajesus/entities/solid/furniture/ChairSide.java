package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class ChairSide extends Furniture {

	public ChairSide(Level level, int x, int y) {
		super(level, x, y, Furniture.chairSide, new int[] { 444, 123, 323 });

	}
	
	@Override
    public byte getId(){
        return Entity.CHAIR_SIDE;
    }
}
