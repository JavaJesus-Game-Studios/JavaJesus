package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.RussianChurchInterior;

/*
 * A Russian church
 */
public class RussianOrthodoxChurch extends Building {

	// serialization
	private static final long serialVersionUID = 4401142269501974132L;

	/**
	 * Creates a Russian church
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public RussianOrthodoxChurch(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF0069AC, 0xFFFFBC02 }, Sprite.russian_orthodox_church);

		// change the bounds
		setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 43, y + 64, new RussianChurchInterior(new Point(x + 49, y + 75), level)));

	}

}
