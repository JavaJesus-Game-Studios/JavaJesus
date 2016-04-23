package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
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

		level.addEntity(
				new Transporter(level, x + 82, y + 40, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
		level.addEntity(
				new Transporter(level, x + 106, y + 40, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}
}
