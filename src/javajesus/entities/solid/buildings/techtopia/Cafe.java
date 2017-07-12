package javajesus.entities.solid.buildings.techtopia;

import java.awt.Point;

import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.CafeInterior;

/*
 * A Cafe in tech topia
 */
public class Cafe extends Building {

	// serialization
	private static final long serialVersionUID = 1292938033962226188L;

	/**
	 * Creates a cafe
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public Cafe(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFF99, 0xFFFFFFCC }, Sprite.cafe);

		level.add(new Transporter(level, x + 77, y + 43, new CafeInterior(new Point(x + 83, y + 55), getLevel())));
	}

}
