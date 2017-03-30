package javajesus.entities;

import java.util.Random;

import javajesus.Game;
import javajesus.entities.monsters.Centaur;
import javajesus.entities.monsters.Cyclops;
import javajesus.entities.monsters.Demon;
import javajesus.entities.monsters.GangMember;
import javajesus.entities.monsters.Monkey;
import javajesus.entities.particles.HealthPack;
import javajesus.graphics.Screen;
import level.Level;

/*
 * An entity that creates other entities
 */
public class Spawner extends Entity {

	private static final long serialVersionUID = -1243740183193796893L;

	// types of entities to spawn
	public static final int DEMON = 0, GANG_MEMBER = 1, HEALTH_PACK = 2, CAR = 3, CYCLOPS = 4, MONKEY = 5, CENTAUR = 6;

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
		
		this.setBounds(x, y, 1, 1);
	}
	
	/**
	 * Creates an invisible entity to spawn an infinite amount of entities
	 * 
	 * @param level
	 *            the level to place it on
	 * @param x
	 *            the x coord on the level
	 * @param y
	 *            the y coord on the level
	 * @param name
	 *            Types are found in the Spawner class
	 */
	public Spawner(Level level, int x, int y, String name) {
		super(level, x, y);
		
		switch (name){ 
		case "Centaur":
			this.type = CENTAUR;
			break;
		case "Cyclops":
			this.type = CYCLOPS;
			break;
		case "Gangster":
			this.type = GANG_MEMBER;
			break;
		case "Monkey":
			this.type = MONKEY;
			break;
		
		// demon is default
		default:
			this.type = DEMON;
			break;
		}
		// infinite
		amount = -1;
		
		this.setBounds(x, y, 1, 1);
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
		
		this.setBounds(x, y, 1, 1);
	}

	/**
	 * Randomly generates entities
	 */
	public void tick() {
		
		// first spawn
		if (currentEntity == null) {
			
			if (random.nextInt(2000) == 0) {
				spawnMob();
			} else {
				return;
			}
		}

		// only spawn another mob if the current one is dead
		if (currentEntity instanceof Mob) {
			if (((Mob) currentEntity).isDead() && random.nextInt(2000) == 0
					&& getLevel().getMobs().size() < Game.ENTITY_LIMIT)
				spawnMob();
		} else if (random.nextInt(1000) == 0 && getLevel().getEntities().size() < Game.ENTITY_LIMIT) {
			spawnMob();
		}

		// remove the spawner if the amount left is 0
		if (amount == 0) {
			getLevel().remove(this);
		}

	}

	/**
	 * Generates the entity
	 */
	private void spawnMob() {

		if (amount > 0) {
			amount--;
			getLevel().add(getEntity());
		} else if (amount == -1) {
			getLevel().add(getEntity());
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
			return currentEntity = new Demon(getLevel(), getX(), getY(), 1, 100 + Game.score * 2);
		case GANG_MEMBER:
			return currentEntity = new GangMember(getLevel(), getX(), getY(), 1, 200 + Game.score * 3, random.nextInt(2));
		case HEALTH_PACK:
			return currentEntity = new HealthPack(getLevel(), getX(), getY(), true);
		case MONKEY:
			return currentEntity = new Monkey(getLevel(), getX(), getY(), 200 + Game.score * 4);
		case CYCLOPS:
			return currentEntity = new Cyclops(getLevel(), getX(), getY(), 300 + Game.score * 5);
		case CENTAUR:
			return currentEntity = new Centaur(getLevel(), getX(), getY(), 250 + Game.score * 4);
		default:
			return null;
		}
	}

	public void render(Screen screen) {
	}

}
