package ca.javajesus.game.entities.structures.transporters;

import java.awt.Point;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;
import ca.javajesus.level.RandomCave;

public class TransporterCave extends Transporter {

	public TransporterCave(Level currentLevel, int x, int y) {
		super(currentLevel, x, y, new RandomCave(Level.level1.width,
				Level.level1.height, 5, currentLevel, new Point(x + 1, y + 10)));
	}

	public TransporterCave(Level currentLevel, int x, int y, Point point) {
		super(currentLevel, x, y,
				new RandomCave(Level.level1.width, Level.level1.height, 5,
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