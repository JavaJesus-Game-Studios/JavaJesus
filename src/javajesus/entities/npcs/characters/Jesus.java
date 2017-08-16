package javajesus.entities.npcs.characters;

import javajesus.entities.Entity;
import javajesus.entities.npcs.NPC;
import javajesus.level.Level;
import javajesus.quest.original.TheEvilFox;

/*
 * Jesus
 */
public class Jesus extends NPC {

	// dimensions Jesus
	private static final int WIDTH = 16, HEIGHT = 16;
	
	/**
	 * Creates Jesus
	 * @param level the level to place him on
	 * @param x the x coord
	 * @param y the y coord
	 */
	public Jesus(Level level, int x, int y) {
		this(level, x, y, "", 0);
	}

	/**
	 * Creates Jesus
	 * @param level the level to place him on
	 * @param x the x coord
	 * @param y the y coord
	 * @param walkPath the type of idle walking
	 * @param walkDistance the distance of the idle walking
	 */
	public Jesus(Level level, int x, int y, String walkPath, int walkDistance) {
		super(level, "Jesus", x, y, 1, WIDTH, HEIGHT, 9000, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFD89B, 0xFF4b2700 }, 0, 6,
				walkPath, walkDistance);
		
		// add the evil fox quest
		addQuest(new TheEvilFox(this));
	}

	@Override
	public int getStrength() {
		return 0;
	}

	@Override
	public int getDefense() {
		return 100;
	}

	@Override
	public byte getId() {
		return Entity.JESUS;
	}
}
