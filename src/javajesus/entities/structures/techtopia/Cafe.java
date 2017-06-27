package javajesus.entities.structures.techtopia;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.CafeInterior;

import java.awt.Point;

public class Cafe extends Building {

	private static final long serialVersionUID = 1292938033962226188L;

	/**
	 * Creates a Cafe
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public Cafe(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFF99, 0xFFFFFFCC }, Sprite.cafe, SolidEntity.TWO_THIRDS);

		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(
				new Transporter(level, x + 77, y + 43, new CafeInterior(new Point(x + 83, y + 55), getLevel())));
	}

}
