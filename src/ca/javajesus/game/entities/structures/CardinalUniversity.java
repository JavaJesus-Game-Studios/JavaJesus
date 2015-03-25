package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class CardinalUniversity extends SolidEntity {

	public CardinalUniversity(Level level, int x, int y) {
		super(level, x, y, 200, 56);
		level.addEntity(new Transporter(level, x + 82, y + 40,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
		level.addEntity(new Transporter(level, x + 106, y + 40,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {
		screen.render(x, y, new int[] { 0xFFFF1111, 0xFFFFFFB2, 0xFFFFFFFF }, Sprite.cardinalUniversity);
	}
}
