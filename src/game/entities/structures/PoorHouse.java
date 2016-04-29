package game.entities.structures;

import java.awt.Point;
import java.util.Random;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * A typical poor house
 */
public class PoorHouse extends Building {

	private static final long serialVersionUID = 6601315410399638769L;
	
	// randomly assigns a color
	private static final Random random = new Random();

	public PoorHouse(Level level, int x, int y) {
		super(level, x, y, getColor(), Sprite.poor_house, SolidEntity.HALF);
		level.addEntity(new Transporter(level, x + 14, y + 39,
				new PoorHouseInterior(new Point(x + 20, y + 50), getLevel())));
	}

	/**
	 * @return The color for the poor house
	 */
	private static int[] getColor() {
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
		return color;
	}

}
