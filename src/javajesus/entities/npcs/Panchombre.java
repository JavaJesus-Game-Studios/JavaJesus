package javajesus.entities.npcs;

import javajesus.entities.Entity;
import javajesus.level.Level;

/*
 * Guy wearing poncho on friendly sheet
 */
public class Panchombre extends NPC {

	/**
	 * Creates a panchombre
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Panchombre(Level level, int x, int y) {
		super(level, "Panchombre", x, y, 1, 16, 16, 200, new int[] { 0xFF111111,
				0xFF715B17, 0xFFEDC5AB }, 0, 37, "", 0);
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
		return Entity.PANCHOMBRE;
	}

}
