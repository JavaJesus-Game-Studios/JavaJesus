package game.entities.structures.trees;

import java.awt.Rectangle;

import game.entities.Entity;
import game.entities.SolidEntity;
import game.graphics.Screen;
import game.graphics.Sprite;
import level.Level;

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

		shadow = new Rectangle(sprite.getWidth(), sprite.getHeight() - 8);
		shadow.setLocation(x, y);

		// makes smoother collision points for the player
		setBounds(x + shadow.width / 2 - 3, y + shadow.height, 6, sprite.getHeight() - shadow.height);

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
