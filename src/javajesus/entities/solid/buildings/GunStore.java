package javajesus.entities.solid.buildings;

import java.awt.Point;

import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.GunStoreInterior;

/*
 * A gunstore front
 */
public class GunStore extends Building {

	// serialization
	private static final long serialVersionUID = 7439780840552868539L;

	/**
	 * Creates a gunstore
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public GunStore(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFABD3FF }, Sprite.gunstore);

		level.add(new Transporter(level, x + 29, y + 26, new GunStoreInterior(new Point(x + 35, y + 37), getLevel())));
	}

}
