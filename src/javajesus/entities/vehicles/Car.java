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

	// serialization
	private static final long serialVersionUID = -1861142691248572564L;

	// colorset for this car
	private int[] color;

	// the offset on the tilesheet
	private int yTile;

	// randomly chooses colors
	private static final Random random = new Random();

	// size of each box
	private static final int UNIT_SIZE = 8;

	/**
	 * Creates a car
	 * 
	 * @param level - the level it is on
	 * @param name - the name of the car
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param shortSide - the length of the short side of the car
	 * @param longSide - the length of the long side of the car
	 * @param yTile - the y tile on the spritesheet
	 */
	public Car(Level level, String name, int x, int y, int shortSide,
			int longSide, int yTile) {
		super(level, name, x, y, 2, longSide, shortSide, shortSide, longSide,
				SpriteSheet.vehicles, 200);
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
	 * Adds the player into the vehicle
	 * 
	 * @param player the player to drive
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

		// gets the right offset
		if (getDirection() == Direction.EAST) {
			xTile = 4;
		} else if (getDirection() == Direction.WEST) {
			xTile = 9;
		} else if (getDirection() == Direction.NORTH) {
			xTile = 14;
		}

		// render a broken car
		if (isBroken()) {
			xTile += 18;
		}

		// renders the horizontal car
		if (getDirection() == Direction.EAST
				|| getDirection() == Direction.WEST) {

			// top to bottom
			for (int i = 0; i < 4; i++) {

				for (int j = 0; j < 5; j++) {

					// render the segment
					screen.render(xOffset + modifier * j, yOffset + modifier
							* i, xTile + j, yTile + i, getSpriteSheet(), false,
							color);
				}

			}

		} else {

			// top to bottom
			for (int i = 0; i < 5; i++) {

				// left to right
				for (int j = 0; j < 4; j++) {

					// render the segment
					screen.render(xOffset + modifier * j, yOffset + modifier
							* i, xTile + j, yTile + i, getSpriteSheet(), false,
							color);
				}

			}
		}

	}

}
