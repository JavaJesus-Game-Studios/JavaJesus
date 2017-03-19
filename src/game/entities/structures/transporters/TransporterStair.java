package game.entities.structures.transporters;

import java.awt.Point;

import game.graphics.Screen;
import game.graphics.SpriteSheet;
import level.Level;
import utility.Direction;

/*
 * Stairs
 */
public class TransporterStair extends Transporter {

	private static final long serialVersionUID = -2539827489488693710L;

	// the different textures for stairs
	public static final int WOOD = 11, STONE = 7, CARPET = 9;

	// different colors for different textures
	private static final int[] COLOR_WOOD = { 0xFF111111, 0xFF704200, 0xFF000000 },
			COLOR_STONE = { 0xFF4c4c4c, 0xFF909090, 0xFF636363 }, COLOR_CARPET = { 0xFF111111, 0xFF704200, 0xFF1F7901 };

	// the direction the stairs are facing
	private Direction direction;

	// the offset on the spritesheet
	private int xTile;

	// the colorset
	private int[] color;

	// size of each tile
	private static final int SIZE = 8;

	/**
	 * Creates stairs
	 * 
	 * @param currentLevel
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param nextLevel
	 *            the level it goes to
	 * @param direction
	 *            the direction the stairs are facing
	 * @param type
	 *            the material of the stairs, Ex: TransporterStair.WOOD
	 */
	public TransporterStair(Level currentLevel, int x, int y, Level nextLevel, Direction direction, int type) {
		super(currentLevel, x, y, nextLevel);

		this.direction = direction;
		xTile = type;
		assignColor();
	}

	/**
	 * Creates stairs and sets a spawnpoint for the next level
	 * 
	 * @param currentLevel
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param nextLevel
	 *            the level it goes to
	 * @param direction
	 *            the direction the stairs are facing
	 * @param type
	 *            the material of the stairs, Ex: TransporterStair.WOOD
	 */
	public TransporterStair(Level currentLevel, int x, int y, Level nextLevel, Point point, Direction direction,
			int type) {
		super(currentLevel, x, y, nextLevel, point);

		this.direction = direction;
		xTile = type;
		assignColor();
	}

	/**
	 * Assigns the appropriate color for the type
	 */
	private void assignColor() {
		switch (xTile) {
		case WOOD:
			color = COLOR_WOOD;
			break;
		case STONE:
			color = COLOR_STONE;
			break;
		default:
			color = COLOR_CARPET;
			break;
		}
	}

	/**
	 * Displays the stairs on the screen
	 */
	public void render(Screen screen) {

		switch (direction) {
		case NORTH:
			screen.render(getX(), getY(), xTile + 6 * SpriteSheet.tiles.getNumBoxes(), color, SpriteSheet.tiles);
			screen.render(getX() + SIZE, getY(), (xTile + 1) + 6 * SpriteSheet.tiles.getNumBoxes(), color, SpriteSheet.tiles);
			break;
		case EAST:
			screen.render(getX(), getY(), xTile + 7 * SpriteSheet.tiles.getNumBoxes(), color, SpriteSheet.tiles);
			screen.render(getX(), getY() + SIZE, xTile + 8 * SpriteSheet.tiles.getNumBoxes(), color, SpriteSheet.tiles);
			break;
		case WEST:
			screen.render(getX(), getY(), xTile + 5 * SpriteSheet.tiles.getNumBoxes(), color, SpriteSheet.tiles);
			screen.render(getX() + SIZE, getY(), (xTile + 1) + 5 * SpriteSheet.tiles.getNumBoxes(), color, SpriteSheet.tiles);
			break;
		default:
			screen.render(getX(), getY(), xTile + 9 * SpriteSheet.tiles.getNumBoxes(), color, SpriteSheet.tiles);
			screen.render(getX(), getY() + SIZE, xTile + 10 * SpriteSheet.tiles.getNumBoxes(), color, SpriteSheet.tiles);
			break;
		}
	}
}
