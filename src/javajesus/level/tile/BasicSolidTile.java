package javajesus.level.tile;

/*
 * A Generic solid tile
 */
public class BasicSolidTile extends BaseTile {

	/**
	 * Creates a Generic Tile that can't be clipped through
	 * 
	 * @param id
	 *            the UNIQUE identifier for this tile
	 * @param x
	 *            the x pos on the spritesheet
	 * @param y
	 *            the y pos on the spritesheet
	 * @param tileColor
	 *            the colorset for this tile
	 * @param levelColor
	 *            the unique color identifier on the level file
	 */
	public BasicSolidTile(int id, int x, int y, int[] tileColor, int levelColor) {
		super(id, x, y, tileColor, levelColor);
		this.solid = true;
	}

}
