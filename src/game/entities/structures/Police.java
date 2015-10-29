package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;

public class Police  extends SolidEntity {

	public Police(Level level, int x, int y) {
		super(level, x, y, 100, 85);
		level.addEntity(new Transporter(level, x + 45, y + 69,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFFC9C9C9, 0xFF496787 }, Sprite.police_building);

	}

}