package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class TransporterCave extends Transporter {

	public TransporterCave(Level currentLevel, double x, double y) {
		super(currentLevel, x, y, Level.randomCave);
		this.color = Colors.get(Colors.fromHex("#474645"),
				Colors.fromHex("#663300"), Colors.fromHex("#472400"),
				Colors.fromHex("#ffde00"));
	}

	public TransporterCave(Level currentLevel, double x, double y, Point point) {
		super(currentLevel, x, y, Level.randomCave, point);
		this.color = Colors.get(Colors.fromHex("#474645"),
				Colors.fromHex("#663300"), Colors.fromHex("#472400"),
				Colors.fromHex("#ffde00"));
	}

	public void render(Screen screen) {
		screen.render(x + 0, y + 0, 4 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 0, y + 8, 4 + 6 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 8, y + 0, 5 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 8, y + 8, 5 + 6 * 32, color, 0, 1, SpriteSheet.tiles);
	}
}