package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class Sofa extends Furniture {

	public Sofa(Level level, int x, int y) {
		super(level, x, y, Furniture.sofa, new int[] { 444, 123, 323 });

	}
	
	@Override
    public byte getId(){
        return Entity.SOFA;
    }
}
