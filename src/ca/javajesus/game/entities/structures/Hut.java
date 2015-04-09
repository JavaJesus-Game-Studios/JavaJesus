package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.HutInterior;

public class Hut extends SolidEntity {

	public Hut(Level level, int x, int y) {
		super(level, x, y, 40, 48);
		level.addEntity(new Transporter(level, x + 18, y + 32, new HutInterior(
				new Point(x + 24, y + 43), this.level)));
	}

	public Hut(Level level, int x, int y, Entity entity) {
		super(level, x, y, 40, 48);
		level.addEntity(new Transporter(level, x + 18, y + 32, new HutInterior(
				new Point(x + 24, y + 43), this.level, entity)));
	}

	public Hut(Level level, int x, int y, Entity entity, Spawner spawner) {
		super(level, x, y, 40, 48);
		level.addEntity(new Transporter(level, x + 18, y + 32, new HutInterior(
				new Point(x + 24, y + 43), this.level, entity, spawner)));
	}

	public void render(Screen screen) {

		screen.render(x, y, new int[] { 0xFF654000, 0xFF814700, 0xFFFFEA00 }, Sprite.hut_exterior);

	}

}
