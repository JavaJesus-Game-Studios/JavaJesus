package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.RancheroHouseInterior;

/*
 * Typical ranchero house
 */
public class RancheroHouse extends Building {

	private static final long serialVersionUID = -5896010603184934105L;

	public RancheroHouse(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFD30000 }, Sprite.ranchero_house, SolidEntity.HALF);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 44, y + 45,
				new RancheroHouseInterior(new Point(x + 50, y + 56),
						level)));
	}

}
