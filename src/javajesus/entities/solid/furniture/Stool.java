package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class Stool extends Furniture {

	public Stool(Level level, int x, int y) {
		super(level, x, y, Furniture.stool, new int[] { 444, 123, 323 });

	}
	
	@Override
    public byte getId(){
        return Entity.STOOL;
    }
}
