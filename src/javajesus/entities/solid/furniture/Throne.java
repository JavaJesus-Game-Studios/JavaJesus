package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class Throne extends Furniture {

	public Throne(Level level, int x, int y) {
		super(level, x, y, Furniture.throne, new int[] { 444, 123, 323 });

	}
	
	@Override
    public byte getId(){
        return Entity.THRONE;
    }
}
