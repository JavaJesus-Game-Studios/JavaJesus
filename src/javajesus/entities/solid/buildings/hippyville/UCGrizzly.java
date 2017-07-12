package javajesus.entities.solid.buildings.hippyville;

import java.awt.Point;

import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.UCGrizzlyInterior;

/*
 * Better than UC berkeley
 */
public class UCGrizzly extends Building {

	// serialization
	private static final long serialVersionUID = 1258793771122138267L;

	/**
	 * Creates a school
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public UCGrizzly(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFB2, 0xFF6D6D61 }, Sprite.grizzly);

		level.add(new Transporter(level, x + 82, y + 45, new UCGrizzlyInterior(new Point(x + 88, y + 57), level)));
		level.add(new Transporter(level, x + 106, y + 45, new UCGrizzlyInterior(new Point(x + 112, y + 57), level)));
	}
}
