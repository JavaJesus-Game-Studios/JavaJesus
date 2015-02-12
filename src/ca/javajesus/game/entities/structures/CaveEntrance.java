package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class CaveEntrance extends SolidEntity {
	
	protected int color = Colors.get(-1, Colors.fromHex("#301e01"), Colors.fromHex("#372201"), -1);
	
	public CaveEntrance(Level level, double x, double y) {
		super(level, x, y, 48, 36);
		level.addEntity(new Transporter(level, x + 18, y + 20, new PoorHouseInterior(new Point((int) x + 20, (int) y + 28), this.level)));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.cave_entrance);

	}

}
