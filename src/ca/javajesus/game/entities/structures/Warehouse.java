package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class Warehouse  extends SolidEntity {

	public Warehouse(Level level, int x, int y) {
		super(level, x, y, 100, 59);
		level.addEntity(new Transporter(level, x + 77, y + 43,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFF8D1919, 0xFF545454 }, Sprite.warehouse);

	}

}
