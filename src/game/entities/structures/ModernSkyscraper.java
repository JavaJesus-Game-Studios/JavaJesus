package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Rectangle;

import level.Level;

public class ModernSkyscraper extends SolidEntity {

	public ModernSkyscraper(Level level, int x, int y) {
		super(level, x, y, 97, 251);
		this.shadow = new Rectangle(width, (2 * height / 3));
		this.shadow.setLocation(x, y);
		this.bounds = new Rectangle(width, (height / 3) - 8);
		this.bounds.setLocation(x, y + shadow.height);
		level.addEntity(new Transporter(level, x + 43, y + 235, this.level));
	}

	public void render(Screen screen) {

		screen.render(x, y, new int[] { 0xFF111111, 0xFF182D42, 0xFF3E89D5 }, Sprite.modern_skyscraper);

	}

}
