package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;

public class TechTopiaCityHall extends SolidEntity {

	public TechTopiaCityHall(Level level, int x, int y) {
		super(level, x, y, 96, 96);
		level.addEntity(new Transporter(level, x + 42, y + 80,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {
		screen.render((int) x, (int) y, new int[] { 0xFF283A28, 0xFF1F5C1F, 0xFFABD3FF }, Sprite.techTopia_city_hall);
	}
}
