package javajesus.graphics.render_strategies;

import javajesus.graphics.IRenderStrategy;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.utility.Direction;

public class Render2x2 implements IRenderStrategy {

	@Override
	public void renderNormal(Screen screen, int xLocation, int yLocation, int xTile, int yTile, boolean flip,
	        SpriteSheet sheet, int[] color) {
		for(int i = 0; i < 2; i++) {
			// Left
			screen.render(xLocation + (tileSize * (flip ? 1: 0)), yLocation + i * tileSize, xTile,
					yTile + i, sheet, flip, color);
			// Right
			screen.render(xLocation + (tileSize * (flip ? 0: 1)), yLocation + i * tileSize, xTile + 1,
					yTile + i, sheet, flip, color);
		}
		
	}

	@Override
	public void renderSwimming(Screen screen, int xLocation, int yLocation, int xTile, int yTile, boolean flip,
			SpriteSheet sheet, int[] color) {
		// Left
		screen.render(xLocation + (tileSize * (flip ? 1: 0)), yLocation + 1 * tileSize, xTile, yTile,
				sheet, flip, color);
		// Right
		screen.render(xLocation + (tileSize * (flip ? 0: 1)), yLocation + 1 * tileSize, xTile + 1, yTile,
				sheet, flip, color);		
	}
	
	// 2x2 Sprites will have 2x3 Rendering while carrying a Gun
	public void renderShooting(Screen screen, int xLocation, int yLocation, int xTile, int yTile, boolean flip,
			boolean isShooting, Direction movingDir, Direction shootingDir, SpriteSheet sheet, int[] color) {
		// Render from right to left
		for( int i = 0; i < 3; i++ ) {
			// Correcting for extra left hand tile for South and West directions
			if( ((movingDir == Direction.SOUTH || movingDir == Direction.WEST) && !isShooting)
				|| ((shootingDir == Direction.SOUTH || shootingDir == Direction.WEST) && isShooting )) {
				// Top
				screen.render(xLocation + (i - 1) * tileSize, yLocation, xTile + i, yTile, sheet, flip, color);
				// Bottom
				screen.render(xLocation + (i - 1) * tileSize, yLocation + tileSize, xTile + i, yTile + 1, sheet, flip, color);
			} else {
				// Top
				screen.render(xLocation + i * tileSize, yLocation, xTile + i, yTile, sheet, flip, color);
				// Bottom
				screen.render(xLocation + i * tileSize, yLocation + tileSize, xTile + i, yTile + 1, sheet, flip, color);
				}
		}
	}
	// 2x2 Sprites will have 3x2 and 3x3 Walking Sprites while Carrying a Sword
	public void renderSwordWalk(Screen screen, int xLocation, int yLocation, int xTile, int yTile, boolean flip, Direction movingDir,
			SpriteSheet sheet, int[] color) {
		boolean isLongitudinal = (movingDir == Direction.WEST || movingDir == Direction.EAST);

		for(int i = 0; i < 3; i++) {
			if(!isLongitudinal) {
				//Left
				screen.render(xLocation, yLocation + (i -1) * tileSize, xTile, yTile + (i -1), sheet, flip, color);
				// Right
				screen.render(xLocation + tileSize, yLocation + (i-1) * tileSize, xTile + 1, yTile + (i-1), sheet,
						flip, color);
			}
			else {
				if( movingDir == Direction.WEST )
					xLocation -= tileSize;
				//Right
				screen.render(xLocation, yLocation + (i-1)*tileSize, xTile, yTile + (i-1), sheet,
						flip, color);
				//Middle
				screen.render(xLocation + tileSize, yLocation + (i-1)*tileSize, xTile+1, yTile + (i-1), sheet, flip, color);
				//Left
				screen.render(xLocation + 2 * tileSize, yLocation + (i-1) * tileSize, xTile + 2,
						yTile + (i-1), sheet, flip, color);
				if( movingDir == Direction.WEST )
					xLocation += tileSize;
			}
		}
	}
}
