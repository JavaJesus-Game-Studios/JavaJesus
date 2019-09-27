package javajesus.graphics.render_strategies;

import javajesus.graphics.IRenderStrategy;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;

public class Render3x3 implements IRenderStrategy {

	@Override
	public void renderNormal(Screen screen, int xLocation, int yLocation,
			int xTile, int yTile, boolean flip, SpriteSheet sheet,
			int[] color) {
		for(int i = 0; i < 3; i++) {
			// Left
			screen.render(xLocation + (tileSize * (flip ? 1: 0)), yLocation + i * tileSize, xTile,
					yTile + i, sheet, flip, color);
			// Middle
			screen.render(xLocation + (tileSize * (flip ? 0: 1)), yLocation + i * tileSize, xTile + 1,
					yTile + i, sheet, flip, color);
			// Right
			screen.render(xLocation + (tileSize * (flip ? 0: 2)), yLocation + i * tileSize, xTile + 2,
					yTile + i, sheet, flip, color);
		}
	}

	@Override
	public void renderSwimming(Screen screen, int xLocation, int yLocation,
			int xTile, int yTile, boolean flip, SpriteSheet sheet,
			int[] color) {
		// Left
		screen.render(xLocation + (tileSize * (flip ? 1: 0)), yLocation + 2 * tileSize, xTile, yTile,
				sheet, flip, color);
		// Middle
		screen.render(xLocation + (tileSize * (flip ? 0: 1)), yLocation + 2 * tileSize, xTile + 1, yTile,
				sheet, flip, color);
		// Right
		screen.render(xLocation + (tileSize * (flip ? 0: 2)), yLocation + 2 * tileSize, xTile + 2, yTile,
				sheet, flip, color);
	}

}
