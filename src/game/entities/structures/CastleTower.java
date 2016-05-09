package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.CastleTowerInterior;

/*
 * The castle arch tower
 */
public class CastleTower extends Building {

	private static final long serialVersionUID = 5113885652722266985L;

	public CastleTower(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF8D8D8D, 0xFFEEFEFF }, Sprite.castle_tower,
				SolidEntity.FIVE_SIXTHS);

		level.add(new Transporter(level, x + 41, y + 159,
				new CastleTowerInterior(new Point(x + 43, y + 167), getLevel())));

	}

}
