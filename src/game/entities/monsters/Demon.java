package game.entities.monsters;

import level.Level;
import utility.Direction;
import game.entities.Mob;
import game.entities.projectiles.FireBall;
import game.graphics.Screen;

/*
 * A generic demon that populates most of the game
 */
public class Demon extends Monster {

	private static final long serialVersionUID = 1670392462486505990L;

	// dimensions of the centaur
	private static final int WIDTH = 16, HEIGHT = 24;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	// color set of a centaur
	private static final int[] color = { 0xFF111111, 0xFF700000, 0xFFDBA800 };

	/**
	 * Creates a Demon
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param speed
	 *            how fast the demon moves
	 * @param health
	 *            the base health
	 */
	public Demon(Level level, int x, int y, int speed, int health) {
		super(level, "Demon", x, y, speed, WIDTH, HEIGHT, 0, health, 100);

	}

	/**
	 * Displays the Demon to the screen
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
		if (isShooting)
			xTile += 12;

		// dead has an absolute position
		if (isDead()) {
			if (isLongitudinal()) {
				setDirection(Direction.WEST);
			}
			xTile = 24;
		}

		// only a living demon has a top layer
		if (!isDead()) {

			// Upper body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile + yTile * getSpriteSheet().boxes, color,
					flip, getScale(), getSpriteSheet());

			// Upper body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset,
					(xTile + 1) + yTile * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());
		}

		// Middle Body 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier,
				xTile + (yTile + 1) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		// Middle Body 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier,
				(xTile + 1) + (yTile + 1) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		// Lower Body 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier,
				xTile + (yTile + 2) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier,
				(xTile + 1) + (yTile + 2) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		// dead bodies have an extended segment
		if (isDead()) {

			int offset = getDirection() == Direction.WEST ? -16 : 0;

			// Middle Body 3
			screen.render(xOffset + offset + 2 * modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier,
					(xTile + 2) + (yTile + 1) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

			// Lower Body 3
			screen.render(xOffset + offset + 2 * modifier - (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier,
					(xTile + 2) + (yTile + 2) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		}

	}

	/**
	 * Throws a fireball at a target Uses dummy parameters to conform to Mob
	 * class
	 */
	@Override
	public void attack(int fake, int fake2, Mob other) {

		getLevel().add(new FireBall(getLevel(), getX(), getY(), target.getX(), target.getY(), this, getStrength()));
	}

	/**
	 * Sets the demon's strength
	 */
	@Override
	public int getStrength() {
		return 3;
	}

}
