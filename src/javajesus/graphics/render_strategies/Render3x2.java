package javajesus.graphics.render_strategies;

import javajesus.graphics.IRenderStrategy;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;

public class Render3x2 implements IRenderStrategy {

	@Override
	public void renderNormal(Screen screen, int xLocation, int yLocation,
			int xTile, int yTile, boolean flip, SpriteSheet sheet,
			int[] color) {
		// Render from top to bottom
		for( int i = 0; i < 2; i++ ) {
			// Left
			screen.render(xLocation + (TILE_SIZE * (flip ? 1: 0)), yLocation + i * TILE_SIZE, xTile,
						yTile + i, sheet, flip, color);
			// Middle
			screen.render(xLocation + (TILE_SIZE * (flip ? 0: 1)), yLocation + i * TILE_SIZE, xTile + 1,
						yTile + i, sheet, flip, color);
			// Right
			screen.render(xLocation + (TILE_SIZE * (flip ? -1: 2)), yLocation + i * TILE_SIZE, xTile + 2,
						yTile + i, sheet, flip, color);
		}
	}

	@Override
	public void renderSwimming(Screen screen, int xLocation, int yLocation,
			int xTile, int yTile, boolean flip, SpriteSheet sheet,
			int[] color) {
		// TODO Auto-generated method stub

	}

}
