package game.entities.npcs;

import java.awt.geom.Ellipse2D;

import level.Level;
import quests.SampleQuest;

public class Jesus extends NPC {

	private static final long serialVersionUID = 4573976610972157907L;
	
	public Jesus(Level level, int x, int y, String walkPath, int walkDistance) {
		super(level, "Jesus", x, y, 1, 16, 16, 9000, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFFFFD89B }, 0, 6, walkPath,
				walkDistance);
		this.walkRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);

		this.addQuest(new SampleQuest(this));
		this.currentQuest = quests.get(0);

	}
}
