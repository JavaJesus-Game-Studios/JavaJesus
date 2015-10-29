package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.ProjectsLobby;

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