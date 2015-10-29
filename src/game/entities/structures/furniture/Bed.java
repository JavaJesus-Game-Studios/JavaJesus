package game.entities.structures.furniture;

import level.Level;

public class Bed extends Furniture{
	
	public Bed(Level level, int x, int y) {
		super(level, x, y, Furniture.bed, new int[] {444, 123, 323});
		this.bounds.setSize(getSprite().xSize - 8, getSprite().ySize);
		this.shadow.setSize(0, 0);
		this.bounds.setLocation(x, y);
	}

}


