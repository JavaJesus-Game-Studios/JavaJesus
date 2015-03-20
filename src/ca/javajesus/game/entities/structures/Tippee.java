package ca.javajesus.game.entities.structures;


import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;

public class Tippee  extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#f8fa8f"),
			Colors.fromHex("#559dda"));

	public Tippee(Level level, int x, int y) {
		super(level, x, y, 32, 50);
		level.addEntity(new Transporter(level, x + 10, y + 34, this.level));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.tippee);

	}

}
