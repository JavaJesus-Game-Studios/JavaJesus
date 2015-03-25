package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Colors;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class Police  extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#c9c9c9"),
			Colors.fromHex("#496787"));

	public Police(Level level, int x, int y) {
		super(level, x, y, 100, 85);
		level.addEntity(new Transporter(level, x + 45, y + 69,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.police_building);

	}

}