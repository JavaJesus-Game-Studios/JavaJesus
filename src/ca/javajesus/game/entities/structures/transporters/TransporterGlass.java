package ca.javajesus.game.entities.structures.transporters;

import java.awt.Point;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class TransporterGlass extends Transporter {
	

	public TransporterGlass(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, nextLevel);
	}
	
	public TransporterGlass(Level currentLevel, int x, int y, Level nextLevel, Point point) {
		super(currentLevel, x, y, nextLevel, point);
	}

	public void render(Screen screen) {
		int[] color = { 0xFF111111, 0xFF00BAFF, 0xFFFFDE00 };
		
		screen.render(x + 0, y + 0, 0 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 8, y + 0, 1 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 0, y + 8, 0 + 6 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 8, y + 8, 1 + 6 * 32, color, 0, 1, SpriteSheet.tiles);
	}

}
