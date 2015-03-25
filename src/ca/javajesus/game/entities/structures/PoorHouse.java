package ca.javajesus.game.entities.structures;

import java.awt.Point;
import java.util.Random;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;

public class PoorHouse extends SolidEntity {

	private Random random = new Random();

	public PoorHouse(Level level, int x, int y) {
		super(level, x, y, 32, 55);
		getColor();
		level.addEntity(new Transporter(level, x + 14, y + 39,
				new PoorHouseInterior(new Point(x + 16, y + 47), this.level)));
	}

	public void render(Screen screen) {

		screen.render(x, y, color, Sprite.poor_house);

	}

	private void getColor() {
		int[] color = { 0xFF111111, 0xFFD50000, 0xFFFFFFFF };
		switch (random.nextInt(8)) {
		case 0: {
			// red color
			color[1] = 0xFFD50000;
			break;
		}
		case 1: {
			// yellow color
			color[1] = 0xFFFFF115;
			break;
		}

		case 2: {
			// blue color
			color[1] = 0xFF6997FF;
			break;
		}
		case 3: {
			// pink color
			color[1] = 0xFFFF8BE5;
			break;
		}
		case 4: {
			// white color
			color[1] = 0xFFFFFFFF;

			break;
		}
		case 5: {
			// green color
			color[1] = 0xFF009612;
			break;
		}
		case 6: {
			// purple color
			color[1] = 0xFF6F1E8D;
			break;
		}

		default: {
			// tan color
			color[1] = 0xFFFFFCB1;
			break;
		}
		}
		this.color = color;
	}

}
