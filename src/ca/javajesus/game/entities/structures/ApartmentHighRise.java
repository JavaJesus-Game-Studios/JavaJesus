package ca.javajesus.game.entities.structures;

import java.awt.Rectangle;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Colors;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;

public class ApartmentHighRise extends SolidEntity {

	public ApartmentHighRise(Level level, int x, int y) {
		super(level, x, y, 71, 222);
		this.shadow = new Rectangle(width, (2 * height / 3));
		this.shadow.setLocation(x, y);
		this.bounds = new Rectangle(width, (height / 3) - 8);
		this.bounds.setLocation(x, y + shadow.height);
		level.addEntity(new Transporter(level, x + 30, y + 206, this.level));
		this.color = new int[] { 111, Colors.fromHex("#673101"),
				Colors.fromHex("#abd3ff") };
	}

	public void render(Screen screen) {

		screen.render(x, y, color, Sprite.apartment);

	}

}
