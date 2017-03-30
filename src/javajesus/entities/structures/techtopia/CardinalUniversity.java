package javajesus.entities.structures.techtopia;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * Parody of Stanford
 */
public class CardinalUniversity extends Building {

	private static final long serialVersionUID = 8968241141418375189L;

	/**
	 * Creates Cardinal University
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public CardinalUniversity(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFFFF1111, 0xFFFFFFB2, 0xFFFFFFFF }, Sprite.cardinalUniversity,
				SolidEntity.TWO_THIRDS);

		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(
				new Transporter(level, x + 82, y + 40, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
		level.add(
				new Transporter(level, x + 106, y + 40, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}
}
