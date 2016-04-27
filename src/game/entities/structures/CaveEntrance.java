package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.TransporterCave;
import game.graphics.Sprite;
import level.Level;

/*
 * The cave structure front
 */
public class CaveEntrance extends Building {

	private static final long serialVersionUID = -6709208432522451198L;
	
	private static final int[] color = { 0xFF301E01, 0xFF474645, 0xFF000000 };

	public CaveEntrance(Level level, int x, int y) {
		super(level, x, y, color, Sprite.cave_entrance, SolidEntity.QUARTER);
		level.addEntity(new TransporterCave(level, x + 18, y + 20));
	}

	public CaveEntrance(Level level, int x, int y, Point spawn) {
		super(level, x, y, color, Sprite.cave_entrance, SolidEntity.QUARTER);
		level.addEntity(new TransporterCave(level, x + 18, y + 20, spawn));
	}

	public CaveEntrance(Level level, int x, int y, Level nextLevel) {
		super(level, x, y, color, Sprite.cave_entrance, SolidEntity.QUARTER);
		level.addEntity(new TransporterCave(level, x + 18, y + 20, nextLevel));
	}

}
