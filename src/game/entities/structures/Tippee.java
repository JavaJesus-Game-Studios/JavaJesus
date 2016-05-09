package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.TippeeInterior;

/*
 * It looks like tippee is spelled wrong
 */
public class Tippee extends Building {

	private static final long serialVersionUID = 5182709078376633434L;

	public Tippee(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF522900, 0xFF977F66, 0xFF335C33 }, Sprite.tippee, SolidEntity.QUARTER);
		level.add(new Transporter(level, x + 10, y + 34, new TippeeInterior(new Point(x + 16, y + 45), level)));
	}

}
