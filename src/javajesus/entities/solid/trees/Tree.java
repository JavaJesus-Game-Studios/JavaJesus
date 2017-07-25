package javajesus.entities.solid.trees;

import java.awt.Rectangle;

import javajesus.dataIO.EntityData;
import javajesus.entities.Entity;
import javajesus.entities.SolidEntity;
import javajesus.graphics.Screen;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * A tree cannot be clipped
 */
public abstract class Tree extends Entity implements SolidEntity {

	// The area where the player can walk behind the tree
	private Rectangle shadow;

	// the tree texture
	private Sprite sprite;

	/**
	 * Creates a tree from a specified sprite
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param sprite - the sprite to use
	 * @param width - the trunk width in pixels
	 * @param height - the trunk height in pixels
	 *  MUST BE AT LEAST 8 to ensure front rendering
	 * @param xOffset - the pixels on the left side of the trunk
	 */
	public Tree(Level level, int x, int y, Sprite sprite, int width, int height, int xOffset) {
		super(level, x, y);

		this.sprite = sprite;

		shadow = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight() - height);

		// makes smoother collision points for the player
		setBounds(x + xOffset, y + shadow.height, width, height);

	}

	/**
	 * Displays the tree on the screen
	 */
	public void render(Screen screen) {
		screen.render(getX(), getY(), null, sprite);
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
	public void tick() {}
	
	@Override
	public long getData() {
		return EntityData.type1(getX(), getY());
	}

}
