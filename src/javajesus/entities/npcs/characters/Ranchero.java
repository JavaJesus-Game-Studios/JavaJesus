package javajesus.entities.npcs.characters;

import javajesus.entities.Entity;
import javajesus.entities.npcs.NPC;
import javajesus.level.Level;

public class Ranchero extends NPC {

	public Ranchero(Level level, int x, int y) {
		super(level, "Ranchero", x, y, 1, 16, 16, 100, new int[] { 0xFF111111,
				0xFF000046, 0xFFEDC5AB }, 0, 35, "", 0);
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
		return Entity.RANCHERO;
	}

}
