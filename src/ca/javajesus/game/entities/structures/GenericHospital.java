package ca.javajesus.game.entities.structures;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Colors;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;

public class GenericHospital extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#ffffff"),
			Colors.fromHex("#496787"));

	public GenericHospital(Level level, int x, int y) {
		super(level, x, y, 100, 80);
		level.addEntity(new Transporter(level, x + 45, y + 64, this.level));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.generic_hospital);

	}

}
