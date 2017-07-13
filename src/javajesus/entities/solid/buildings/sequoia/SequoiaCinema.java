package javajesus.entities.solid.buildings.sequoia;

import java.awt.Point;

import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * A snazzy cinema
 */
public class SequoiaCinema extends Building {

	// serialization
	private static final long serialVersionUID = -7034832823882430032L;

	/**
	 * Creates a sequoiac cinema
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public SequoiaCinema(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF8D1919, 0xFF4D4DFF }, Sprite.sequoiaCinema);

		// change the bounds
		setBounds(getBounds().x + 14, getBounds().y, getBounds().width - 14, getBounds().height);

		level.add(new Transporter(level, x + 59, y + 99, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}
}