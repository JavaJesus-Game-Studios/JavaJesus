package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class FilingCabinet extends Furniture {

	public FilingCabinet(Level level, int x, int y) {
		super(level, x, y, Furniture.filingCabinet, new int[] { 444, 123, 323 });

	}
	
	@Override
    public byte getId(){
        return Entity.FILING_CABINET;
    }
}
