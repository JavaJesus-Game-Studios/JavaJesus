package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.HutInterior;

/*
 * A small hut
 */
public class Hut extends Building {

	// serialization
	private static final long serialVersionUID = -2327932586773802554L;

	/**
	 * Creates a hut
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public Hut(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF654000, 0xFF814700, 0xFFFFEA00 }, Sprite.hut_exterior);

		level.add(new Transporter(level, x + 18, y + 32, new HutInterior(new Point(x + 24, y + 43), getLevel())));
	}

}
