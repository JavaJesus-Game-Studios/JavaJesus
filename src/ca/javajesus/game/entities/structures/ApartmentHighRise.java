package ca.javajesus.game.entities.structures;

import java.awt.Rectangle;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;

public class ApartmentHighRise extends SolidEntity {

	public ApartmentHighRise(Level level, int x, int y) {
		super(level, x, y, 71, 222);
		this.shadow = new Rectangle(width, (2 * height / 3));
		this.shadow.setLocation(x, y);
		this.bounds = new Rectangle(width, (height / 3) - 8);
		this.bounds.setLocation(x, y + shadow.height);
		level.addEntity(new Transporter(level, x + 30, y + 206, this.level));
	}

	public void render(Screen screen) {

		screen.render(x, y, new int[] { 0xFF111111, 0xFF673101, 0xFFABD3FF }, Sprite.apartment);

	}

}
