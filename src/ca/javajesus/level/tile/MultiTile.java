package ca.javajesus.level.tile;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class MultiTile extends Tile {

	protected int width;
	protected int height;
	protected int tileID;
	protected Tile[] multiBlock;

	public MultiTile(int id, int levelColour, int width, int height,
			Tile[] multiBlock) {
		super(id, false, false, levelColour, SpriteSheet.tiles);
		this.width = width;
		this.height = height;
		this.multiBlock = multiBlock;
	}

	public void initMultiBlock(Level level, int x, int y) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				level.alterTile(x + j, y + i, multiBlock[j + i * width]);
			}
		}
	}

	public void tick() {

	}

	public void render(Screen screen, Level level, int x, int y) {

	}

}
