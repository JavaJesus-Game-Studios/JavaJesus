package javajesus.entities.npcs;

import javajesus.entities.Entity;
import javajesus.entities.Type;
import javajesus.level.Level;

/*
 * Generic Citizen NPC
 */
public class Citizen extends NPC implements Type {

	// types of citizens
	public static final int MALE = 0, FEMALE = 1, BOY = 2, GIRL = 3;

	// the type of citizen
	private byte type;

	/**
	 * Citizen ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param type - Peasant.MALE/FEMALE/BOY/GIRL
	 */
	public Citizen(Level level, int x, int y, int type) {
		this(level, x, y, type, "", 0);
	}

	/**
	 * Citizen ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param type - type of peasant to render
	 * @param walkPath - walk path
	 * @param walkDistance - walk distance
	 */
	public Citizen(Level level, int x, int y, int type, String walkPath, int walkDistance) {
		super(level, "Citizen", x, y, 1, 16, 16, 100, new int[] { 0xFF111111, 0xFF77a2e3, 0xFFf4cd96, 0xFF5b2a05, 0xFFd3c087 }
		, 0, 15, walkPath, walkDistance);
		
		// instance data
		this.type = (byte) type;
		
		// calculate the tile offsets
		update();
	}

	/**
	 * Sets the correct x and y tiles on the spritesheet
	 */
	private void update() {
		switch (type) {
		case FEMALE: {
			xTile = 0;
			yTile = 17;
			break;
		}
		case BOY: {
			xTile = 14;
			yTile = 15;
			break;
		}
		case GIRL: {
			xTile = 14;
			yTile = 17;
			break;
		}
		default: // MALE
			xTile = 0;
			yTile = 15;
			break;
		}
	}

	@Override
	public int getStrength() {
		return 0;
	}

	@Override
	public int getDefense() {
		return 2;
	}

	@Override
	public byte getId() {
		return Entity.PEASANT;
	}

	@Override
	public byte getType() {
		return type;
	}

	@Override
	public void setType(byte type) {
		this.type = type;
		update();
	}

}
