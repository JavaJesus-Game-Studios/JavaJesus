package ca.javajesus.game.entities.structures;

import java.util.Random;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.RandomLevel;

public class House1 extends SolidEntity {

	private Random random = new Random();

	public House1(Level level, double x, double y, int width, int height) {
		super(level, x, y, width, height);
		getColor();
		level.addEntity(new Transporter(level, x + 9, y + 24, new RandomLevel(
				Game.WIDTH, Game.HEIGHT)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.house);

	}
	private void getColor() {
		switch (random.nextInt(8)) {
		case 0: {
			// red color
			color = Colors.get(-1, 111, Colors.toHex("#d50000"), 555);
			break;
		}
		case 1: {
			// yellow color
			color = Colors.get(-1, 111, Colors.toHex("#fff115"), 555);
			break;
		}

		case 2: {
			// blue color
			color = Colors.get(-1, 111, Colors.toHex("#6997ff"), 555);
			break;
		}
		case 3: {
			// pink color
			color = Colors.get(-1, 111, Colors.toHex("#ff8be5"), 555);
			break;
		}
		case 4: {
			// white color
			color = Colors.get(-1, 111, Colors.toHex("#ffffff"), 555);

			break;
		}
		case 5: {
			// green color
			color = Colors.get(-1, 111, Colors.toHex("#009612"), 555);
			break;
		}
		case 6: {
			// purple color
			color = Colors.get(-1, 111, Colors.toHex("#6f1e8d"), 555);
			break;
		}

		default: {
			// tan color
			color = Colors.get(-1, 111, Colors.toHex("#fffcb1"), 555);
			break;
		}
		}
	}

}
