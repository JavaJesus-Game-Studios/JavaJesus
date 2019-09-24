package javajesus.entities;

import java.awt.Rectangle;

import javajesus.dataIO.EntityData;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.level.tile.Tile;

/*
 * A facade that acts like a tile but is actually an entity
 * Basically a tile but can be destroyed
 */
public class DestructibleTile extends Entity implements Damageable, SolidEntity, Type {

	// amount of times it must be hit before being destroyed
	private short health, maxHealth;
	
	// color set
	private int[] color;
	
	// which tile to display
	private int xTile, yTile;
	
	// spritesheet to render on
	private SpriteSheet sheet;
	
	// dummy shadow bounds
	private static final Rectangle shadow = new Rectangle();
	
	// ID of the tile
	private byte type;
	
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
		this.setBounds(Tile.snapToCorner(x) - 1, Tile.snapToCorner(y) - 1, Tile.SIZE + 1, Tile.SIZE + 1);
		this.health = maxHealth = (short) defaultHealth;
		this.xTile = tile.getXTile();
		this.yTile = tile.getYTile();
		this.color = tile.getColor();
		this.type = tile.getId();
		this.sheet = tile.getSpriteSheet();

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
		screen.render(getX(), getY(), xTile, yTile, sheet, false, color);
	}

	/**
	 * Gets the current health
	 */
	@Override
	public short getCurrentHealth() {
		return health;
	}

	/**
	 * Gets the max health
	 */
	@Override
	public short getMaxHealth() {
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

	@Override
	public long getData() {
		return EntityData.type4(getX(), getY(), maxHealth, type);
	}

	@Override
	public void setMaxHealth(short max) {
		maxHealth = max;
		
	}

	@Override
	public byte getType() {
		return type;
	}

	@Override
	public void setType(byte type) {
		this.type = type;
		
		// update the xtile and ytile info
		this.xTile = Tile.tileList[type & 0x000000FF].getXTile();
		this.yTile = Tile.tileList[type & 0x000000FF].getYTile();
		this.color = Tile.tileList[type & 0x000000FF].getColor();
		this.sheet = Tile.tileList[type & 0x000000FF].getSpriteSheet();
	}

	@Override
	public boolean isDead() {
		return health <= 0;
	}

	@Override
	public void onCollisionWithEntity(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRemovedCollisionWithEntity(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
