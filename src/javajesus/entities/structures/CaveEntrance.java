package javajesus.entities.structures;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.transporters.TransporterCave;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * The cave structure front
 */
public class CaveEntrance extends Building {

	private static final long serialVersionUID = -6709208432522451198L;
	
	private static final int[] color = { 0xFF301E01, 0xFF474645, 0xFF000000 };

	public CaveEntrance(Level level, int x, int y) {
		super(level, x, y, color, Sprite.cave_entrance, SolidEntity.QUARTER);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new TransporterCave(level, x + 18, y + 20));
	}

	public CaveEntrance(Level level, int x, int y, Point spawn) {
		super(level, x, y, color, Sprite.cave_entrance, SolidEntity.QUARTER);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new TransporterCave(level, x + 18, y + 20, spawn));
	}

	public CaveEntrance(Level level, int x, int y, Level nextLevel) {
		super(level, x, y, color, Sprite.cave_entrance, SolidEntity.QUARTER);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new TransporterCave(level, x + 18, y + 20, nextLevel));
	}

}
