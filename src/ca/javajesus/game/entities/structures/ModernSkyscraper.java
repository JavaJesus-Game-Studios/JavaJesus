package ca.javajesus.game.entities.structures;

import java.awt.Rectangle;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Colors;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;

public class ModernSkyscraper extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#182d42"),
			Colors.fromHex("#3e89d5"));

	public ModernSkyscraper(Level level, int x, int y) {
		super(level, x, y, 97, 251);
		this.shadow = new Rectangle(width, (2 * height / 3));
		this.shadow.setLocation(x, y);
		this.bounds = new Rectangle(width, (height / 3) - 8);
		this.bounds.setLocation(x, y + shadow.height);
		level.addEntity(new Transporter(level, x + 43, y + 235, this.level));
	}

	public void render(Screen screen) {

		screen.render(x, y, color, Sprite.modern_skyscraper);

	}

}
