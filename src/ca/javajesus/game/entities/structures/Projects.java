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
import ca.javajesus.level.interior.ProjectsLobby;

public class Projects  extends SolidEntity {

	public Projects(Level level, int x, int y) {
		super(level, x, y, 100, 80);
		level.addEntity(new Transporter(level, x + 44, y + 64,
				new ProjectsLobby(new Point(x + 45, y + 73), level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] {0xFF111111, 0xFF93DA85, 0xFF6CB1FE }, Sprite.projects);

	}

}