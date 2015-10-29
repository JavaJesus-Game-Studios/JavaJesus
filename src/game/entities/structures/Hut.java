package game.entities.structures;

import game.entities.Entity;
import game.entities.SolidEntity;
import game.entities.Spawner;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.HutInterior;

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
