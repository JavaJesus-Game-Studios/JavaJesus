package javajesus.entities.structures.techtopia;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * Tech topia city hall!
 */
public class TechTopiaCityHall extends Building {

	private static final long serialVersionUID = -6355244198834564219L;

	public TechTopiaCityHall(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF283A28, 0xFF1F5C1F, 0xFFABD3FF }, Sprite.techTopia_city_hall,
				SolidEntity.TWO_THIRDS);
		
		this.setBounds(getBounds().x + 19, getBounds().y, getBounds().width - 36, getBounds().height);

		
		level.add(
				new Transporter(level, x + 42, y + 80, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}
}
