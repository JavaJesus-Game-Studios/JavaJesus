package game.entities.monsters;

import game.ChatHandler;
import game.entities.Player;
import game.graphics.Screen;
import java.awt.Color;
import level.Level;
import utility.Direction;

/*
 * A monkey is a powerful monster that strikes fear into any foe
 */
public class Monkey extends Monster {

	private static final long serialVersionUID = -2503364257598403097L;

	// dimensions of the monkey
	private static final int WIDTH = 16, HEIGHT = 16;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	// color set of a monkey
	private static final int[] color = { 0xFF2A1609, 0xFF391E0C, 0xFFB08162 };

	/**
	 * Creates a monkey
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param speed
	 *            how fast the monkey moves
	 * @param health
	 *            the base health
	 */
	public Monkey(Level level, int x, int y, int speed, int health) {
		super(level, "Monkey", x, y, speed, WIDTH, HEIGHT, 8, health, 40);
	}

	/**
	 * Displays the cyclops to the screen
	 */
	public void render(Screen screen) {
		super.render(screen);

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE * getScale();

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal position on the spritesheet
		int xTile = 0;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile = 10;
		} else if (getDirection() == Direction.SOUTH) {
			xTile = 2;
		} else {
			xTile = 4 + (flip ? 2 : 0);
			flip = getDirection() == Direction.WEST;
		}

		// attacking animation
		if (isShooting) {
			if (getDirection() == Direction.NORTH) {
				xTile = 18;
			} else if (getDirection() == Direction.SOUTH) {
				xTile = 14;
			} else {
				xTile = 16 + (flip ? 2 : 0);
			}
		}

		// death image
		if (isDead())
			xTile = 12;

		// Upper body
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile + yTile * getSpriteSheet().boxes, color,
				flip, getScale(), getSpriteSheet());

		// Upper body
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset,
				(xTile + 1) + yTile * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		// Lower Body
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier,
				xTile + (yTile + 1) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		// Lower Body
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier,
				(xTile + 1) + (yTile + 1) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());
	}

	/**
	 * Text to player
	 */
	public void speak(Player player) {
		isTalking = true;
		ChatHandler.displayText("Chimp no speak with human.", Color.white);
		return;
	}

	/**
	 * @return the Cyclop's strength
	 */
	public int getStrength() {
		return 10;
	}
}
