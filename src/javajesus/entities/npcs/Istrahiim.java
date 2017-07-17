package javajesus.entities.npcs;

import javajesus.MessageHandler;
import javajesus.graphics.Screen;
import javajesus.level.Level;
import javajesus.utility.Direction;

import java.awt.Color;

/*
 * Istrahiim, the tall dude
 */
public class Istrahiim extends NPC {

	private static final long serialVersionUID = -344533173854730970L;

	// how fast the npcs toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	// dimensions Istrahiim
	private static final int WIDTH = 16, HEIGHT = 24;

	/**
	 * Creates Istrahiim
	 * 
	 * @param level
	 *            the level he is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public Istrahiim(Level level, int x, int y) {
		super(level, "Istrahiim", x, y, 1, WIDTH, HEIGHT, 200, null, 0, 25, "", 0);
	}

	/**
	 * Istrahiim's dialogue options
	 */
	public void doDialogue() {
		MessageHandler.displayText(getName() + ": Hello, I am not an owl. My Name is Istrahiim.", Color.white);
		return;
	}

	/**
	 * Displays Istrahiim on the screen
	 */
	public void render(Screen screen) {

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal/vertical position on the spritesheet
		int xTile = this.xTile;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile += 12 + (flip ? 1 : 0);
			if (!isMoving) {
				xTile = 10;
			}
		} else if (getDirection() == Direction.SOUTH) {
			xTile += 2 + (flip ? 1 : 0);
			if (!isMoving) {
				xTile = 0;
			}
			flip = false;
		} else if (isLatitudinal()) {
			xTile += 6 + (flip ? 1 : 0);
			if (!isMoving) {
				xTile = 6;
			}
			flip = getDirection() == Direction.WEST;
		}

		// absolute position of dead body
		if (isDead()) {
			xTile = 16;
			int yTile = this.yTile + 1;

			// render by row
			for (int i = 0; i < 2; i++) {

				screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + (modifier * i), xTile, yTile + i,
				        getSpriteSheet(), flip);

				screen.render(xOffset + modifier, yOffset + (modifier * i), xTile + 1, yTile + i, getSpriteSheet(),
				        flip);

				screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + (modifier * i), xTile + 2,
				        yTile + i, getSpriteSheet(), flip);
			}
		} else {
			// render by row
			for (int i = 0; i < 3; i++) {
				// render by column TODO check if this works
				for (int j = 0; j < 2; j++) {
					screen.render(xOffset + (modifier * j), yOffset + (modifier * i), xTile + j, yTile + i,
					        getSpriteSheet(), flip);
				}
			}
		}
	}

}
