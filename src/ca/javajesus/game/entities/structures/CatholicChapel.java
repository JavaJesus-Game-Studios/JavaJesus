package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.CatholicChurchInterior;

public class CatholicChapel extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#fffab0"),
			Colors.fromHex("#abd3ff"));

	public CatholicChapel(Level level, int x, int y) {
		super(level, x, y, 54, 63);
		level.addEntity(new Transporter(level, x + 21, y + 47,
				new CatholicChurchInterior(new Point(x + 40, y + 67),
						this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.catholic_chapel);

	}

}
