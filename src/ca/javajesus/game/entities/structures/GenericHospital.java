package ca.javajesus.game.entities.structures;

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

public class GenericHospital extends SolidEntity {

	public GenericHospital(Level level, int x, int y) {
		super(level, x, y, 100, 80);
		level.addEntity(new Transporter(level, x + 45, y + 64, this.level));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFF496787 }, Sprite.generic_hospital);

	}

}
