package javajesus.entities.animals;

import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 *	A Cow Mob 
 */
public class Cow extends Animal {

	// color of the fox
	private static final int[] color = { 0xFF111111, 0xFFFFFFFF, 0xFFEDC5AB };

	// how fast the npcs toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	/**
	 * @param level - level it is on
	 * @param x - x coord
	 * @param y - y coord
	 */
	public Cow(Level level, int x, int y) {
		super(level, "Cow", x, y, 24, 20, SpriteSheet.quadrapeds, 3, color, true);
	}

	@Override
	public byte getId() {
		return Entity.COW;
	}

	/**
	 * Display the cow on the screen
	 */
	public void render(Screen screen) {
		super.render(screen);

		// default color
		int[] color = Cow.color;

		// change color if knockback
		if (isHit) {
			color = mobHitColor;
		}

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal position on the spritesheet
		int xTile = 0;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile += 10;
			if (isMoving)
				xTile += 2;
		} else if (getDirection() == Direction.SOUTH) {
			if (isMoving)
				xTile += 2;
		} else if (isLatitudinal()) {
			xTile += 4;
			if (isMoving)
				xTile += (flip ? 3 : 0);
			flip = getDirection() == Direction.WEST;
		}

		// the dead sprite has a certain location
		if (isDead()) {
			if (isLongitudinal()) {
				setDirection(Direction.EAST);
			}
			xTile = 14;
		}

		// standing vertical
		if (isLongitudinal()) {
			// Upper body
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile, yTile, getSpriteSheet(), flip, color);

			// Upper body
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset, xTile + 1, yTile, getSpriteSheet(),
			        flip, color);

			// Middle Body
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile, yTile + 1, getSpriteSheet(),
			        flip, color);

			// Middle Body
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile + 1, yTile + 1,
			        getSpriteSheet(), flip, color);

			// Lower Body
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier, xTile, yTile + 2,
			        getSpriteSheet(), flip, color);

			// Lower Body
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier, xTile + 1,
			        yTile + 2, getSpriteSheet(), flip, color);
		}
		// standing horizontal
		else {

			// loop through top to bottom
			for (int i = 0; i < 3; i++) {

				// left
				screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + (modifier * i), xTile, yTile + i,
				        getSpriteSheet(), flip, color);

				// middle
				screen.render(xOffset + modifier, yOffset + (modifier * i), xTile + 1, yTile + i, getSpriteSheet(),
				        flip, color);

				// right
				screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + (modifier * i), xTile + 2,
				        yTile + i, getSpriteSheet(), flip, color);
			}
		}

	}

}
