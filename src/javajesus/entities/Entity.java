package javajesus.entities;

import java.awt.Rectangle;
import java.io.Serializable;

import javajesus.graphics.Screen;
import level.Level;

/*
 * Something that is rendered on the screen on a level with an x and y coordinate
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 7152333070540764158L;
	
	// position on the screen
	private int x, y;
	
	// current level
	private Level level;
	
	// the dimensions to damage this entity
	private Rectangle bounds;

	/**
	 * Creates a new entity that can be manipulated on the screen
	 * @param level the level to place it
	 * @param x the x coord
	 * @param y the y coord
	 */
	public Entity(final Level level, int x, int y) {
		this.level = level;
		setX(x);
		setY(y);
		setBounds(0, 0, 0, 0);
	}

	/**
	 * @return the X coordinate of the entity
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the Y coordinate of the entity
	 */
	public int getY() {
		return y;
	}

	/**
	 * Changes the entity horizontally
	 * @param x the location to move the entity
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Changes the entity vertically
	 * @param y the location to move the entity
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the current level
	 */
	public Level getLevel() {
		return level;
	}
	
	/**
	 * @param level the entity's new level
	 */
	public void updateLevel(final Level level) {
		this.level = level;
	}

	/** 
	 * @return the bounds of the entity
	 */
	public final Rectangle getBounds() {
		return bounds;
	}
	
	/**
	 * Moves the entity horizontally and/or vertically relatively
	 * Also updates the bounds
	 * @param dx the change in x
	 * @param dy the change in y
	 */
	protected void move(int dx, int dy) {
		setX(getX() + dx);
		setY(getY() + dy);
		bounds.setLocation(getX(), getY());
	}
	
	/**
	 * Moves the entity to the specified x and y coord
	 * Also updates the bounds
	 * @param x the x coord
	 * @param y the y coord
	 */
	protected void moveTo(int x, int y) {
		setX(x);
		setY(y);
		bounds.setLocation(getX(), getY());
	}

	/**
	 * Creates the bounds of the entity from the top left corner (same as coords)
	 * @param xPos the x position (usually getX())
	 * @param yPos the y position (usually getY())
	 * @param width the width of the bounds
	 * @param height the height of the bounds
	 */
	public void setBounds(int xPos, int yPos, int width, int height) {
		bounds = new Rectangle(xPos, yPos, width, height);
		bounds.setLocation(xPos, yPos);
	}

	/**
	 * All entites are updated 60 times per second
	 */
	public abstract void tick();

	/**
	 * Most entities are displayed on the screen by rendering
	 * @param screen which screen to add the pixels to
	 */
	public abstract void render(final Screen screen);
	
	public String toString() {
		return "Location: (" + getX() + ", " + getY() +")";
	}
	
	/**
	 * Converts seconds to ticks
	 * @param seconds
	 * @return the proper tick count from seconds
	 */
	public static final int secondsToTicks(int seconds) {
		return seconds * 60;
	}

}
