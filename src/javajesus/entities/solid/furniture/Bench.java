package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class Bench extends Furniture {

	public Bench(Level level, int x, int y) {
		super(level, x, y, Furniture.bench, new int[] { 444, 123, 323 });
	}
	
	@Override
    public byte getId(){
        return Entity.BENCH;
    }
}
