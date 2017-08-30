package javajesus.entities.monsters;

import java.awt.Color;

import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class EvilOrangutan extends Monster {

	// dimensions of the EvilOrangutan
	private static final int WIDTH = 24, HEIGHT = 24;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	// color set of orangutan
	private static final int[] color = { 0xFF111111, 0xFF700000, 0xFFDBA800, 0, 0 };

	/**
	 * Creates a generic EvilOrangutan with different abilities
	 * 
	 * @param level the level it is on
	 * @param x the x coord
	 * @param y the y coord
	 * @param defaultHealth the base health
	 * @param walkPath the walk pattern
	 * @param walkDistance the walk distance
	 */
	public EvilOrangutan(Level level, int x, int y, int defaultHealth, String walkPath, int walkDistance) {
		super(level, "EvilOrangutan", x, y, 1, WIDTH, HEIGHT, 23, 400, 50);

	}

	/**
	 * Default EvilOrangutan
	 * 
	 * @param level the level it is on
	 * @param x the x coord
	 * @param y the y coord
	 */
	public EvilOrangutan(Level level, int x, int y) {
		this(level, x, y, 200, "", 0);
	}

	/**
	 * Displays the EvilOrangutan on the screen
	 */
	public void render(Screen screen) {

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal position on the spritesheet
		int xTile = 0;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// get spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile += 15;
		} else if (getDirection() == Direction.SOUTH) {
			xTile += 3;
		} else {
			xTile += 6 + (flip ? 3 : 0);
			flip = getDirection() == Direction.WEST;
		}

		// attacking animation
		if (isShooting) {
			if (getDirection() == Direction.NORTH) {
				xTile = 27;
			} else if (getDirection() == Direction.SOUTH) {
				xTile = 21;
			} else {
				xTile = 24 + (flip ? 3 : 0);
				flip = getDirection() == Direction.WEST;
			}
		}

		// absolute x tile for dead sprite
		if (isDead())
			xTile = 18;

		// render by row
		for (int i = 0; i < 3; i++) {

			screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + (modifier * i), xTile, yTile + i,
			        getSpriteSheet(), flip, color);

			screen.render(xOffset + modifier, yOffset + (modifier * i), xTile + 1, yTile + i, getSpriteSheet(), flip,
			        color);

			screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + (modifier * i), xTile + 2,
			        yTile + i, getSpriteSheet(), flip, color);
		}
	}

	/**
	 * Speech options for EvilOrangutan
	 */
	public void doDialogue() {
		MessageHandler.displayText(getName() + ": Orangutan no like human.", Color.white);
		return;
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 15;
	}

	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getId() {
		return Entity.EVIL_ORANGUTAN;
	}

}
