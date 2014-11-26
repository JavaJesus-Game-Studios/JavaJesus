package ca.javajesus.game.entities;

import java.util.Random;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.monsters.Demon;
import ca.javajesus.game.entities.monsters.GangMember;
import ca.javajesus.game.entities.particles.HealthPack;
import ca.javajesus.game.entities.vehicles.CenturyLeSabre;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Spawner extends Entity {

	private int id;
	private Random random = new Random();

	public Spawner(Level level, double x, double y, Entity entity) {
		super(level);
		this.x = x;
		this.y = y;
		this.id = 0;
	}

	public Spawner(Level level, double x, double y, int id) {
		super(level);
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public void tick() {
		if (level.getMobs().size() > Game.ENTITY_LIMIT * 3 / 2.0) {
			return;
		}
		if (random.nextInt(1000) == 0) {
			spawnMob();
		}

	}

	public void spawnMob() {

		this.level.addEntity(getEntity(id));

	}

	private Entity getEntity(int id) {
		switch (id) {
		case 0:
			return new Demon(this.level, "Demon", x, y, 1);
		case 1:
			return new GangMember(this.level, "Gang", x, y, 1, 200);
		case 2:
			return new CenturyLeSabre(this.level, "Century LeSabre", x, y, 5,
					SpriteSheet.vehicles, 200);
		default:
			return new HealthPack(this.level, x, y);
		}
	}

	public void render(Screen screen) {
	}

}
