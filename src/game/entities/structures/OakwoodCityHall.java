package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * City hall of Oakwood
 */
public class OakwoodCityHall extends Building {

	private static final long serialVersionUID = 1092805299412039212L;

	public OakwoodCityHall(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFFF99 }, Sprite.oakwoodcityhall, SolidEntity.TWO_THIRDS);
		level.addEntity(new Transporter(level, x + 48, y + 104,
				new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

}
