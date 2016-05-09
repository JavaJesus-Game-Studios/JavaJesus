package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * Entrance to the mineshaft
 */
public class MineShaft extends Building {

	private static final long serialVersionUID = -1803480550218050753L;

	public MineShaft(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF3B312A, 0xFFFFEA5D }, Sprite.mineshaft, SolidEntity.QUARTER);
		level.add(
				new Transporter(level, x + 79, y + 48, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

}