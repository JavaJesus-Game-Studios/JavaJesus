package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.CatholicChurchInterior;

/*
 * A bigger place to pray!
 */
public class CatholicChurch extends Building {

	private static final long serialVersionUID = -3808305570492236904L;

	public CatholicChurch(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFD30000 }, Sprite.catholic_church, SolidEntity.HALF);
		level.add(new Transporter(level, x + 38, y + 59,
				new CatholicChurchInterior(new Point(x + 44, y + 70), getLevel())));
	}

}
