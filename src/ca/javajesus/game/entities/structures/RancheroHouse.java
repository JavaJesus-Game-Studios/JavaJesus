package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.CatholicChurchInterior;

public class RancheroHouse extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#fffab0"),
			Colors.fromHex("#d30000"));

	public RancheroHouse(Level level, int x, int y) {
		super(level, x, y, 100, 61);
		level.addEntity(new Transporter(level, x + 44, y + 45,
				new CatholicChurchInterior(new Point(x + 40, y + 67),
						this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.ranchero_house);

	}

}
