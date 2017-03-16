package game.entities.structures;

import java.awt.Rectangle;

import game.entities.Entity;
import game.entities.SolidEntity;
import game.graphics.Screen;
import game.graphics.Sprite;
import level.Level;

/*
 * base class for all buildings
 */
public class Building extends Entity implements SolidEntity {

	private static final long serialVersionUID = -6892803773946189695L;

	// The area where the player can walk behind the building
	private Rectangle shadow;

	// the building texture
	private Sprite sprite;

	// the colorset
	private int[] color;

	/**
	 * Creates a building
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param color
	 *            the colorset
	 * @param sprite
	 *            the sprite
	 * @param ratio
	 *            the ratio of shadow to solid
	 */
	public Building(Level level, int x, int y, int[] color, Sprite sprite, double ratio) {
		super(level, x, y);

		this.sprite = sprite;
		this.color = color;

		shadow = new Rectangle(sprite.getWidth(), (int) (sprite.getHeight() * ratio));
		shadow.setLocation(x, y);

		this.setBounds(x, y + shadow.height, sprite.getWidth(), sprite.getHeight() - (int) shadow.getHeight() - 12);

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

}
