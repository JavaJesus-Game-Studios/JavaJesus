package level.tile;

import level.Level;
import game.graphics.Screen;

/*
 * A generic background tile with a simple texture
 */
public class BaseTile extends Tile {

	// ID of the tile
	private int tileId;

	// color of the tile
	private int[] tileColor;

	/**
	 * Creates a Generic Tile
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
	public BaseTile(int id, int x, int y, int[] tileColor, int levelColor) {
		super(id, false, levelColor);

		setTileID(x, y);
		this.tileColor = tileColor;
	}

	/**
	 * Displays the tile on the screen
	 * 
	 * @param screen
	 *            the screen to send it to
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the X tile coordinate (Not entity X coords)
	 * @param y
	 *            the Y tile coordinate (Not entity Y coords)
	 */
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, tileId, tileColor, getSpriteSheet());
	}

	/**
	 * Sets the ID of the tile
	 * 
	 * @param x
	 *            the x position on the spritesheet
	 * @param y
	 *            the y position on the spritesheet
	 */
	protected void setTileID(int x, int y) {
		this.tileId = x + y * getSpriteSheet().boxes;
	}

	/**
	 * @return the tile ID
	 */
	public int getTileID() {
		return tileId;
	}

	// Does not do anything
	public void tick() {

	}

}
