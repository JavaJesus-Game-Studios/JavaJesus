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

public class RussianClub extends SolidEntity {

	public RussianClub(Level level, int x, int y) {
		super(level, x, y, 100, 61);
		level.addEntity(new Transporter(level, x + 38, y + 45, 
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
		level.addEntity(new Transporter(level, x + 51, y + 45, this.level));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFFFFEB0A, 0xFF80004B, 0xFFE934F9 }, Sprite.russian_club);

	}

}
