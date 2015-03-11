package ca.javajesus.game.entities;

import java.util.Random;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.monsters.Demon;
import ca.javajesus.game.entities.monsters.GangMember;
import ca.javajesus.game.entities.particles.HealthPack;
import ca.javajesus.game.entities.vehicles.CenturyLeSabre;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Spawner extends Entity {

	private String type;
	private Random random = new Random();
	private int amount = 0;

	public Spawner(Level level, double x, double y, String type) {
		super(level);
		this.x = x;
		this.y = y;
		this.type = type;
		amount = -1;
	}

	public Spawner(Level level, double x, double y, String type, int amount) {
		super(level);
		this.x = x;
		this.y = y;
		this.type = type;
		this.amount = amount;
	}

	public void tick() {

		if (random.nextInt(1000) == 0
				&& level.getMobs().size() < Game.ENTITY_LIMIT) {
			spawnMob();
		}

		if (amount == 0) {
			level.remEntity(this);
		}
		
	}

	public void spawnMob() {

		if (amount > 0) {
			amount--;
			this.level.addEntity(getEntity());
		}

	}

	private Entity getEntity() {
		switch (type) {
		case "Demon":
			return new Demon(this.level, "Demon", x, y, 1);
		case "Gang":
			return new GangMember(this.level, "Gang", x, y, 1, 200,
					random.nextInt(2));
		case "Car":
			return new CenturyLeSabre(this.level, "Century LeSabre", x, y);
		case "Health":
			return new HealthPack(this.level, x, y);
		default:
			return null;
		}
	}

	public void render(Screen screen) {
	}

}
