package javajesus.entities.monsters;

import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class Skeleton extends Monster {
	
	// dimensions of the bandito
	private static final int WIDTH = 16, HEIGHT = 16;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;
		
	// base stats
	private static final int BASE_STRENGTH = 7, BASE_DEFENSE = 5;
	
	// color set of bandito
	private static final int[] color =  { 0xFF111111, 0xFF700000, 0xFFDBA800 };

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Skeleton(Level level, int x, int y) {
		super(level, "Skeleton", x, y, 1, WIDTH, HEIGHT, 26, 100, 100);
	}
	
	/**
	 * Displays the Bandito to the screen
	 */
	public void render(Screen screen) {
		super.render(screen);
		
		// default color
		int[] color = Skeleton.color;

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
			xTile = 10;
		} else if (getDirection() == Direction.SOUTH) {
			xTile = 2;
		} else {
			xTile = 4 + (flip ? 2 : 0);
			flip = getDirection() == Direction.WEST;
		}

		// death image
		if (isDead())
			xTile = 12;

		// Upper body
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile, yTile, getSpriteSheet(), flip, color);

		// Upper body
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset, xTile + 1, yTile, getSpriteSheet(),
		        flip, color);

		// Lower Body
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile, yTile + 1, getSpriteSheet(),
		        flip, color);

		// Lower Body
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile + 1, yTile + 1,
		        getSpriteSheet(), flip, color);
	}

	@Override
	public int getStrength() {
		return BASE_STRENGTH;
	}

	@Override
	public int getDefense() {
		return BASE_DEFENSE;
	}

	@Override
	public byte getId() {
		return Entity.SKELETON;
	}

}
