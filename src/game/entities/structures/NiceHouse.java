package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;
import java.util.Random;

import level.Level;
import level.interior.NiceHouse1Interior;

public class NiceHouse extends SolidEntity {

	private Random random = new Random();

	public NiceHouse(Level level, int x, int y) {
		super(level, x, y, 46, 57);
		getColor();
		level.addEntity(new Transporter(level, x + 21, y + 41,
				new NiceHouse1Interior(new Point(x + 23, y + 49), this.level)));
	}

	public void render(Screen screen) {

		screen.render(x, y, color, Sprite.nice_house);

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
