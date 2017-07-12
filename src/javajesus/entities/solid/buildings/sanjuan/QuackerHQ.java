package javajesus.entities.solid.buildings.sanjuan;

import java.awt.Point;

import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * Quack Quack
 */
public class QuackerHQ extends Building {

	// serialization
	private static final long serialVersionUID = 435091821654383254L;

	/**
	 * Creates a quacker hq
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public QuackerHQ(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFF4D4DFF }, Sprite.quacker_hq);

		// change the bounds
		setBounds(getBounds().x + 21, getBounds().y, getBounds().width - 58, getBounds().height);

		level.add(new Transporter(level, x + 53, y + 83, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}
}
