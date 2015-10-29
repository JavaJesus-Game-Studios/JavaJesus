package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.CatholicChurchInterior;
import level.interior.RancheroHouseInterior;

public class RancheroHouse extends SolidEntity {

	public RancheroHouse(Level level, int x, int y) {
		super(level, x, y, 100, 61);
		level.addEntity(new Transporter(level, x + 44, y + 45,
				new RancheroHouseInterior(new Point(x + 50, y + 56),
						this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFD30000 }, Sprite.ranchero_house);

	}

}
