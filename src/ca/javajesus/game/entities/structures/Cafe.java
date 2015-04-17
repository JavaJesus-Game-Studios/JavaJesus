package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.CafeInterior;
import ca.javajesus.level.interior.PoorHouseInterior;

public class Cafe extends SolidEntity {

	public Cafe(Level level, int x, int y) {
		super(level, x, y, 100, 59);
		level.addEntity(new Transporter(level, x + 77, y + 43,
				new CafeInterior(new Point(x + 83, y + 55), this.level)));
	}

	public void render(Screen screen) {
		screen.render(x, y, new int[] { 0xFF111111, 0xFFFFFF99, 0xFFFFFFCC }, Sprite.cafe);
	}
}
