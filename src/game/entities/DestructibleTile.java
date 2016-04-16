package game.entities;

import game.graphics.Screen;
import game.graphics.SpriteSheet;

import java.util.Random;

import level.Level;
import level.tile.Tile;

/*
 * A facade that acts like a tile but is actually an entity
 * Basically a tile but can be destroyed
 */
public class DestructibleTile extends Entity {

	private static final long serialVersionUID = 3662337682501677071L;
	
	// amount of times it must be hit before being destroyed
	private int health;
	
	// color set
	private int[] color;
	
	// which tile to display
	private int tileNumber;

	// used for generating random damage
	private static final Random random = new Random();
	
	/**
	 * Creates an entity that mimics a Tile
	 * @param level the current level
	 * @param x 
	 * @param y
	 * @param defaultHealth
	 */
	public DestructibleTile(Level level, int x, int y, int defaultHealth) {
		super(level, x, y);
		this.setBounds(x, y, Tile.SIZE, Tile.SIZE);
		this.health = defaultHealth;

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
		screen.render(getX(), getY(), tileNumber, color, SpriteSheet.tiles);
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
			getLevel().remEntity(this);
		}
	}

	@Override
	public String toString() {
		return "Destructable Tile, " + super.toString();
	}

}