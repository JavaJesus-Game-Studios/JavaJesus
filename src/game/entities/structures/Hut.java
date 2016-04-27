package game.entities.structures;

import java.awt.Point;
import game.entities.Entity;
import game.entities.SolidEntity;
import game.entities.Spawner;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.HutInterior;

/*
 * A small hut
 */
public class Hut extends Building {
	
	private static final int[] color = { 0xFF654000, 0xFF814700, 0xFFFFEA00 };

	private static final long serialVersionUID = -2327932586773802554L;

	public Hut(Level level, int x, int y) {
		super(level, x, y, color, Sprite.hut_exterior, SolidEntity.QUARTER);
		level.addEntity(new Transporter(level, x + 18, y + 32, new HutInterior(
				new Point(x + 24, y + 43), getLevel())));
	}

	public Hut(Level level, int x, int y, Entity entity) {
		super(level, x, y, color, Sprite.hut_exterior, SolidEntity.QUARTER);
		level.addEntity(new Transporter(level, x + 18, y + 32, new HutInterior(
				new Point(x + 24, y + 43), getLevel(), entity)));
	}

	public Hut(Level level, int x, int y, Entity entity, Spawner spawner) {
		super(level, x, y, color, Sprite.hut_exterior, SolidEntity.QUARTER);
		level.addEntity(new Transporter(level, x + 18, y + 32, new HutInterior(
				new Point(x + 24, y + 43), getLevel(), entity, spawner)));
	}

}
