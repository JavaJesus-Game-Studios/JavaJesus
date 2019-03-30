package javajesus.entities;

import java.util.Random;

import javajesus.JavaJesus;
import javajesus.dataIO.EntityData;
import javajesus.graphics.Screen;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * An entity that creates other entities
 */
public class Spawner extends Entity implements Type {

	// types of entities to spawn
	public static final byte DEMON = 0, GANG_MEMBER = 1, HEALTH_PACK = 2, CAR = 3, CYCLOPS = 4, MONKEY = 5, CENTAUR = 6;

	// the type of mob to spawn
	private byte type;

	// used to spawn mobs at random times
	private static final Random random = new Random();

	// the number of mobs spawned
	private int amount;

	// the last entity spawned
	private Entity currentEntity;
	
	// sprite of the spawner
	private static final Sprite sprite = new Sprite("/VISUAL_DATA/TILES/entity_spawn.png");

	/**
	 * Creates an invisible entity to spawn an infinite amount of entities
	 * 
	 * @param level - the level to place it on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @param type - Types are found in the Spawner class
	 */
	public Spawner(Level level, int x, int y, int type) {
		super(level, x, y);
		this.type = (byte) type;

		// infinite
		amount = -1;
		
		this.setBounds(x, y, 1, 1);
	}

	/**
	 * Creates an invisible entity to spawn a finite amount of entities
	 * 
	 * @param level - the level to place it on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @param type - Types are found in the Spawner class
	 * @param amount - the amount of entities to spawn
	 */
	public Spawner(Level level, int x, int y, int type, int amount) {
		super(level, x, y);
		this.type = (byte) type;
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
					&& getLevel().getMobs().size() < JavaJesus.ENTITY_LIMIT)
				spawnMob();
		} else if (random.nextInt(1000) == 0 && getLevel().getEntities().size() < JavaJesus.ENTITY_LIMIT) {
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
		return EntityData.getEntity(type, getLevel(), getX(), getY(), null);
	}

	/**
	 * Renders the sprite to the screen
	 */
	public void render(Screen screen) {
		
		// render only in the editors
		if (!JavaJesus.isRunning()) {
			screen.render(getX(), getY(), null, sprite);
		}
	}

	@Override
	public byte getId() {
		return Entity.SPAWNER;
	}

	@Override
	public long getData() {
		return EntityData.type3(getX(), getY(), type);
	}

	@Override
	public byte getType() {
		return type;
	}

	@Override
	public void setType(byte type) {
		this.type = type;
	}

}
