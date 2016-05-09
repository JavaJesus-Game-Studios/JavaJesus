package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * A large factory
 */
public class Factory extends Building {

	private static final long serialVersionUID = -3123875678253174894L;

	public Factory(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF3B312A, 0xFF002244 }, Sprite.factory, SolidEntity.TWO_THIRDS);
		level.add(
				new Transporter(level, x + 45, y + 69, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

}
