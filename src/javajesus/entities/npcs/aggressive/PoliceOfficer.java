package javajesus.entities.npcs.aggressive;

import java.awt.Color;

import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.level.Level;

/*
 * A police officer is a good fighter against hostile mobs
 */
public class PoliceOfficer extends Shooter {

	// dimensions the police officer
	private static final int WIDTH = 16, HEIGHT = 16;

	// different types of police officer
	public static final int MALE = 0, FEMALE = 1;

	// the type of police officer
	private byte type;

	/**
	 * Creates a Police Officer
	 * 
	 * @param level the level it is on
	 * @param x the x coord
	 * @param y the y coord
	 * @param defaultHealth the base health
	 * @param walkPath the walk pattern
	 * @param walkDistance the walk distance
	 */
	public PoliceOfficer(Level level, int x, int y, int defaultHealth, String walkPath, int walkDistance, int type) {
		super(level, "Police Officer", x, y, 1, WIDTH, HEIGHT, defaultHealth,
		        new int[] { 0xFF111111, 0xFF000046, 0xFFEDC5AB, 0, 0 }, 0, 4, walkPath, walkDistance);

		// instance data
		this.type = (byte) type;
		update();
	}

	/**
	 * Creates a default police officer
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 */
	public PoliceOfficer(Level level, int x, int y, int type) {
		this(level, x, y, 200, "", 0, type);
	}

	private void update() {
		switch (type) {
		case FEMALE: {
			xTile = 0;
			yTile = 5;
			break;
		}
		default: // MALE
			xTile = 0;
			yTile = 3;
			break;
		}
	}

	/**
	 * Dialogue option for police officers
	 */
	public void doDialogue() {
		MessageHandler.displayText(getName() + ": Hello citizen!", Color.yellow);
		return;
	}

	@Override
	public byte getId() {
		return Entity.POLICE_OFFICER;
	}

}
