package javajesus.entities.npcs.aggressive;

import java.awt.Color;

import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.entities.Type;
import javajesus.level.Level;

/*
 * A Native American NPC
 */
public class NativeAmerican extends Shooter implements Type {

	// dimensions the companion
	private static final int WIDTH = 16, HEIGHT = 16;

	// different types of native americans
	public static final int MALE = 0, FEMALE = 1, BOY = 2, GIRL = 3;
	
	// the type of native american
	private byte type;
	
	// the base ytile
	private int base;

	/**
	 * Creates a Native American with different abilities
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param defaultHealth - the base health
	 * @param walkPath - the walk pattern
	 * @param walkDistance - the walk distance
	 * @param type - NativeAmerican.MALE or NativeAmerican.FEMALE
	 */
	public NativeAmerican(Level level, int x, int y, int defaultHealth, String walkPath, int walkDistance, int type) {
		super(level, "Ohlone", x, y, 1, WIDTH, HEIGHT, defaultHealth,
				new int[] { 0xFF111111, 0xFF000046, 0xFFEDC5AB }, 0, 23, walkPath, walkDistance);
		
		// instance data
		this.type = (byte) type;
		this.base = yTile;
		update();
	}
	
	private void update() {
		switch (type) {
		case FEMALE: {
			xTile = 0;
			yTile = 25;
			break;
		}
		case BOY: {
			xTile = 0;
			yTile = 27;
			break;
		}
		case GIRL: {
			xTile = 0;
			yTile = 29;
			break;
		}
		default: // MALE
			xTile = 0;
			yTile = 23;
			break;
		}
	}

	/**
	 * Creates a default Native American
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param type - NativeAmerican.MALE or NativeAmerican.FEMALE
	 */
	public NativeAmerican(Level level, int x, int y, int type) {
		this(level, x, y, 200, "", 0, type);
	}

	/**
	 * Dialogue options for Native Americans
	 */
	public void doDialogue() {
		MessageHandler.displayText(getName() + ": You look mischevious, were you sent by Coyote?", Color.white);
		return;
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Entity.NATIVE_AMERICAN;
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
