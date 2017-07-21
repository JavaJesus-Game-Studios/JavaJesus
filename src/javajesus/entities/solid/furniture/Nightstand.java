package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class Nightstand extends Furniture {

	public Nightstand(Level level, int x, int y) {
		super(level, x, y, Furniture.nightstand, new int[] { 444, 123, 323 });

	}
	
	@Override
    public byte getId(){
        return Entity.NIGHTSTAND;
    }
}
