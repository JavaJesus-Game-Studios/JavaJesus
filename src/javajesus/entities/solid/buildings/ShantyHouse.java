package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.ShantyHouseInterior;

/*
 * a small run down house
 */
public class ShantyHouse extends Building {

	// serialization
	private static final long serialVersionUID = -2605055003316533697L;

	public ShantyHouse(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFADADAD, 0xFF000000 }, Sprite.shantyhouse);

		level.add(new Transporter(level, x + 12, y + 31, new ShantyHouseInterior(new Point(x + 18, y + 42), level)));
	}
}
