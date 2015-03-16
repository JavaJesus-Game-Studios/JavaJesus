package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;

public class CaveEntrance extends SolidEntity {

	protected int color = Colors.get(-1, Colors.fromHex("#301e01"),
			Colors.fromHex("#474645"), -1);

	public CaveEntrance(Level level, int x, int y) {
		super(level, x, y, 40, 36);
		level.addEntity(new TransporterCave(level, x + 18, y + 20));
	}

	public CaveEntrance(Level level, int x, int y, Point spawn) {
		super(level, x, y, 40, 36);
		level.addEntity(new TransporterCave(level, x + 18, y + 20, spawn));
	}

	public CaveEntrance(Level level, int x, int y, Level nextLevel) {
		super(level, x, y, 40, 36);
		level.addEntity(new TransporterCave(level, x + 18, y + 20, nextLevel));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.cave_entrance);

	}

}
