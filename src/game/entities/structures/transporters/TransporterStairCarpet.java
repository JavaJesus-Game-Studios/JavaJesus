package game.entities.structures.transporters;

import java.awt.Point;

import game.graphics.Screen;
import game.graphics.SpriteSheet;
import level.Level;
import utility.Direction;

public class TransporterStairCarpet extends Transporter {
	
	private static final long serialVersionUID = 6585053529821344131L;
	
	private Direction direction;

	/**
	 * @param direction
	 *            1 for up, 2 for right, 3 for down, 4 for left
	 */
	public TransporterStairCarpet(Level currentLevel, int x, int y,
			Level nextLevel, Direction direction) {
		super(currentLevel, x, y, nextLevel);
		this.direction = direction;
	}

	/**
	 * @param direction
	 *            1 for up, 2 for right, 3 for down, 4 for left
	 */
	public TransporterStairCarpet(Level currentLevel, int x, int y,
			Level nextLevel, Point point, Direction direction) {
		super(currentLevel, x, y, nextLevel, point);
		this.direction = direction;
	}

	public void render(Screen screen) {	
		int[] color = { 0xFF111111, 0xFF704200, 0xFF1F7901 };
		
		switch (direction) {
		case NORTH:
			screen.render(x + 0, y + 0, 9 + 6 * 312, color, 0, 1, SpriteSheet.tiles);
			screen.render(x + 8, y + 0, 10 + 6 * 32, color, 0, 1, SpriteSheet.tiles);
			break;
		case EAST:
			screen.render(x + 0, y + 0, 9 + 7 * 32, color, 0, 1, SpriteSheet.tiles);
			screen.render(x + 0, y + 8, 9 + 8 * 32, color, 0, 1, SpriteSheet.tiles);
			break;
		case SOUTH:
			screen.render(x + 0, y + 0, 9 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
			screen.render(x + 8, y + 0, 10 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
			break;
		case WEST:
			screen.render(x + 0, y + 0, 9 + 9 * 32, color, 0, 1, SpriteSheet.tiles);
			screen.render(x + 0, y + 8, 9 + 10 * 32, color, 0, 1, SpriteSheet.tiles);
			break;
		}
	}
}
