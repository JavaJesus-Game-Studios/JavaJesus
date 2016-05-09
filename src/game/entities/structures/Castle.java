package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import java.awt.Point;
import level.Level;
import level.interior.CastleInterior;

/*
 *  Hillsborough's castle
 */
public class Castle extends Building {

	private static final long serialVersionUID = -5648283117813621970L;

	public Castle(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF8D8D8D, 0xFFEEFEFF }, Sprite.castle, SolidEntity.FIVE_SIXTHS);

		level.add(
				new Transporter(level, x + 154, y + 160, new CastleInterior(new Point(x + 43, y + 167), getLevel())));

	}

}
