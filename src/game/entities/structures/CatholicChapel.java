package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.CatholicChapelInterior;

public class CatholicChapel extends SolidEntity {

	public CatholicChapel(Level level, int x, int y) {
		super(level, x, y, 54, 63);
		level.addEntity(new Transporter(level, x + 21, y + 47,
				new CatholicChapelInterior(new Point(x + 27, y + 57),
						this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFABD3FF }, Sprite.catholic_chapel);

	}

}
