package javajesus.entities.solid.buildings.techtopia;

import java.awt.Point;

import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.RadarDishInterior;

/*
 * A large round dish
 */
public class RadarDish extends Building {

	// serialization
	private static final long serialVersionUID = 2945216436648329178L;

	/**
	 * Creates a radar dish
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public RadarDish(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFA3A3C2, 0xFF75758C }, Sprite.radardish);

		// change the  bounds
		setBounds(getBounds().x + 6, getBounds().y, getBounds().width - 12, getBounds().height);

		level.add(new Transporter(level, x + 37, y + 117, new RadarDishInterior(new Point(x + 43, y + 125), level)));
	}

}
