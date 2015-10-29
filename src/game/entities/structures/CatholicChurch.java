package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.CatholicChurchInterior;

public class CatholicChurch extends SolidEntity {

	public CatholicChurch(Level level, int x, int y) {
		super(level, x, y, 78, 75);
		level.addEntity(new Transporter(level, x + 38, y + 59,
				new CatholicChurchInterior(new Point(x + 44, y + 70),
						this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] {0xFF111111, 0xFFFFFAB0, 0xFFD30000 }, Sprite.catholic_church);

	}

}
