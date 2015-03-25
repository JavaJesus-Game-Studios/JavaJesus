package ca.javajesus.game.entities.structures.furniture;

import ca.javajesus.level.Level;

public class Nightstand extends Furniture {
	public Nightstand(Level level, int x, int y) {
		super(level, x, y, Furniture.nightstand, new int[] {444, 123, 323});
		this.bounds.setSize(getSprite().xSize - 8, getSprite().ySize);
		this.shadow.setSize(0, 0);
		this.bounds.setLocation(x, y);

	}
}
