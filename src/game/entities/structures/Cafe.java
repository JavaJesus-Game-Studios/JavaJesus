package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import java.awt.Point;
import level.Level;
import level.interior.CafeInterior;

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

		level.add(
				new Transporter(level, x + 77, y + 43, new CafeInterior(new Point(x + 83, y + 55), getLevel())));
	}

}
