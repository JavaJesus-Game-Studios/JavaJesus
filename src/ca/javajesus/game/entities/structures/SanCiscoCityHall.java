package ca.javajesus.game.entities.structures;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;

public class SanCiscoCityHall extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#ffffff"),
			Colors.fromHex("#ffbc02"));

	public SanCiscoCityHall(Level level, int x, int y) {
		super(level, x, y, 192, 120);
		level.addEntity(new Transporter(level, x + 90, y + 104, this.level));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.san_cisco_city_hall);

	}

}