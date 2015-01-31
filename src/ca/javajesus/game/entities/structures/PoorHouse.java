package ca.javajesus.game.entities.structures;

import java.awt.Point;
import java.util.Random;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.RandomLevel2;
import ca.javajesus.level.interior.PoorHouseInterior;

public class PoorHouse extends SolidEntity {

	private Random random = new Random();

	public PoorHouse(Level level, double x, double y) {
		super(level, x, y, 40, 55);
		getColor();
		//level.addEntity(new Transporter(level, x + 14, y + 39, new PoorHouseInterior(new Point((int) x + 16, (int) y + 47))));
		level.addEntity(new Transporter(level, x + 14, y + 39, new RandomLevel2(Level.level1.width, Level.level1.height)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.poor_house);

	}
	private void getColor() {
		switch (random.nextInt(8)) {
		case 0: {
			// red color
			color = Colors.get(-1, 111, Colors.fromHex("#d50000"), 555);
			break;
		}
		case 1: {
			// yellow color
			color = Colors.get(-1, 111, Colors.fromHex("#fff115"), 555);
			break;
		}

		case 2: {
			// blue color
			color = Colors.get(-1, 111, Colors.fromHex("#6997ff"), 555);
			break;
		}
		case 3: {
			// pink color
			color = Colors.get(-1, 111, Colors.fromHex("#ff8be5"), 555);
			break;
		}
		case 4: {
			// white color
			color = Colors.get(-1, 111, Colors.fromHex("#ffffff"), 555);

			break;
		}
		case 5: {
			// green color
			color = Colors.get(-1, 111, Colors.fromHex("#009612"), 555);
			break;
		}
		case 6: {
			// purple color
			color = Colors.get(-1, 111, Colors.fromHex("#6f1e8d"), 555);
			break;
		}

		default: {
			// tan color
			color = Colors.get(-1, 111, Colors.fromHex("#fffcb1"), 555);
			break;
		}
		}
	}

}
