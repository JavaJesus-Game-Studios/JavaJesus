package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class TechTopiaCityHall extends SolidEntity {

	public TechTopiaCityHall(Level level, int x, int y) {
		super(level, x, y, 96, 96);
		level.addEntity(new Transporter(level, x + 42, y + 80,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {
		screen.render((int) x, (int) y, new int[] { 0xFF283A28, 0xFF1F5C1F, 0xFF000000 }, Sprite.techTopia_city_hall);
	}
}
