package ca.javajesus.game.entities.structures;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.RandomLevel;

public class SanCiscoSkyscraper extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#fffdf5"), Colors.fromHex("#081a3d"));

	public SanCiscoSkyscraper(Level level, double x, double y) {
		super(level, x, y, 64, 208);
		level.addEntity(new Transporter(level, x + 26, y + 192,
				new RandomLevel(Game.WIDTH, Game.HEIGHT)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.sanCisco_skyscraper);

	}

}
