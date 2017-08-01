package javajesus.entities;

import java.awt.Rectangle;

import javajesus.dataIO.Serializable;
import javajesus.graphics.Screen;
import javajesus.level.Level;

/*
 * Something that is rendered on the screen on a level with an x and y coordinate
 */
public abstract class Entity implements Serializable {
	
	// IDs used in saving and loading
	public static final byte DESTRUCTIBLE_TILE = 0, FIRE_ENTITY = 1, SPAWNER = 3, CENTAUR = 4, CYCLOPS = 5,
            DEMON = 6, GANG_MEMBER = 7, MONKEY = 8, BAUTISTA = 9, DAUGHTER = 10, ISTRAHIIM = 11, JESUS = 12, JOBS = 13,
            KNIGHT = 14, KOBE = 15, LORD_HILLSBOROUGH = 16, OCTAVIUS = 17, PEASANT = 18, RANCHERO = 19, SON = 20,
            WIFE = 21, ZORRA = 22, COMPANION = 23, GORILLA = 24, NATIVE_AMERICAN = 25, POLICE_OFFICER = 26,
            SWAT_OFFICER = 27, TECH_WARRIOR = 28, APARTMENT_HIGH_RISE = 29, CASTLE = 30, CASTLE_TOWER = 31,
            CATHOLIC_CHAPEL = 32, CATHOLIC_CHURCH = 33, CAVE_ENTRANCE = 34, FACTORY = 35, GENERIC_HOSPITAL = 36,
            GUNSTORE = 37, HOTEL = 38, HUT = 39, MINESHAFT = 40, NICE_HOUSE = 41,
            NICE_HOUSE2 = 43, POLICE = 44, POOR_HOUSE = 45, PRISON = 46, PROJECTS = 47, RANCHERO_HOUSE = 48, 
            REFUGEE_TENT = 49, RUSSIAN_ORTHODOX_CHURCH = 50, SHANTY_HOUSE = 51, SKYSCRAPER = 52,
            TIPPEE = 53, WAREHOUSE = 54, GREAT_TREE = 55, TREE_HOUSE = 56, UC_GRIZZLY = 57, 
            OAKWOOD_CITY_HALL = 58, CHINATOWN_HOUSE = 59, RUSSIAN_CLUB = 60, SAN_CISCO_CITY_HALL = 61,
            SAN_CISCO_SKYSCRAPER = 62, TRIAD_HQ = 63, JUNGLE_HQ = 64, QUACKER_HQ = 65, SAN_JUAN_CITY_HALL = 66,
            THE_HUB = 67, SEQUOIA_CINEMA = 68, SEQUOIA_SCHOOL = 69, CAFE = 70, CARDINAL_UNIVERSITY = 71,
            PEAR_HQ = 72, RADAR_DISH = 73, TECHTOPIA_CITY_HALL = 74, WEIRD_TECH_BUILDING1 = 75,
            WEIRD_TECH_BUILDING2 = 76, BED = 77, BENCH = 78, CHAIR_FRONT = 79, CHAIR_SIDE = 80, CHEST = 81,
            COMPUTER_MONITOR = 82, COMPUTER_TOWER = 83, DINING_TABLE = 84, FILING_CABINET = 85, 
            LONG_TABLE = 86, NIGHTSTAND = 87, SOFA = 88, SQUARE_TABLE = 89, STOOL = 90, TELEVISION = 91,
            THRONE = 92, DEAD_SEQUOIA = 93, GENERIC_TREE = 94, LARGE_SEQUOIA = 95, MEDIUM_SEQUOIA = 96,
            SMALL_SEQUOIA = 97, BOAT = 98, CENTURY_LESABRE = 99, HORSE = 100, SPORTS_CAR = 101, TRUCK = 102, MODERN_SKYSCRAPER = 103,
            COW = 104, FOX = 105, DOG = 106, CAT = 107;
	
	// number of unique entities worth saving
	public static final int NUM_ENTITIES = 103;

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
	public short getX() {
		return x;
	}

	/**
	 * @return the Y coordinate of the entity
	 */
	public short getY() {
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
	public void moveTo(int x, int y) {
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
	 * @return the ID of this entity
	 */
	public abstract byte getId();
	
}
