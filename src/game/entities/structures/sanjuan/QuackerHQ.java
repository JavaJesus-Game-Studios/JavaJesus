package game.entities.structures.sanjuan;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.Building;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * Quack Quack
 */
public class QuackerHQ extends Building {

	private static final long serialVersionUID = 435091821654383254L;

	public QuackerHQ(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFF4D4DFF }, Sprite.quacker_hq, SolidEntity.TWO_THIRDS);
		
		this.setBounds(getBounds().x + 25, getBounds().y, getBounds().width - 66, getBounds().height);

		
		level.add(
				new Transporter(level, x + 53, y + 83, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}
}
