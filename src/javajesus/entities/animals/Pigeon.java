package javajesus.entities.animals;

import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 *	A Pigeon Mob 
 */
public class Pigeon extends Animal {

	// how fast the npcs toggles steps
	private static final int WALKING_ANIMATION_SPEED = 3;

	// color of the Pigeon
	private static final int[] color = { 0xFF111111, 0xFF86889e, 0xFF34353a, 0xFF34353a, 0xFF636581 };

	/**
	 * @param level - level it is on
	 * @param x - x coord
	 * @param y - y coord
	 */
	public Pigeon(Level level, int x, int y) {
		super(level, "Pigeon", x, y, 8, 8, SpriteSheet.bipeds, 3, color, true);
	}

	/**
	 * Display the Pigeon on the screen
	 */
	public void render(Screen screen) {
		super.render(screen);

		// default color
		int[] color = Pigeon.color;

		// change color if knockback
		if (isHit) {
			color = mobHitColor;
		}

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal position on the spritesheet
		int xTile = 0;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile = 5 + (isMoving && flip ? 1 : 0);
		} else if (getDirection() == Direction.SOUTH) {
			if (isMoving && flip) {
				xTile += 1;
			}
		} else if (isLatitudinal()) {
			xTile = 2;
			if (isMoving) {
				xTile += 1 + (flip ? 1 : 0);
			}
			flip = getDirection() == Direction.WEST;
		}

		// the dead sprite has a certain location
		if (isDead()) {
			xTile = 7;
		}

		// render the square
		screen.render(xOffset, yOffset, xTile, yTile, getSpriteSheet(), flip, color);

	}

	@Override
	public byte getId() {
		return Entity.PIGEON;
	}

}