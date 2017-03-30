package javajesus.entities.npcs;

import level.Level;

public class Peasant extends NPC {

	private static final long serialVersionUID = 289754202579619845L;
	
	public static final int MALE = 0, FEMALE = 1, BOY = 2, GIRL = 3;

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

}
