package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;
import level.interior.TreeHouseInterior;

public class TreeHouse extends SolidEntity {

	public TreeHouse(Level level, int x, int y) {
		super(level, x, y, 48, 120);
		level.addEntity(new Transporter(level, x + 18, y + 30,
				new TreeHouseInterior(new Point(x + 24, y + 42), this.level)));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFF143914, 0xFF522900 }, Sprite.treehouse);

	}

}
