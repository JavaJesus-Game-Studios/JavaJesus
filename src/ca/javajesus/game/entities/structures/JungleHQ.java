package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class JungleHQ extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#648ca4"),
			Colors.fromHex("#f87a36"));
	
	public JungleHQ(Level level, int x, int y) {
		super(level, x, y, 148, 96);
		level.addEntity(new Transporter(level, x + 61, y + 80,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
		level.addEntity(new Transporter(level, x + 75, y + 80,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.jungle_hq);

	}
	
}
