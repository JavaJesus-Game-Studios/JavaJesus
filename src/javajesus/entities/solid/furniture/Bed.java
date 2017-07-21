package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

/*
 * Creates a bed
 */
public class Bed extends Furniture {

	public Bed(Level level, int x, int y) {
		super(level, x, y, Furniture.bed, new int[] { 444, 123, 323 });
	}

	@Override
    public byte getId(){
        return Entity.BED;
    }
}
