package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.CatholicChapelInterior;

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
