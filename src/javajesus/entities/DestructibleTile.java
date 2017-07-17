package javajesus.entities;

import java.util.Random;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.level.tile.Tile;

/*
 * A facade that acts like a tile but is actually an entity
 * Basically a tile but can be destroyed
 * 
 * TODO implement damageable
 */
public class DestructibleTile extends Entity {

	// serialization
	private static final long serialVersionUID = 3662337682501677071L;
	
	// amount of times it must be hit before being destroyed
	private int health;
	
	// color set
	private int[] color;
	
	// which tile to display
	private int xTile, yTile;

	// used for generating random damage
	private static final Random random = new Random();
	
	/**
	 * Creates an entity that mimics a Tile
	 * @param level the current level
	 * @param x 
	 * @param y
	 * @param defaultHealth
	 */
	public DestructibleTile(Level level, int x, int y, int defaultHealth, int xTile, int yTile) {
		super(level, x, y);
		this.setBounds(x, y, Tile.SIZE, Tile.SIZE);
		this.health = defaultHealth;
		this.xTile = xTile;
		this.yTile = yTile;

	}

	/**
	 * TODO
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
	 * Randomizes the damage when attacked
	 * @param min the minimum damage dealt
	 * @param max the maximum damage dealt
	 */
	public void damage(int min, int max) {
		int damage = random.nextInt(max - min + 1) + min;
		this.health -= damage;
		if (health <= 0) {
			getLevel().remove(this);
		}
	}

	@Override
	public String toString() {
		return "Destructable Tile, " + super.toString();
	}

}
