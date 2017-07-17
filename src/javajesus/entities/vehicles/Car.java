package javajesus.entities.vehicles;

import java.util.Random;

import javajesus.SoundHandler;
import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A car is a set of vehicles with similar shapes
 */
public class Car extends Vehicle {

	private static final long serialVersionUID = -1861142691248572564L;

	// colorset for this car
	private int[] color;

	// the offset on the tilesheet
	private int yTile;

	// randomly chooses colors
	private static final Random random = new Random();

	// size of each box
	private static final int UNIT_SIZE = 8;

	// used for offsetting the bounds based on direction
	private static final int SHORT_SIDE = 32, LONG_SIDE = 40;

	/**
	 * Creates a car
	 * 
	 * @param level
	 *            the level it is on
	 * @param name
	 *            the name of the car
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param yTile
	 *            the y tile on the spritesheet
	 */
	public Car(Level level, String name, int x, int y, int yTile) {
		super(level, name, x, y, 2, SHORT_SIDE, LONG_SIDE, SpriteSheet.vehicles, 200);
		getColor();
		this.yTile = yTile;
	}

	/**
	 * Updates the car
	 */
	public void tick() {
		super.tick();

		// plays sound
		if (isUsed()) {
			if (!isMoving()) {
				SoundHandler.playSmoothly(SoundHandler.carIdle);
			} else {
				SoundHandler.playSmoothly(SoundHandler.carDriving);
			}
		}

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
	 * Adds the player into the vehicle
	 * 
	 * @param player
	 *            the player to drive
	 */
	public void drive(Player player) {
		super.drive(player);
		SoundHandler.play(SoundHandler.carStartUp);
	}

	/**
	 * Randomly assigns a color
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
	 * Displays the car on the screen
	 */
	public void render(Screen screen) {
		super.render(screen);

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// horizontal offset on spritesheet
		int xTile = 0;

		// whether or not to render backwards horizontally
		boolean flipX = getDirection() == Direction.WEST;

		// gets the right offset
		if (getDirection() == Direction.EAST || getDirection() == Direction.WEST) {
			xTile = 4;
		} else if (getDirection() == Direction.NORTH) {
			xTile = 14;
		}
		
		// render a broken car
		if (isBroken()) {
			xTile += 18;
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
