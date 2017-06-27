package javajesus.entities.structures;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * A typical police station
 */
public class Police extends Building {

	private static final long serialVersionUID = -8366530842339041365L;

	public Police(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFC9C9C9, 0xFF496787 }, Sprite.police_building, SolidEntity.HALF);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(
				new Transporter(level, x + 45, y + 69, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

}