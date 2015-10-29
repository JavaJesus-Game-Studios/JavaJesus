package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;
import level.interior.WarehouseInterior;

public class Warehouse  extends SolidEntity {

	public Warehouse(Level level, int x, int y) {
		super(level, x, y, 100, 59);
		level.addEntity(new Transporter(level, x + 77, y + 43,
				new WarehouseInterior(new Point(x + 83, y + 54), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFF8D1919, 0xFF545454 }, Sprite.warehouse);

	}

}
