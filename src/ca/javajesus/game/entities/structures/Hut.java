package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class Hut extends SolidEntity {
	
	protected int color = Colors.get(-1, Colors.fromHex("#654000"), Colors.fromHex("#814700"), Colors.fromHex("#ffea00"));
	
	public Hut(Level level, double x, double y) {
		super(level, x, y, 48, 48);
		level.addEntity(new Transporter(level, x + 18, y + 32, new PoorHouseInterior(new Point((int) x + 20, (int) y + 40), this.level)));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.hut_exterior);

	}

}
