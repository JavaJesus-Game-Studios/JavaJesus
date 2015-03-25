package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.CatholicChurchInterior;

public class CatholicChurch extends SolidEntity {

	public CatholicChurch(Level level, int x, int y) {
		super(level, x, y, 78, 75);
		level.addEntity(new Transporter(level, x + 38, y + 59,
				new CatholicChurchInterior(new Point(x + 40, y + 67),
						this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] {0xFF111111, 0xFFFFFAB0, 0xFFD30000 }, Sprite.catholic_church);

	}

}
