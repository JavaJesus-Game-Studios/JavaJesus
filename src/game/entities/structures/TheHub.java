package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * Who does not know what TheHub is?
 */
public class TheHub extends Building {

	private static final long serialVersionUID = -1995931044777514462L;

	public TheHub(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF86868D, 0xFF4D4DFF }, Sprite.theHub, SolidEntity.FIVE_SIXTHS);
		level.addEntity(
				new Transporter(level, x + 46, y + 148, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}

}
