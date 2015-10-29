package game.entities.structures;


import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.TippeeInterior;

public class Tippee  extends SolidEntity {

	public Tippee(Level level, int x, int y) {
		super(level, x, y, 32, 50);
		level.addEntity(new Transporter(level, x + 10, y + 34, new TippeeInterior(new Point(x + 16, y + 45), level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF522900, 0xFF977F66, 0xFF335C33 }, Sprite.tippee);

	}

}
