package ca.javajesus.game.entities.npcs;

import ca.javajesus.level.Level;

public class Ranchero extends NPC {

	public Ranchero(Level level, int x, int y) {
		super(level, "Ranchero", x, y, 1, 16, 16, 100, new int[] { 0xFF111111,
				0xFF000046, 0xFFEDC5AB }, 0, 35, "", 0);
	}

}
