package ca.javajesus.game.entities.npcs;

import java.awt.geom.Ellipse2D;

import quests.SampleQuest;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.JJFont;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Jesus extends NPC {

	public Jesus(Level level, int x, int y, String walkPath,
			int walkDistance) {
		super(level, "Jesus", x, y, 1, 16, 16, 9000, Colors.get(-1, 111, 555,
				Colors.fromHex("#ffd89b")), 0, 6, walkPath, walkDistance, 8);
		this.walkRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		
		this.addQuest(new SampleQuest(this));
		this.currentQuest = quests.get(0);

	}
}
