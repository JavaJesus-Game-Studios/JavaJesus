package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;
import level.interior.UCGrizzlyInterior;

public class UCGrizzly extends SolidEntity {

	public UCGrizzly(Level level, int x, int y) {
		super(level, x, y, 200, 61);
		level.addEntity(new Transporter(level, x + 82, y + 45,
				new UCGrizzlyInterior(new Point(x + 88, y + 57), this.level)));
		level.addEntity(new Transporter(level, x + 106, y + 45,
				new UCGrizzlyInterior(new Point(x + 112, y + 57), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFFFFFFB2, 0xFF6D6D61 }, Sprite.grizzly);

	}
}
