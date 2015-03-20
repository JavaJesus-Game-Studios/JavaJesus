package ca.javajesus.game.entities.structures;

import java.awt.Point;
import java.awt.Rectangle;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.CastleInterior;

public class Castle extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#8d8d8d"),
			Colors.fromHex("#eefeff"));

	public Castle(Level level, int x, int y) {
		super(level, x, y, 321, 176);
		this.shadow = new Rectangle(width, (5 * height / 6));
		this.shadow.setLocation(x + 12, y);
		this.bounds = new Rectangle(width, (height / 6) - 8);
		this.bounds.setLocation(x + 12, y + shadow.height);
		level.addEntity(new Transporter(level, x + 154, y + 160,
				new CastleInterior(new Point(x + 43, y + 167), this.level)));

	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.castle);

	}

}
