package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class Television extends Furniture {

	public Television(Level level, int x, int y) {
		super(level, x, y, Furniture.television, new int[] { 444, 123, 323 });

	}

	@Override
    public byte getId(){
        return Entity.TELEVISION;
    }
}
