package javajesus.entities.structures.techtopia;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * Tech headquarters
 */
public class PearHQ extends Building {

	private static final long serialVersionUID = -6572045770846891724L;

	public PearHQ(Level level, int x, int y) {
		super(level, x, y, new int[] { -1, 111, 0xFF648ca4, 0xFFf87a36 }, Sprite.pear_hq,
				SolidEntity.TWO_THIRDS);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(
				new Transporter(level, x + 46, y + 176, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}
}
