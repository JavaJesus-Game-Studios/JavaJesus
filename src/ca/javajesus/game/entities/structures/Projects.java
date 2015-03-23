package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class Projects  extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#93da85"),
			Colors.fromHex("#6cb1fe"));

	public Projects(Level level, int x, int y) {
		super(level, x, y, 100, 80);
		level.addEntity(new Transporter(level, x + 44, y + 64,
				new PoorHouseInterior(new Point(x + 45, y + 72), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.projects);

	}

}