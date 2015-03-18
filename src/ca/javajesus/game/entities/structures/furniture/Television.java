package ca.javajesus.game.entities.structures.furniture;

import ca.javajesus.game.gfx.Colors;
import ca.javajesus.level.Level;

public class Television extends Furniture{

	public Television(Level level, int x, int y) {
		super(level, x, y, Furniture.television, Colors.get(-1, 444, 123, 323));
		this.bounds.setSize(getSprite().xSize - 8, getSprite().ySize);
		this.shadow.setSize(0, 0);
		this.bounds.setLocation(x, y);

	}

}
