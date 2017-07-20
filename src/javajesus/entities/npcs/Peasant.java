package javajesus.entities.npcs;

import javajesus.entities.Entity;
import javajesus.level.Level;

public class Peasant extends NPC {

	// types of peasants
	public static final int MALE = 0, FEMALE = 1, BOY = 2, GIRL = 3;

	/**
	 * Peasant ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param g - type; ex Peasant.MALE
	 */
	public Peasant(Level level, int x, int y, int g) {
		super(level, "Peasant", x, y, 1, 16, 16, 100, new int[] { 0xFF111111,
				0xFF715B17, 0xFFEDC5AB }, 0, 16, "", 0);
		calc(g);
	}
	
	public Peasant(Level level, int x, int y, int g, String walkPath, int walkDistance) {
		super(level, "Peasant", x, y, 1, 16, 16, 100, new int[] { 0xFF111111,
				0xFF715B17, 0xFFEDC5AB }, 0, 16, walkPath, walkDistance);
		calc(g);
	}
	
	private void calc(int g) {
		switch (g) {
		case FEMALE: {
			yTile += 2;
			break;
		}
		case BOY: {
			xTile += 14;
			break;
		}
		case GIRL: {
			xTile += 14;
			yTile += 2;
			break;
		}
		default:
			break;
		}
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Entity.PEASANT;
	}

}
