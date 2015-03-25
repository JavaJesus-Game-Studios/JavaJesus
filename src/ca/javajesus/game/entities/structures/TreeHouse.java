package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class TreeHouse extends SolidEntity {

	public TreeHouse(Level level, int x, int y) {
		super(level, x, y, 48, 120);
		level.addEntity(new Transporter(level, x + 18, y + 30,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFF143914, 0xFF522900 }, Sprite.treehouse);

	}

}
