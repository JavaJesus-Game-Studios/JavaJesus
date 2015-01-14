package ca.javajesus.game.entities.structures;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class NiceHouse extends SolidEntity {
	
	protected int color = Colors.get(-1, 111, Colors.fromHex("#d50000"), 555);

	public NiceHouse(Level level, double x, double y) {
		super(level, x, y, 48, 48);
		level.addEntity(new Transporter(level, x + 17, y + 32, new PoorHouseInterior()));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.nice_house);

	}

}