package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.HutInterior;

public class Hut extends SolidEntity {

	protected int color = Colors.get(-1, Colors.fromHex("#654000"),
			Colors.fromHex("#814700"), Colors.fromHex("#ffea00"));

	public Hut(Level level, int x, int y) {
		super(level, x, y, 40, 48);
		level.addEntity(new Transporter(level, x + 18, y + 32, new HutInterior(
				new Point(x + 20, y + 40), this.level)));
	}

	public Hut(Level level, int x, int y, Entity entity) {
		super(level, x, y, 40, 48);
		level.addEntity(new Transporter(level, x + 18, y + 32, new HutInterior(
				new Point(x + 20, y + 40), this.level, entity)));
	}

	public Hut(Level level, int x, int y, Entity entity, Spawner spawner) {
		super(level, x, y, 40, 48);
		level.addEntity(new Transporter(level, x + 18, y + 32, new HutInterior(
				new Point(x + 20, y + 40), this.level, entity, spawner)));
	}

	public void render(Screen screen) {

		screen.render(x, y, color, Sprite.hut_exterior);

	}

}
