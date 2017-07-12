package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.WarehouseInterior;

/*
 * A place for storage
 */
public class Warehouse extends Building {

	// serialization
	private static final long serialVersionUID = 741321813070993409L;

	/**
	 * Creates a warehouse
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public Warehouse(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF8D1919, 0xFF545454 }, Sprite.warehouse);

		level.add(new Transporter(level, x + 77, y + 43, new WarehouseInterior(new Point(x + 83, y + 54), level)));
	}

}
