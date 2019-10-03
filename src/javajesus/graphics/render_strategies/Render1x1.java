package javajesus.graphics.render_strategies;

import javajesus.graphics.IRenderStrategy;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;

public class Render1x1 implements IRenderStrategy {

	@Override
	public void renderNormal(Screen screen, int xLocation, int yLocation,
			int xTile, int yTile, boolean flip, SpriteSheet sheet,
			int[] color) {
		screen.render(xLocation, yLocation, xTile, yTile, sheet, flip, color);

	}

	@Override
	public void renderSwimming(Screen screen, int xLocation, int yLocation,
			int xTile, int yTile, boolean flip, SpriteSheet sheet,
			int[] color) {
		// TODO Auto-generated method stub

	}

}
