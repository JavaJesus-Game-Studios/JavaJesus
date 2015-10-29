package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;

public class CardinalUniversity extends SolidEntity {

	private static final long serialVersionUID = 8968241141418375189L;

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
