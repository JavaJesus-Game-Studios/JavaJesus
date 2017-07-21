package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class ChairFront extends Furniture {

	public ChairFront(Level level, int x, int y) {
		super(level, x, y, Furniture.chairFront, new int[] { 444, 123, 323 });

	}

	@Override
    public byte getId(){
        return Entity.CHAIR_FRONT;
    }
}
