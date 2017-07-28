package javajesus.entities.transporters;

import javajesus.dataIO.EntityData;
import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.level.Level;

/*
 * A transporter is an entity that sends a player to another level
 */
public abstract class Transporter extends Entity {

	// the level the transporter leads to
	private Level nextLevel;

	/**
	 * Creates a Transporter to change the level
	 * @param currentLevel the level it is on
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param outside the new level
	 */
	public Transporter(Level currentLevel, int x, int y, int width, int height, Level nextLevel) {
		super(currentLevel, x, y);

		// set up the transporter
		this.nextLevel = nextLevel;
		this.setBounds(getX(), getY(), width, height);
	}

	/**
	 * Transporters don't update
	 */
	public void tick() {}

	/**
	 * Displays the transporter on the screen
	 */
	public abstract void render(Screen screen);
	
	/**
	 * Sets the outside
	 * @param next
	 */
	public final void setNextLevel(Level next) {
		nextLevel = next;
	}
	
	/**
	 * @return the next level
	 */
	public Level getNextLevel() {
		return nextLevel;
	}

	@Override
	public byte getId() {
		return -1;
	}
	
	@Override
	public long getData() {
		return EntityData.type3(getX(), getY(), (byte) 0);
	}

}
