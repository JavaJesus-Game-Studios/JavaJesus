package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class ChinatownHouse extends SolidEntity {

	protected int[] color = {Colors.get(-1, Colors.fromHex("#618249"), Colors.fromHex("#992b2b"),
			Colors.fromHex("#ffffff"))};

	public ChinatownHouse(Level level, int x, int y) {
		super(level, x, y, 64, 57);
		level.addEntity(new Transporter(level, x + 26, y + 41,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.chinatown_house);

	}

}
