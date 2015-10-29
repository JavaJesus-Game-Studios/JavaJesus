package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.TransporterCave;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;

public class CaveEntrance extends SolidEntity {

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

		screen.render((int) x, (int) y, new int[] { 0xFF301E01, 0xFF474645, 0xFF000000 }, Sprite.cave_entrance);

	}

}
