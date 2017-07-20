package javajesus.entities;

import java.awt.Rectangle;

import javajesus.graphics.Screen;
import javajesus.level.Level;

/*
 * Something that is rendered on the screen on a level with an x and y coordinate
 */
public abstract class Entity {
	
	// IDs used in saving and loading
	public static final byte DESTRUCTIBLE_TILE = 0, FIRE_ENTITY = 1, PLAYER = 2, SPAWNER = 3, CENTAUR = 4, CYCLOPS = 5,
	        DEMON = 6, GANG_MEMBER = 7, MONKEY = 8, BAUTISTA = 9, DAUGHTER = 10, ISTRAHIIM = 11, JESUS = 12, JOBS = 13,
	        KNIGHT = 14, KOBE = 15, LORD_HILLSBOROUGH = 16, OCTAVIUS = 17, PEASANT = 18, RANCHERO = 19, SON = 20,
	        WIFE = 21, ZORRA = 22, COMPANION = 23, GORILLA = 24, NATIVE_AMERICAN = 25, POLICE_OFFICER = 26,
	        SWAT_OFFICER = 27, TECH_WARRIOR = 28, APARTMENT_HIGH_RISE = 29, CASTLE = 30, CASTLE_TOWER = 31,
	        CATHOLIC_CHAPEL = 32;
	// ..etc. Continue for all unique buildings and furniture

	// position on the screen
	private short x, y;
	
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
		this.x = (short) x;
	}

	/**
	 * Changes the entity vertically
	 * @param y the location to move the entity
	 */
	public void setY(int y) {
		this.y = (short) y;
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
		bounds.translate(dx, dy);
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
	
	/**
	 * @return Entity information as a string
	 */
	public String toString() {
		return "Point: (" + getX() + ", " + getY() +")";
	}
	
	/**
	 * Converts seconds to ticks
	 * @param seconds
	 * @return the proper tick count from seconds
	 */
	public static final int secondsToTicks(int seconds) {
		return seconds * 60;
	}
	
	/**
	 * @return X and Y data of the entity
	 * First 16 bits is X, second 16 bits is Y
	 */
	public final int getData() {
		return (x << 16) | y;
	}
	
	/**
	 * @return the ID of this entity
	 */
	public abstract byte getId();
	
}
