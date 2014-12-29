package ca.javajesus.game.entities.structures;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class Skyscraper extends SolidEntity {
	
	protected int color = Colors.get(-1, 111, Colors.fromHex("#d50000"), 555);
	
	public Skyscraper(Level level, double x, double y) {
		super(level, x, y, 64, 170);
		level.addEntity(new Transporter(level, x + 24, y + 155, new PoorHouseInterior()));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.skyscraper);

	}

}
