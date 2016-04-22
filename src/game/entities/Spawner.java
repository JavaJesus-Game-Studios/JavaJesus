package game.entities;

import game.Game;
import game.entities.monsters.Demon;
import game.entities.monsters.GangMember;
import game.entities.particles.HealthPack;
import game.graphics.Screen;

import java.util.Random;

import level.Level;

/*
 * An entity that creates other entities
 */
public class Spawner extends Entity {

	private static final long serialVersionUID = -1243740183193796893L;

	// types of entities to spawn
	public static final int DEMON = 0;
	public static final int GANG_MEMBER = 1;
	public static final int HEALTH_PACK = 2;

	// the type of mob to spawn
	private int type;

	// used to spawn mobs at random times
	private static final Random random = new Random();

	// the number of mobs spawned
	private int amount;

	// the last entity spawned
	private Entity currentEntity;

	/**
	 * Creates an invisible entity to spawn an infinite amount of entities
	 * 
	 * @param level
	 *            the level to place it on
	 * @param x
	 *            the x coord on the level
	 * @param y
	 *            the y coord on the level
	 * @param type
	 *            Types are found in the Spawner class
	 */
	public Spawner(Level level, int x, int y, int type) {
		super(level, x, y);
		this.type = type;

		// infinite
		amount = -1;
	}

	/**
	 * Creates an invisible entity to spawn a finite amount of entities
	 * 
	 * @param level
	 *            the level to place it on
	 * @param x
	 *            the x coord on the level
	 * @param y
	 *            the y coord on the level
	 * @param type
	 *            Types are found in the Spawner class
	 * @param amount
	 *            the amount of entities to spawn
	 */
	public Spawner(Level level, int x, int y, int type, int amount) {
		super(level, x, y);
		this.type = type;
		this.amount = amount;
	}

	/**
	 * Randomly generates entities
	 */
	public void tick() {

		// always have at least something spawned
		if (currentEntity == null) {
			spawnMob();
		}

		// only spawn another mob if the current one is dead
		if (currentEntity instanceof Mob) {
			if (((Mob) currentEntity).isDead() && random.nextInt(1000) == 0
					&& getLevel().getMobs().size() < Game.ENTITY_LIMIT)
				spawnMob();
		} else if (random.nextInt(1000) == 0 && getLevel().getEntities().size() < Game.ENTITY_LIMIT) {
			spawnMob();
		}

		// remove the spawner if the amount left is 0
		if (amount == 0) {
			getLevel().remEntity(this);
		}

	}

	/**
	 * Generates the entity
	 */
	private void spawnMob() {

		if (amount > 0) {
			amount--;
			getLevel().addEntity(getEntity());
		} else if (amount == -1) {
			getLevel().addEntity(getEntity());
		}

	}

	/**
	 * gets the appropriate entity to spawn
	 * 
	 * @return the Entity to spawn
	 */
	private Entity getEntity() {
		switch (type) {
		case DEMON:
			return currentEntity = new Demon(getLevel(), getX(), getY(), 1, 100);
		case GANG_MEMBER:
			return currentEntity = new GangMember(getLevel(), getX(), getY(), 1, 200, random.nextInt(2));
		case HEALTH_PACK:
			return currentEntity = new HealthPack(getLevel(), getX(), getY(), true);
		default:
			return null;
		}
	}

	public void render(Screen screen) {
	}

}
