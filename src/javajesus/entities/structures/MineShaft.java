package javajesus.entities.structures;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * Entrance to the mineshaft
 */
public class MineShaft extends Building {

	private static final long serialVersionUID = -1803480550218050753L;

	public MineShaft(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF3B312A, 0xFFFFEA5D }, Sprite.mineshaft, SolidEntity.QUARTER);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(
				new Transporter(level, x + 79, y + 48, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

}