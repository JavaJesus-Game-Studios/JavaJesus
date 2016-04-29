package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.RussianChurchInterior;

/*
 * A russian church
 */
public class RussianOrthodoxChurch extends Building {

	private static final long serialVersionUID = 4401142269501974132L;

	public RussianOrthodoxChurch(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF0069AC, 0xFFFFBC02 }, Sprite.russian_orthodox_church,
				SolidEntity.TWO_THIRDS);

		level.addEntity(
				new Transporter(level, x + 43, y + 64, new RussianChurchInterior(new Point(x + 49, y + 75), level)));

	}

}
