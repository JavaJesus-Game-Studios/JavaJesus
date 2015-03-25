package ca.javajesus.game.entities.structures;

import java.awt.Rectangle;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
<<<<<<< HEAD
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
=======
import ca.javajesus.game.graphics.Colors;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
>>>>>>> origin/master
import ca.javajesus.level.Level;

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
