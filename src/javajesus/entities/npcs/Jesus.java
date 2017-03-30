package javajesus.entities.npcs;

import level.Level;
import quests.SampleQuest;

/*
 * Jesus
 */
public class Jesus extends NPC {

	private static final long serialVersionUID = 4573976610972157907L;

	// dimensions Jesus
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates Jesus
	 * @param level the level to place him on
	 * @param x the x coord
	 * @param y the y coord
	 * @param walkPath the type of idle walking
	 * @param walkDistance the distance of the idle walking
	 */
	public Jesus(Level level, int x, int y, String walkPath, int walkDistance) {
		super(level, "Jesus", x, y, 1, WIDTH, HEIGHT, 9000, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFD89B }, 0, 6,
				walkPath, walkDistance);

		this.addQuest(new SampleQuest(this));

	}
}
