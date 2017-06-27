package javajesus.entities.structures.trees;

import java.awt.Rectangle;

import javajesus.entities.Entity;
import javajesus.entities.SolidEntity;
import javajesus.graphics.Screen;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * A tree cannot be clipped
 */
public class Tree extends Entity implements SolidEntity {

	private static final long serialVersionUID = 5310167130019869321L;

	// The area where the player can walk behind the tree
	private Rectangle shadow;

	// the tree texture
	private Sprite sprite;

	/**
	 * Creates a tree from a specified sprite
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param sprite
	 *            the sprite to use
	 */
	public Tree(Level level, int x, int y, Sprite sprite) {
		super(level, x, y);

		this.sprite = sprite;

		shadow = new Rectangle(sprite.getWidth(), sprite.getHeight() - 16);
		shadow.setLocation(x, y);

		// makes smoother collision points for the player
		setBounds(x + 4, y + shadow.height, 1, sprite.getHeight() - shadow.height - 8);

	}

	/**
	 * Displays the tree on the screen
	 */
	public void render(Screen screen) {
		screen.render(getX(), getY(), null, sprite);
	}

	/**
	 * @return The height of the tree
	 */
	public int getHeight() {
		return sprite.getHeight();
	}

	/**
	 * @return the area behind the tree
	 */
	public Rectangle getShadow() {
		return shadow;
	}

	/**
	 * Trees don't really update
	 */
	public void tick() {
		
	}

}
