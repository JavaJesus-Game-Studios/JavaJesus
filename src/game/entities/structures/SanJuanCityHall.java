package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;

public class SanJuanCityHall extends SolidEntity {

	public SanJuanCityHall(Level level, int x, int y) {
		super(level, x, y, 120, 120);
		level.addEntity(new Transporter(level, x + 67, y + 104,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFF919192, 0xFF4D4DFF }, Sprite.sanJuan_City_Hall);

	}

}
