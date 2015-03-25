package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class RadarDish extends SolidEntity {

	public RadarDish(Level level, int x, int y) {
		super(level, x, y, 85, 133);
		level.addEntity(new Transporter(level, x + 37, y + 117,
				new PoorHouseInterior(new Point(x + 44, y + 67), this.level)));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFFA3A3C2, 0xFF75758C }, Sprite.radardish);

	}

}
