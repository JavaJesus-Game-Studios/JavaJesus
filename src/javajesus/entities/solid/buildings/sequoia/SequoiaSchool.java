package javajesus.entities.solid.buildings.sequoia;

import java.awt.Point;

import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * The school in sequoia
 */
public class SequoiaSchool extends Building {

	// serialization
	private static final long serialVersionUID = 4874810669481482545L;

	/**
	 * Creates a sequoia school
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public SequoiaSchool(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF8D1919, 0xFF4D4DFF }, Sprite.sequoiaSchool);

		level.add(new Transporter(level, x + 60, y + 99, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}

}