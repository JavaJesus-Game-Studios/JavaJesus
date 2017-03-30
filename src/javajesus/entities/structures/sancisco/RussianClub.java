package javajesus.entities.structures.sancisco;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * A Russian Club
 */
public class RussianClub extends Building {

	private static final long serialVersionUID = 6477494248033598079L;

	public RussianClub(Level level, int x, int y) {
		super(level, x, y,  new int[] { 0xFFFFEB0A, 0xFF80004B, 0xFFE934F9 }, Sprite.russian_club, SolidEntity.TWO_THIRDS);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 38, y + 45, 
				new PoorHouseInterior(new Point(x + 40, y + 67), level)));
		level.add(new Transporter(level, x + 51, y + 45, level));
	}

}
