package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class MineShaft extends SolidEntity {

	public MineShaft(Level level, int x, int y) {
		super(level, x, y, 120, 64);
		level.addEntity(new Transporter(level, x + 79, y + 48,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFF3B312A, 0xFFFFEA5D }, Sprite.mineshaft);

	}

}