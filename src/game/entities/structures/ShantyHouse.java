package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.ShantyHouseInterior;

public class ShantyHouse extends SolidEntity {

	public ShantyHouse(Level level, int x, int y) {
		super(level, x, y, 36, 47);
		level.addEntity(new Transporter(level, x + 12, y + 31, new ShantyHouseInterior(new Point(x + 18, y + 42), level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFFADADAD,
				0xFF000000 }, Sprite.shantyhouse);

	}
}
