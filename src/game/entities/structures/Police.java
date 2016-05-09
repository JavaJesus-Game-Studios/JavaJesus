package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * A typical police station
 */
public class Police extends Building {

	private static final long serialVersionUID = -8366530842339041365L;

	public Police(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFC9C9C9, 0xFF496787 }, Sprite.police_building, SolidEntity.HALF);
		level.add(
				new Transporter(level, x + 45, y + 69, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

}