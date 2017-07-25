package javajesus.entities.solid.buildings;

import java.awt.Rectangle;

import javajesus.dataIO.EntityData;
import javajesus.entities.Entity;
import javajesus.entities.SolidEntity;
import javajesus.graphics.Screen;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * base class for all buildings
 * TODO make Building abstract and make each building override getId()
 */
public abstract class Building extends Entity implements SolidEntity {

	// The area where the player can walk behind the building
	private final Rectangle shadow;

	// the building texture
	private final Sprite sprite;

	// the colorset
	private final int[] color;

	/**
	 * Creates a building
	 * 
	 * @param level - the level it is on
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param color - the colorset
	 * @param sprite - the sprite
	 * @param height - the height of the base of the building
	 */
	public Building(final Level level, int x, int y, final int[] color, final Sprite sprite, int height) {
		super(level, x, y);

		// instance data
		this.sprite = sprite;
		this.color = color;

		// set bounds
		shadow = new Rectangle(x, y, sprite.getWidth(), (int) (sprite.getHeight() - height));
		setBounds(x, y + shadow.height, sprite.getWidth(), height);

	}
	
	/**
	 * Creates a building
	 * 
	 * @param level - the level it is on
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param color - the colorset
	 * @param sprite - the sprite
	 */
	public Building(final Level level, int x, int y, final int[] color, final Sprite sprite) {
		this(level, x, y, color, sprite, 18);
	}

	/**
	 * Get the bounds behind the building
	 */
	public Rectangle getShadow() {
		return shadow;
	}

	/**
	 * Doesn't do anything for buildings
	 */
	public void tick() {

	}

	/**
	 * Renders the building on the screen
	 */
	public void render(Screen screen) {
		screen.render(getX(), getY(), color, sprite);

	}
	
	@Override
	public long getData() {
		return EntityData.type1(getX(), getY());
	}
	
}
