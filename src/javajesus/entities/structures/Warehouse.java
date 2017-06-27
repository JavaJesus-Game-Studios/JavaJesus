package javajesus.entities.structures;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.WarehouseInterior;

/*
 * A place for storage
 */
public class Warehouse extends Building {

	private static final long serialVersionUID = 741321813070993409L;

	public Warehouse(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF8D1919, 0xFF545454 }, Sprite.warehouse, SolidEntity.HALF);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(
				new Transporter(level, x + 77, y + 43, new WarehouseInterior(new Point(x + 83, y + 54), level)));
	}

}
