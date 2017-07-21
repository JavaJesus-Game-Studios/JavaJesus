package javajesus.entities;

import java.awt.Rectangle;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.level.tile.Tile;

/*
 * A facade that acts like a tile but is actually an entity
 * Basically a tile but can be destroyed
 */
public class DestructibleTile extends Entity implements Damageable, SolidEntity {

	// amount of times it must be hit before being destroyed
	private int health, maxHealth;
	
	// color set
	private int[] color;
	
	// which tile to display
	private int xTile, yTile;
	
	// dummy shadow bounds
	private static final Rectangle shadow = new Rectangle();
	
	/**
	 * Creates an entity that mimics a Tile
	 * @param level the current level
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param tile - the tile to imitate
	 * @param defaultHealth - the health of the tile
	 */
	public DestructibleTile(Level level, int x, int y, Tile tile, int defaultHealth) {
		super(level, Tile.snapToCorner(x), Tile.snapToCorner(y));
		
		// instance data
		this.setBounds(Tile.snapToCorner(x), Tile.snapToCorner(y), Tile.SIZE, Tile.SIZE);
		this.health = maxHealth = defaultHealth;
		this.xTile = tile.getXTile();
		this.yTile = tile.getYTile();
		this.color = tile.getColor();

	}
	
	/**
	 * Creates an entity that mimics a Tile
	 * @param level the current level
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public DestructibleTile(Level level, int x, int y) {
		this(level, x, y, Tile.NICE_WALL_DWN, 30);
	}

	/**
	 * Does nothing
	 */
	public void tick() {

	}

	/**
	 * Sends the pixels to the screen to be processed
	 */
	public void render(Screen screen) {
		screen.render(getX(), getY(), xTile, yTile, SpriteSheet.tiles, false, color);
	}

	/**
	 * Gets the current health
	 */
	@Override
	public int getCurrentHealth() {
		return health;
	}

	/**
	 * Gets the max health
	 */
	@Override
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Damages this entity
	 */
	@Override
	public void damage(int damage) {
		
		// remove the health
		health -= damage;
		
		// remove if  no health
		if (health <= 0) {
			getLevel().remove(this);
		}
		
	}

	/**
	 * @return the shadow of the tile
	 */
	@Override
	public Rectangle getShadow() {
		return shadow;
	}

	@Override
	public byte getId() {
		return Entity.DESTRUCTIBLE_TILE;
	}

}
