package javajesus.entities.structures.sanjuan;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * San Juan City Hall!
 */
public class SanJuanCityHall extends Building {

	private static final long serialVersionUID = 6074562352706611380L;

	public SanJuanCityHall(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF919192, 0xFF4D4DFF }, Sprite.sanJuan_City_Hall,
				SolidEntity.TWO_THIRDS);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 41, getBounds().height);

		
		level.add(
				new Transporter(level, x + 67, y + 104, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}

}
