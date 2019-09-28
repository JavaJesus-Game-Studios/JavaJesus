package javajesus.entities.effects;

import javajesus.graphics.Screen;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/**
 * 
 * @author JJ Game Studios
 * Class that handles creating and rendering shadows underneath entities.
 */
public class Shadow {
	
	private Sprite sprite;

	// Color of the Shadow
	private static final int[] shadowColor = {0xFF1e1e1e};
	// Amount to blend shadowColor with the background Color
	private static final float alpha = 0.80f;
	
	private static final int TILE_SIZE = 8;
	
	private int height;
	private int width = 0;
	
	
	/**
	 * Constructor for mob
	 * @param level the level to render the shadow on
	 * @param width the width of the entity's collision box, used to determine shadow sprite
	 * @param isDead determines whether to use the dead shadow sprite
	 * @param isChild determines whether to use the child shadow sprite
	 */
	public Shadow(Level level, int width, int height, boolean isDead, boolean isChild) {
		this.height = height;
		setSprite(width, isDead, isChild);
	}
	
	/**
	 * Constructor for generic entities
	 * @param level the level to render the shadow on
	 * @param x the x coordinate of the shadow (upper left hand coord)
	 * @param y the y coordinate of the shadow (upper left hand coord)
	 * @param width the width of the entity's collision box, used to determine shadow sprite
	 */
	public Shadow(Level level, int width, int height) {
		this.height = height;
		boolean isDead = false;
		boolean isChild = false;
		setSprite(width, isDead, isChild);
	}
	/**
	 * Displays the shadow on the screen
	 */
	public void render(Screen screen, int x, int y) {
		// Blend the Colors to create transparent shadow
		screen.renderTransparency(x + width, y + height, shadowColor, alpha, sprite);
	}
	/**
	 * Displays the shadow on the screen, allows custom alpha
	 */
	public void render(Screen screen, int x, int y, float alpha) {
		// Blend the Colors to create transparent shadow
		screen.renderTransparency(x + width, y + height, shadowColor, alpha, sprite);
	}
	/**
	 * 
	 * @param width determines the size of the shadow sprite
	 * @param isDead determines whether to use the dead shadow
	 * @param isChild determines whether to use the child shadow
	 */
	public void setSprite(int width, boolean isDead, boolean isChild) {
		switch( width ) {
			case (TILE_SIZE):
				sprite = Sprite.SHADOW_1X1;
				height -= TILE_SIZE - 1;
				break;
			case (TILE_SIZE * 2):
				if( !isDead && !isChild ) {
					sprite = Sprite.SHADOW_2X2;
					height -= TILE_SIZE;
					break;
				} else if( !isDead && isChild ) {
					sprite = Sprite.SHADOW_SMALL2X2;
					height -= TILE_SIZE;
					break;
				} else if( isDead && !isChild ) {
					sprite = Sprite.SHADOW_DEAD2X2;
					height -= TILE_SIZE;
					break;
				}
			case (TILE_SIZE * 3):
				if( !isDead ) {
					sprite = Sprite.SHADOW_DEAD3X3;
					height -= (2 * TILE_SIZE);
					break;
				} else {
					sprite = Sprite.SHADOW_3X3;
					height -= (2 * TILE_SIZE);
					break;
				}
			case (TILE_SIZE * 4):
				sprite = Sprite.SHADOW_4X4;
				height -= (2 * TILE_SIZE);
				break;
			case (TILE_SIZE * 5):
				sprite = Sprite.SHADOW_5X5;
				height -= (2 * TILE_SIZE);
				break;
			default:
				sprite = Sprite.SHADOW_2X2;
				height -= (TILE_SIZE) - 2;
				}
	}


}
