package javajesus.entities.solid.buildings.hippyville;

import java.awt.Point;

import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * A large tree house
 */
public class GreatTree extends Building {

	// serialization
	private static final long serialVersionUID = -2057500563473932212L;

	/**
	 * Creates a great tree
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public GreatTree(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF143914, 0xFF522900 }, Sprite.greattree);

		// change bounds
		setBounds(getBounds().x + 13, getBounds().y, getBounds().width - 26, getBounds().height);

		level.add(new Transporter(level, x + 22, y + 30, new PoorHouseInterior(new Point(x + 28, y + 42), getLevel())));
	}

}
