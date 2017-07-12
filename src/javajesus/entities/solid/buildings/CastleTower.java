package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.CastleTowerInterior;

/*
 * The castle arch tower
 */
public class CastleTower extends Building {

	// serialization
	private static final long serialVersionUID = 5113885652722266985L;

	/**
	 * Creates a castle tower
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public CastleTower(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF8D8D8D, 0xFFEEFEFF }, Sprite.castle_tower);
		
		// change bounds
		setBounds(getBounds().x + 11, getBounds().y, getBounds().width - 22, getBounds().height);

		level.add(new Transporter(level, x + 41, y + 159,
		        new CastleTowerInterior(new Point(x + 43, y + 167), getLevel())));

	}

}
