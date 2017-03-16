package game.entities.structures.sanjuan;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.Building;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * The head quarters for the jungle place
 */
public class JungleHQ extends Building {

	private static final long serialVersionUID = -3217186313954641819L;

	public JungleHQ(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF248F24, 0xFF4D4DFF }, Sprite.jungle_hq, SolidEntity.TWO_THIRDS);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 61, y + 80,
				new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
		level.add(new Transporter(level, x + 75, y + 80,
				new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}
	
}
