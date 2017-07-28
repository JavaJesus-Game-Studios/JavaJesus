package javajesus.entities.solid.buildings;

import java.awt.Point;
import java.io.IOException;
import java.util.Random;

import javajesus.entities.Entity;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.NiceHouse1Interior;

/*
 * A variation of a nice house
 */
public class NiceHouse2 extends Building {

	// randomizes house color
	private static final Random random = new Random();

	/**
	 * Creates another nice house
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public NiceHouse2(Level level, int x, int y) throws IOException {
		super(level, x, y, getColor(), Sprite.nice_house_2);

		if (level != null)
		level.add(
		        new Door(level, x + 18, y + 42, new NiceHouse1Interior(new Point(x + 23, y + 49), getLevel())));
	}

	/**
	 * @return A random color for the house
	 */
	private final static int[] getColor() {
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
	
	@Override
    public byte getId(){
        return Entity.NICE_HOUSE2;
    }
}
