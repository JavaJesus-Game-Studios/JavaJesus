package javajesus.entities.structures;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import level.Level;

/*
 * A large skyscraper
 */
public class ModernSkyscraper extends Building {

	private static final long serialVersionUID = -9106308683773089538L;

	public ModernSkyscraper(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF182D42, 0xFF3E89D5 }, Sprite.modern_skyscraper,
				SolidEntity.SEVEN_EIGTHS);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 43, y + 235, getLevel()));
	}

}
