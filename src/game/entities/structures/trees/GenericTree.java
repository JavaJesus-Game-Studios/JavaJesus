package game.entities.structures.trees;

import level.Level;
import game.graphics.Sprite;

public class GenericTree extends Tree {
	
	private static final long serialVersionUID = -824593418941127086L;

	public GenericTree(Level level, int x, int y) {
		super(level, x, y, 0, Sprite.SMALL_DECIDUOUS);
	}

}
