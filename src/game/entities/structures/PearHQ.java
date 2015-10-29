package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Colors;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;

public class PearHQ extends SolidEntity {

	public PearHQ(Level level, int x, int y) {
		super(level, x, y, 104, 192);
		level.addEntity(new Transporter(level, x + 46, y + 176,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
		this.color = new int[] { -1, 111, Colors.fromHex("#648ca4"),
				Colors.fromHex("#f87a36") };
	}

	public void render(Screen screen) {
		screen.render((int) x, (int) y, color, Sprite.pear_hq);
	}
}
