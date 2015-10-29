package game.entities.structures.transporters;

import game.graphics.Screen;
import game.graphics.SpriteSheet;

import java.awt.Point;

import level.Level;

public class TransporterLadder extends Transporter {

	private static final long serialVersionUID = 2030814150855374528L;

	public TransporterLadder(Level currentLevel, int x, int y,
			Level nextLevel) {
		super(currentLevel, x, y, nextLevel);
	}

	public TransporterLadder(Level currentLevel, int x, int y,
			Level nextLevel, Point point) {
		super(currentLevel, x, y, nextLevel, point);
	}

	public void render(Screen screen) {
		int[] color = { 0xFF663300, 0xFF663300, 0xFFFFDE00 };
		
		screen.render(x + 0, y - 8, 6 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 0, y + 0, 6 + 6 * 32, color, 0, 1, SpriteSheet.tiles);
	}
}