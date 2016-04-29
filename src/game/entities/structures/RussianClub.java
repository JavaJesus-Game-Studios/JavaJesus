package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * A Russian Club
 */
public class RussianClub extends Building {

	private static final long serialVersionUID = 6477494248033598079L;

	public RussianClub(Level level, int x, int y) {
		super(level, x, y,  new int[] { 0xFFFFEB0A, 0xFF80004B, 0xFFE934F9 }, Sprite.russian_club, SolidEntity.TWO_THIRDS);
		level.addEntity(new Transporter(level, x + 38, y + 45, 
				new PoorHouseInterior(new Point(x + 40, y + 67), level)));
		level.addEntity(new Transporter(level, x + 51, y + 45, level));
	}

}
