package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;

/*
 * A large skyscraper
 */
public class ModernSkyscraper extends Building {

	private static final long serialVersionUID = -9106308683773089538L;

	public ModernSkyscraper(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF182D42, 0xFF3E89D5 }, Sprite.modern_skyscraper,
				SolidEntity.SEVEN_EIGTHS);
		level.addEntity(new Transporter(level, x + 43, y + 235, getLevel()));
	}

}
