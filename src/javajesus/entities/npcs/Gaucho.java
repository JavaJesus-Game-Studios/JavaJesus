package javajesus.entities.npcs;

import javajesus.entities.Entity;
import javajesus.level.Level;

/*
 * Guy wearing bandana on friendly sheet
 */
public class Gaucho extends NPC {

	/**
	 * Creates a Gaucho
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Gaucho(Level level, int x, int y) {
		super(level, "Gaucho", x, y, 1, 16, 16, 200, new int[] { 0xFF111111,
				0xFF715B17, 0xFFEDC5AB }, 0, 34, "", 0);
	}

	@Override
	public int getStrength() {
		return 0;
	}

	@Override
	public int getDefense() {
		return 0;
	}

	@Override
	public byte getId() {
		return Entity.GAUCHO;
	}

}
