package javajesus.entities.solid.buildings;

import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * A large skyscraper
 */
public class ModernSkyscraper extends Building {

	// serialization
	private static final long serialVersionUID = -9106308683773089538L;

	/**
	 * Creates a skyscraper
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public ModernSkyscraper(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF182D42, 0xFF3E89D5 }, Sprite.modern_skyscraper);

		level.add(new Transporter(level, x + 43, y + 235, getLevel()));
	}

}
