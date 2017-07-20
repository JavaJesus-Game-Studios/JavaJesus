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
	 * @param level - the level it is on
	 * @param name - the name of the boat
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param speed - the speed of the boat
	 * @param defaultHealth - the default health of the boat
	 */
	public Boat(Level level, int x, int y, int defaultHealth) {
		super(level, "Boat", x, y, 2, LONG_SIDE, SHORT_SIDE, SHORT_SIDE, LONG_SIDE, SpriteSheet.vehicles, defaultHealth);
		getColor();
	}
	
	/**
	 * Checks the type of tile in a new position
	 * Reverses the criteria for boats
	 * 
	 * @param x - the x offset from current value
	 * @param y - the y offset from current value
	 * @param dx - the change in x
	 * @param dy - the change in y
	 * @return true if the new tile is solid
	 */
	@Override
	protected boolean isWaterTile(int x, int y, int dx, int dy) {
		return getLevel().getTileFromEntityCoords(getX() + x + dx,
				getY() + y + dy).getId() != Tile.SEA1.getId() 
				||getLevel().getTileFromEntityCoords(getX() + x + dx,
					getY() + y + dy).getId() != Tile.SEA2.getId()
				||getLevel().getTileFromEntityCoords(getX() + x + dx,
					getY() + y + dy).getId() != Tile.SEA3.getId()
				||getLevel().getTileFromEntityCoords(getX() + x + dx,
					getY() + y + dy).getId() != Tile.SEA4.getId();
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
		int xTile = 0, yTile = 15;

		// whether or not to render backwards horizontally
		boolean flipX = getDirection() == Direction.WEST;

		// gets the right offset
		if (getDirection() == Direction.EAST || getDirection() == Direction.WEST) {
			xTile = 4;
		} else if (getDirection() == Direction.NORTH) {
			xTile = 14;
		}

		// renders the horizontal car
		if (getDirection() == Direction.EAST || getDirection() == Direction.WEST) {

			// iterate downwards
			for (int i = 0; i < 4; i++) {

				// Body 1
				screen.render(xOffset + (modifier * (flipX ? 4 : 0)), (yOffset + modifier * i), xTile, yTile + i,
				        getSpriteSheet(), flipX, color);

				// Body 2
				screen.render(xOffset + modifier + (modifier * (flipX ? 2 : 0)), (yOffset + modifier * i), xTile + 1,
				        yTile + i, getSpriteSheet(), flipX, color);

				// Body 3
				screen.render(xOffset + 2 * modifier, (yOffset + modifier * i), xTile + 2, yTile + i, getSpriteSheet(),
				        flipX, color);

				// Body 4
				screen.render(xOffset + 3 * modifier - (modifier * (flipX ? 2 : 0)), (yOffset + modifier * i),
				        xTile + 3, yTile + i, getSpriteSheet(), flipX, color);

				// Body 5
				screen.render(xOffset + 4 * modifier - (modifier * (flipX ? 4 : 0)), (yOffset + modifier * i),
				        xTile + 4, yTile + i, getSpriteSheet(), flipX, color);

			}

		} else {

			// top to bottom
			for (int i = 0; i < 5; i++) {

				// left to right
				for (int j = 0; j < 4; j++) {

					// render the segment
					screen.render(xOffset + modifier * j, yOffset + modifier * i, xTile + j, yTile + i,
					        getSpriteSheet(), false, color);
				}

			}
		}

	}

}
