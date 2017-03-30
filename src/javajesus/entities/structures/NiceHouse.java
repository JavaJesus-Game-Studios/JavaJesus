package javajesus.entities.structures;

import java.awt.Point;
import java.util.Random;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import level.Level;
import level.interior.NiceHouse1Interior;

/*
 * A nice house
 */
public class NiceHouse extends Building {

	private static final long serialVersionUID = 3456590292364758034L;

	// randomizes color
	private static final Random random = new Random();

	public NiceHouse(Level level, int x, int y) {
		super(level, x, y, getColor(), Sprite.nice_house, SolidEntity.HALF);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(
				new Transporter(level, x + 21, y + 41, new NiceHouse1Interior(new Point(x + 23, y + 49), getLevel())));
	}

	/**
	 * @return the color of the house
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
