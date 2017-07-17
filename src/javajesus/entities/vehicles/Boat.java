package javajesus.entities.vehicles;

import java.util.Random;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.level.tile.Tile;
import javajesus.utility.Direction;

/*
 * A boat can move on the water
 */
public class Boat extends Vehicle {

	private static final long serialVersionUID = -762864479769291428L;

	// colorset for this boat
	private int[] color;

	// randomly chooses colors
	private static final Random random = new Random();

	// size of each box
	private static final int UNIT_SIZE = 8;

	// used for offsetting the bounds based on direction
	private static final int SHORT_SIDE = 32, LONG_SIDE = 40;

	/**
	 * Creates a boat
	 * 
	 * @param level
	 *            the level it is on
	 * @param name
	 *            the name of the boat
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param speed
	 *            the speed of the boat
	 * @param defaultHealth
	 *            the default health of the boat
	 */
	public Boat(Level level, String name, int x, int y, int speed, int defaultHealth) {
		super(level, name, x, y, speed, SHORT_SIDE, LONG_SIDE, SpriteSheet.vehicles, defaultHealth);
		getColor();
	}

	/**
	 * Moves a car on the level
	 * 
	 * @param dx
	 *            the total change in x
	 * @param dy
	 *            the total change in y
	 */
	public void move(int dx, int dy) {

		// adjust the bounds depending on direction
		if (getDirection() == Direction.NORTH || getDirection() == Direction.SOUTH) {
			setBounds(getX(), getY(), SHORT_SIDE, LONG_SIDE);
		} else {
			setBounds(getX(), getY(), LONG_SIDE, SHORT_SIDE);
		}

		super.move(dx, dy);
	}

	/**
	 * Randomly selects a color
	 */
	private void getColor() {
		color = new int[] { 0xFF111111, 0xFF000000, 0xFFC2FEFF };
		switch (random.nextInt(8)) {
		case 0: {
			// red color
			color[1] = 0xFFFF0000;
			break;
		}
		case 1: {
			// gold color
			color[1] = 0xFFCFB53B;
			break;
		}
		case 2: {
			// blue color
			color[1] = 0xFF005AFF;
			break;
		}
		case 3: {
			// silver color
			color[1] = 0xFFCCCCCC;
			break;
		}
		case 4: {
			// black color
			color[1] = 0xFF111111;
			break;
		}
		case 5: {
			// green color
			color[1] = 0xFF066518;
			break;
		}
		case 6: {
			// purple color
			color[1] = 0xFF580271;
			break;
		}
		default: {
			// White
			color[1] = 0xFFFFFFFF;
			break;
		}
		}
	}

	/**
	 * Displays the boat on the screen
	 */
	public void render(Screen screen) {
		super.render(screen);

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// horizontal/vertical offset on spritesheet
		int xTile = 0, yTile = 16;

		// whether or not to render backwards horizontally
		boolean flipX = getDirection() == Direction.WEST;

		// whether or not to render backwards horizontally
		boolean flipY = getDirection() == Direction.NORTH;

		// gets the right offset
		if (getDirection() == Direction.EAST || getDirection() == Direction.WEST) {
			xTile = 4;
		}

		// renders the horizontal car
		if (getDirection() == Direction.EAST || getDirection() == Direction.WEST) {

			// iterate downwards
			for (int i = 0; i < 4; i++) {

				// Body 1
				screen.render(xOffset + (modifier * (flipX ? 4 : 0)), (yOffset + modifier * i),
						xTile + (yTile + i) * getSpriteSheet().getTilesPerRow(), color, getSpriteSheet());

				// Body 2
				screen.render(xOffset + modifier + (modifier * (flipX ? 2 : 0)), (yOffset + modifier * i),
						(xTile + 1) + (yTile + i) * getSpriteSheet().getTilesPerRow(), color, getSpriteSheet());

				// Body 3
				screen.render(xOffset + 2 * modifier, (yOffset + modifier * i),
						(xTile + 2) + (yTile + i) * getSpriteSheet().getTilesPerRow(), color, getSpriteSheet());

				// Body 4
				screen.render(xOffset + 3 * modifier - (modifier * (flipX ? 2 : 0)), (yOffset + modifier * i),
						(xTile + 3) + (yTile + i) * getSpriteSheet().getTilesPerRow(), color, getSpriteSheet());

				// Body 5
				screen.render(xOffset + 4 * modifier - (modifier * (flipX ? 4 : 0)), (yOffset + modifier * i),
						(xTile + 4) + (yTile + i) * getSpriteSheet().getTilesPerRow(), color, getSpriteSheet());

			}

		} else {

			// iterate sideways
			for (int i = 0; i < 5; i++) {

				// Body 1
				screen.render(xOffset + modifier * i, yOffset + (modifier * (flipY ? 4 : 0)),
						(xTile + i) + yTile * getSpriteSheet().getTilesPerRow(), color, flipY, getSpriteSheet());

				// Body 2
				screen.render(xOffset + modifier * i, yOffset + modifier + (modifier * (flipY ? 2 : 0)),
						(xTile + i) + (yTile + 1) * getSpriteSheet().getTilesPerRow(), color, flipY, getSpriteSheet());

				// Body 3
				screen.render(xOffset + modifier * i, yOffset + 2 * modifier,
						(xTile + i) + (yTile + 2) * getSpriteSheet().getTilesPerRow(), color, flipY, getSpriteSheet());

				// Body 4
				screen.render(xOffset + modifier * i, yOffset + 3 * modifier - (modifier * (flipY ? 2 : 0)),
						(xTile + i) + (yTile + 3) * getSpriteSheet().getTilesPerRow(), color, flipY, getSpriteSheet());

				// Body 5
				screen.render(xOffset + modifier * i, yOffset + 4 * modifier - (modifier * (flipY ? 4 : 0)),
						(xTile + i) + (yTile + 4) * getSpriteSheet().getTilesPerRow(), color, flipY, getSpriteSheet());

			}
		}

	}

	/**
	 * Checks if the position is not a water tile Called isSolidTile to conform
	 * to vehicle method
	 * 
	 * @param dx
	 *            the new x
	 * @param dy
	 *            the new y
	 * @param x
	 *            the x offset
	 * @param y
	 *            the y offset
	 * @return true if the new tile is not water
	 */
	protected boolean isSolidTile(int dx, int dy, int x, int y) {

		// tile the mob is on (bottom half)
		Tile lastTile = getLevel().getTile((getX() + x) >> 3, (getY() + y) >> 3);

		// tile the mob will move to
		Tile newTile = getLevel().getTile((getX() + x + dx) >> 3, (getY() + y + dy) >> 3);

		// checking the last tile allows the player to move through solids if
		// already on a solid
		if (!lastTile.equals(newTile) && (newTile != Tile.WATER)) {
			return true;
		}
		return false;
	}

}
