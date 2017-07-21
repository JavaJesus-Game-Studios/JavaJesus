package javajesus.entities.solid.furniture;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class ComputerTower extends Furniture {

	public ComputerTower(Level level, int x, int y) {
		super(level, x, y, Furniture.computerTower, new int[] { 444, 123, 323 });

	}
	
	@Override
    public byte getId(){
        return Entity.COMPUTER_TOWER;
    }
}
