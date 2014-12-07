package ca.javajesus.game.entities.structures;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.RandomLevel;

public class SkyScraper extends SolidEntity {

	protected int color = Colors.get(-1, 500, 111, Colors.toHex("#624300"));

	public SkyScraper(Level level, double x, double y, int width, int height) {
		super(level, x, y, width, height);
		level.addEntity(new Transporter(level, x + 26, y + 193,
				new RandomLevel(Game.WIDTH, Game.HEIGHT)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.skyscraper);

	}

}
