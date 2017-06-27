package javajesus.entities.npcs.aggressive;

import java.awt.Color;

import javajesus.ChatHandler;
import javajesus.level.Level;

/*
 * A police officer is a good fighter against hostile mobs
 */
public class PoliceOfficer extends Shooter {

	private static final long serialVersionUID = -7410940331032155987L;

	// dimensions the police officer
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates a Police Officer
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param defaultHealth
	 *            the base health
	 * @param walkPath
	 *            the walk pattern
	 * @param walkDistance
	 *            the walk distance
	 */
	public PoliceOfficer(Level level, int x, int y, int defaultHealth, String walkPath, int walkDistance) {
		super(level, "Police Officer", x, y, 1, WIDTH, HEIGHT, defaultHealth,
				new int[] { 0xFF111111, 0xFF000046, 0xFFEDC5AB }, 0, 4, walkPath, walkDistance);
	}

	/**
	 * Creates a default police officer
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public PoliceOfficer(Level level, int x, int y) {
		this(level, x, y, 200, "", 0);
	}

	/**
	 * Dialogue option for police officers
	 */
	public void doDialogue() {
		ChatHandler.displayText(getName() + ": Hello citizen!", Color.yellow);
		return;
	}

}
