package ca.javajesus.game.entities.structures;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class NiceHouse extends SolidEntity {

	public NiceHouse(Level level, double x, double y, int width, int height) {
		super(level, x, y, width, height);
		level.addEntity(new Transporter(level, x + 9, y + 24, new PoorHouseInterior()));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.nice_house);

	}

}
