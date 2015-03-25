package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
<<<<<<< HEAD
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
=======
import ca.javajesus.game.graphics.Colors;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
>>>>>>> origin/master
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class QuackerHQ extends SolidEntity {

	public QuackerHQ(Level level, int x, int y) {
		super(level, x, y, 136, 99);
		level.addEntity(new Transporter(level, x + 53, y + 83,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] {0xFF111111, 0xFFFFFFFF, 0xFF4D4DFF }, Sprite.quacker_hq);

	}
}
