package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class OakwoodCityHall extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#648ca4"),
			Colors.fromHex("#f87a36"));
	
	public OakwoodCityHall(Level level, int x, int y) {
		super(level, x, y, 108, 120);
		level.addEntity(new Transporter(level, x + 48, y + 104,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.oakwoodcityhall);

	}

}
