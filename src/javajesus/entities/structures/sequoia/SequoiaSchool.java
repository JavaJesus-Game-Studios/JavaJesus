package javajesus.entities.structures.sequoia;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
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
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(
				new Transporter(level, x + 60, y + 99, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}

}
