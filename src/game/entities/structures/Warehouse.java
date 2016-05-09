package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.WarehouseInterior;

/*
 * A place for storage
 */
public class Warehouse extends Building {

	private static final long serialVersionUID = 741321813070993409L;

	public Warehouse(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF8D1919, 0xFF545454 }, Sprite.warehouse, SolidEntity.HALF);
		level.add(
				new Transporter(level, x + 77, y + 43, new WarehouseInterior(new Point(x + 83, y + 54), level)));
	}

}
