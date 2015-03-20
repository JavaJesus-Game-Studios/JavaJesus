package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class RussianClub extends SolidEntity {

	protected int color = Colors.get(-1, Colors.fromHex("#ffeb0a"), Colors.fromHex("#80004b"),
			Colors.fromHex("#e934f9"));

	public RussianClub(Level level, int x, int y) {
		super(level, x, y, 100, 61);
		level.addEntity(new Transporter(level, x + 38, y + 45, 
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
		level.addEntity(new Transporter(level, x + 51, y + 45, this.level));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.russian_club);

	}

}
