package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.RefugeeTentInterior;

/*
 * A small tent
 */
public class RefugeeTent  extends Building {

	private static final long serialVersionUID = 2510389595988201303L;

	public RefugeeTent(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFF8FA8F, 0xFF000000 }, Sprite.refugee_tent, SolidEntity.QUARTER);
		level.addEntity(new Transporter(level, x + 34, y + 7,
				new RefugeeTentInterior(new Point(x + 40, y + 18), level)));
	}

}
