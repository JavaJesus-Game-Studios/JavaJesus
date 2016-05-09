package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * Exactly what its name is
 */
public class WeirdTechBuilding2 extends Building {

	private static final long serialVersionUID = 2759908163488508097L;

	public WeirdTechBuilding2(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF919192, 0xFF4D4DFF }, Sprite.weirdTechBuilding2,
				SolidEntity.TWO_THIRDS);
		level.add(
				new Transporter(level, x + 17, y + 80, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}

}
