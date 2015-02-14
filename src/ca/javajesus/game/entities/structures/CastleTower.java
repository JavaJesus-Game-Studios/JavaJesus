package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.CastleInterior;
import ca.javajesus.level.interior.PoorHouseInterior;

public class CastleTower extends SolidEntity {
	
	protected int color = Colors.get(-1, 111, Colors.fromHex("#8d8d8d"), Colors.fromHex("#eefeff"));
	
	public CastleTower(Level level, double x, double y) {
		super(level, x, y, 93, 175);
		level.addEntity(new Transporter(level, x + 41, y + 159, new CastleInterior(new Point((int) x + 43, (int) y + 167), this.level)));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.castle_tower);

	}

}
