package javajesus.entities.animals;

import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 *	A Duck Mob 
 */
public class Duck extends Animal {

	// how fast the npcs toggles steps
	private static final int WALKING_ANIMATION_SPEED = 3;

	// color of the Duck
	private static final int[] color = { 0xFF111111, 0xFFFFFFFF, 0xFFffdd03, 0xFFff9203, 0 };

	/**
	 * @param level - level it is on
	 * @param x - x coord
	 * @param y - y coord
	 */
	public Duck(Level level, int x, int y) {
		super(level, "Duck", x, y, 8, 16, SpriteSheet.bipeds, 1, color, true);
	}

	/**
	 * Display the duck on the screen
	 */
	public void render(Screen screen) {
		super.render(screen);

		// default color
		int[] color = Duck.color;

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

		// render the squares
		if(isSwimming)
			screen.render(xOffset, yOffset + 8, xTile, yTile, getSpriteSheet(), flip, color);
		else {
			screen.render(xOffset, yOffset, xTile, yTile, getSpriteSheet(), flip, color);
			screen.render(xOffset, yOffset + modifier, xTile, yTile + 1, getSpriteSheet(), flip, color);
		}

	}

	@Override
	public byte getId() {
		return Entity.DUCK;
	}

}