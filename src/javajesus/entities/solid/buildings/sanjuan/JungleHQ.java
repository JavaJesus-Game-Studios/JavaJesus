package javajesus.entities.solid.buildings.sanjuan;

import java.awt.Point;

import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * The head quarters for the jungle place
 */
public class JungleHQ extends Building {

	// serialization
	private static final long serialVersionUID = -3217186313954641819L;

	/**
	 * Creates a jungle hq
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public JungleHQ(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF248F24, 0xFF4D4DFF }, Sprite.jungle_hq);

		level.add(new Transporter(level, x + 61, y + 80, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
		level.add(new Transporter(level, x + 75, y + 80, new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

}
