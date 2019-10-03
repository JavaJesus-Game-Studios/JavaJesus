package javajesus.entities.animals;

import javajesus.entities.npcs.NPC;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * An animal is just a little creature that has a unique movement pattern
 */
public abstract class Animal extends NPC {
	
	// how fast the npcs toggles steps
	protected static final int WALKING_ANIMATION_SPEED = 5;

	/**
	 * Creates an animal
	 * 
	 * @param level - level it is on
	 * @param name - name of the animal
	 * @param x - x coord
	 * @param y - y coord
	 * @param width - width in pixels
	 * @param height - height in pixels
	 * @param sheet - spritesheet to use
	 * @param yTile - y position on spritesheet
	 * @param color - the color set of the animal
	 * @param customRender - whether or not to use custom rendering
	 */
	public Animal(Level level, String name, int x, int y, int width, int height, SpriteSheet sheet, int yTile,
	        int[] color, boolean customRender) {
		super(level, name, x, y, 1, width, height, 100, color, 0, yTile, "cross", 10, customRender);

		// change the spritesheet
		this.setSpriteSheet(sheet);

	}

	/**
	 * Creates an animal
	 * 
	 * @param level - level it is on
	 * @param name - name of the animal
	 * @param x - x coord
	 * @param y - y coord
	 * @param width - width in pixels
	 * @param height - height in pixels
	 * @param sheet - spritesheet to use
	 * @param yTile - y position on spritesheet
	 * @param color - the color set of the animal
	 */
	public Animal(Level level, String name, int x, int y, int width, int height, SpriteSheet sheet, int yTile,
	        int[] color) {
		this(level, name, x, y, width, height, sheet, yTile, color, false);
	}

	@Override
	public int getStrength() {
		return 0;
	}

	@Override
	public int getDefense() {
		return 0;
	}

}
