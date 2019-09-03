package javajesus.entities.npcs.characters;

import javajesus.entities.Entity;
import javajesus.entities.npcs.NPC;
import javajesus.level.Level;

/*
 * Main Quest Giver for Sandbox mode 
 * 
 * How is he different than Gaucho???
 */
public class Ranchero extends NPC {

	/**
	 * Creates Ranchero
	 * @param level the level to place him on
	 * @param x the x coord
	 * @param y the y coord
	 */
	public Ranchero(Level level, int x, int y) {
		super(level, "Ranchero", x, y, 1, 16, 16, 100, new int[] { 0xFF111111,
				0xFF000046, 0xFFEDC5AB }, 0, 30, "", 0);

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
		return Entity.RANCHERO;
	}

}
