package javajesus.graphics.render_strategies;

import javajesus.graphics.IRenderStrategy;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;

public class Render4x4 implements IRenderStrategy {

	public Render4x4() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderNormal(Screen screen, int xLocation, int yLocation,
			int xTile, int yTile, boolean flip, SpriteSheet sheet,
			int[] color) {
		// Render Left to Right, Top to Bottom
		for(int i = 0; i < 4; i++) {
			// Left Tile
			screen.render(xLocation + (tileSize * (flip ? 1: 0)), yLocation + i * tileSize, xTile,
					yTile + i, sheet, flip, color);
			// Middle Left Tile
			screen.render(xLocation + (tileSize * (flip ? 0: 1)), yLocation + i * tileSize, xTile + 1,
					yTile + i, sheet, flip, color);
			// Middle Right Tile
			screen.render(xLocation + (tileSize * (flip ? 0: 2)), yLocation + i * tileSize, xTile + 2,
					yTile + i, sheet, flip, color);
			// Right Tile
			screen.render(xLocation + (tileSize * (flip ? 0: 3)), yLocation + i * tileSize, xTile + 3,
					yTile + i, sheet, flip, color);
		}

	}

	@Override
	public void renderSwimming(Screen screen, int xLocation, int yLocation,
			int xTile, int yTile, boolean flip, SpriteSheet sheet,
			int[] color) {
		// Left Tile
		screen.render(xLocation + (tileSize * (flip ? 1: 0)), yLocation + 3 * tileSize, xTile,
				yTile, sheet, flip, color);
		// Middle Left Tile
		screen.render(xLocation + (tileSize * (flip ? 0: 1)), yLocation + 3 * tileSize, xTile + 1,
				yTile, sheet, flip, color);
		// Middle Right Tile
		screen.render(xLocation + (tileSize * (flip ? 0: 2)), yLocation + 3 * tileSize, xTile + 2,
				yTile, sheet, flip, color);
		// Right Tile
		screen.render(xLocation + (tileSize * (flip ? 0: 3)), yLocation + 3 * tileSize, xTile + 3,
				yTile, sheet, flip, color);
	}

}
