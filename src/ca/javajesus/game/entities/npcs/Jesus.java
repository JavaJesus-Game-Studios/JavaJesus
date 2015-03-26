package ca.javajesus.game.entities.npcs;

import java.awt.geom.Ellipse2D;

import ca.javajesus.level.Level;
import ca.javajesus.quests.SampleQuest;

public class Jesus extends NPC {
	private static int[] color = { 0xFF111111, 0xFFFFFFFF, 0xFFFFD89B };

	public Jesus(Level level, int x, int y, String walkPath, int walkDistance) {
		super(level, "Jesus", x, y, 1, 16, 16, 9000, color, 0, 6, walkPath,
				walkDistance);
		this.walkRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);

		this.addQuest(new SampleQuest(this));
		this.currentQuest = quests.get(0);

	}
}
