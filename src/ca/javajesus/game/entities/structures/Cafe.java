package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Colors;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class Cafe extends SolidEntity {

	public Cafe(Level level, int x, int y) {
		super(level, x, y, 100, 59);
		level.addEntity(new Transporter(level, x + 77, y + 43,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
		color = new int[] { Colors.get(-1, 111, Colors.fromHex("#648ca4"),
				Colors.fromHex("#f87a36")) };
	}

	public void render(Screen screen) {
		screen.render(x, y, color, Sprite.cafe);
	}
}
