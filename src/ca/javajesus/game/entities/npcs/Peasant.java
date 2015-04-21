package ca.javajesus.game.entities.npcs;

import ca.javajesus.level.Level;

public class Peasant extends NPC {

	private static final long serialVersionUID = 289754202579619845L;

	public Peasant(Level level, int x, int y, Gender g) {
		super(level, "Peasant", x, y, 1, 16, 16, 100, new int[] { 0xFF111111,
				0xFF715B17, 0xFFEDC5AB }, 0, 16, "", 0);
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
