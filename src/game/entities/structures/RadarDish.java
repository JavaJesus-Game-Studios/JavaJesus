package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;
import level.interior.RadarDishInterior;

public class RadarDish extends SolidEntity {

	public RadarDish(Level level, int x, int y) {
		super(level, x, y, 85, 133);
		level.addEntity(new Transporter(level, x + 37, y + 117,
				new RadarDishInterior(new Point(x + 43, y + 125), this.level)));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFFA3A3C2, 0xFF75758C }, Sprite.radardish);

	}

}
