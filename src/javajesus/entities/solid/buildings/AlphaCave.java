package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.AlphaCaveInterior;

/*
 * The cave structure front
 */
public class AlphaCave extends Building {

	// color set
	private static final int[] color = { 0xFF301E01, 0xFF632a06, 0xFF000000 };

	// Instance of the alpha Cave interior, used in the Liberate Church Quest
	public static AlphaCaveInterior interior;

	/**
	 * Creates a cave entrance
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public AlphaCave(Level level, int x, int y) throws IOException {
		super(level, x, y, color, Sprite.cave_entrance);

		// initialize the interior
		interior = new AlphaCaveInterior(new Point(x + 43, y + 167), level);

		if (level != null)
			level.add(new Door(level, x + 18, y + 16, interior, 4, 0));
	}

	@Override
	public byte getId() {
		return Entity.ALPHA_CAVE_ENTRANCE;
	}

}
