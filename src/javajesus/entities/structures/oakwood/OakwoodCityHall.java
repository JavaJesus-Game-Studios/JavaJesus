package javajesus.entities.structures.oakwood;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * City hall of Oakwood
 */
public class OakwoodCityHall extends Building {

	private static final long serialVersionUID = 1092805299412039212L;

	public OakwoodCityHall(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFFF99 }, Sprite.oakwoodcityhall, SolidEntity.TWO_THIRDS);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 48, y + 104,
				new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

}
