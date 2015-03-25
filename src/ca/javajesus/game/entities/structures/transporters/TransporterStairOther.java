package ca.javajesus.game.entities.structures.transporters;

import java.awt.Point;

import ca.javajesus.game.entities.Mob.Direction;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class TransporterStairOther extends Transporter {
	private Direction direction;

	/**
	 * @param direction
	 *            1 for up, 2 for right, 3 for down, 4 for left
	 */
	public TransporterStairOther(Level currentLevel, int x, int y,
			Level nextLevel, Direction direction) {
		super(currentLevel, x, y, nextLevel);
		this.direction = direction;
	}

	/**
	 * @param direction
	 *            1 for up, 2 for right, 3 for down, 4 for left
	 */
	public TransporterStairOther(Level currentLevel, int x, int y,
			Level nextLevel, Point point, Direction direction) {
		super(currentLevel, x, y, nextLevel, point);
		this.direction = direction;
	}

	public void render(Screen screen) {	
		int[] color = { 0xFF1F7901, 0xFF1F4001, 0xFF000000 };
		
		switch (direction) {
		case NORTH:
			screen.render(x + 0, y + 0, 7 + 6 * 312, color, 0, 1, SpriteSheet.tiles);
			screen.render(x + 8, y + 0, 8 + 6 * 32, color, 0, 1, SpriteSheet.tiles);
			break;
		case EAST:
			screen.render(x + 0, y + 0, 7 + 7 * 32, color, 0, 1, SpriteSheet.tiles);
			screen.render(x + 0, y + 8, 7 + 8 * 32, color, 0, 1, SpriteSheet.tiles);
			break;
		case WEST:
			screen.render(x + 0, y + 0, 7 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
			screen.render(x + 8, y + 0, 8 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
			break;
		case SOUTH:
			screen.render(x + 0, y + 0, 7 + 9 * 32, color, 0, 1, SpriteSheet.tiles);
			screen.render(x + 0, y + 8, 7 + 10 * 32, color, 0, 1, SpriteSheet.tiles);
			break;
		}
	}
}
