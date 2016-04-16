package game.entities.structures.transporters;

import game.graphics.Screen;
import game.graphics.SpriteSheet;

import java.awt.Point;

import level.Level;
import level.RandomCave;

public class TransporterCave extends Transporter {

	private static final long serialVersionUID = 3445699890339737978L;

	public TransporterCave(Level currentLevel, int x, int y) {
		super(currentLevel, x, y, new RandomCave(3000,
				3000, 5, currentLevel, new Point(x + 1, y + 10)));
	}

	public TransporterCave(Level currentLevel, int x, int y, Point point) {
		super(currentLevel, x, y,
				new RandomCave(3000, 3000, 5,
						currentLevel, new Point(x + 1, y + 10)), point);
	}

	public TransporterCave(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, nextLevel);
	}

	public void render(Screen screen) {
		int[] color = { 0xFF663300, 0xFF472400, 0xFFFFDE00 };

		screen.render(x + 0, y + 0, 4 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 0, y + 8, 4 + 6 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 8, y + 0, 5 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 8, y + 8, 5 + 6 * 32, color, 0, 1, SpriteSheet.tiles);
	}
}