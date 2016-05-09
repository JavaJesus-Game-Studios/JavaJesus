package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * The school in sequoia
 */
public class SequoiaSchool extends Building {

	private static final long serialVersionUID = 4874810669481482545L;

	public SequoiaSchool(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF8D1919, 0xFF4D4DFF }, Sprite.sequoiaSchool,
				SolidEntity.TWO_THIRDS);
		level.add(
				new Transporter(level, x + 60, y + 99, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}

}
