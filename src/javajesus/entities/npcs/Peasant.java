package javajesus.entities.npcs;

import javajesus.entities.Entity;
import javajesus.entities.Type;
import javajesus.level.Level;

public class Peasant extends NPC implements Type {

	// types of peasants
	public static final int MALE = 0, FEMALE = 1, BOY = 2, GIRL = 3;
	
	// the type of peasant
	private byte type;

	/**
	 * Peasant ctor()
	 * 
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param g - type; ex Peasant.MALE
	 */
	public Peasant(Level level, int x, int y, int g) {
		this(level, x, y, g, "", 0);
	}
	
	public Peasant(Level level, int x, int y, int g, String walkPath, int walkDistance) {
		super(level, "Peasant", x, y, 1, 16, 16, 100, new int[] { 0xFF111111,
				0xFF715B17, 0xFFEDC5AB }, 0, 16, walkPath, walkDistance);
		
		// instance data
		this.type = (byte) g;
		
		// calculate the tile offsets
		update();
	}
	
	private void update() {
		switch (type) {
		case FEMALE: {
			yTile += 2;
			break;
		}
		case BOY: {
			xTile += 14;
			break;
		}
		case GIRL: {
			xTile += 14;
			yTile += 2;
			break;
		}
		default:
			break;
		}
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
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
