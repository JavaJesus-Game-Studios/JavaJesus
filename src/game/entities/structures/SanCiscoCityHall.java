package game.entities.structures;

import level.Level;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

public class SanCiscoCityHall extends SolidEntity {

	public SanCiscoCityHall(Level level, int x, int y) {
		super(level, x, y, 192, 120);
		level.addEntity(new Transporter(level, x + 90, y + 104, this.level));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFBC02 }, Sprite.san_cisco_city_hall);

	}

}