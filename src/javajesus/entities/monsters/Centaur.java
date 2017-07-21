package javajesus.entities.monsters;

import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A Centaur is half man half horse creature that attacks others
 * TODO attacking animation
 */
public class Centaur extends Monster {

	// dimensions of the centaur
	private static final int WIDTH = 16, HEIGHT = 24;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	// color set of a centaur
	private static final int[] color = { 0xFF111111, 0xFF8F4C1F, 0xFFEDC5AB };

	/**
	 * Creates a centaur
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param speed - how fast the centaur moves
	 * @param health - the base health
	 */
	public Centaur(Level level, int x, int y, int speed, int health) {
		super(level, "Centaur", x, y, speed, WIDTH, HEIGHT, 5, health, 20);

	}
	
	/**
	 * Creates a centaur
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param health - the base health
	 */
	public Centaur(Level level, int x, int y, int health) {
		this(level, x, y, 1, health);
	}
	
	/**
	 * Creates a centaur
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 */
	public Centaur(Level level, int x, int y) {
		this(level, x, y, 1, 100);
	}

	/**
	 * Moves a centaur on the level
	 * 
	 * @param dx - the total change in x
	 * @param dy - the total change in y
	 */
	public void move(int dx, int dy) {

		// standing upright
		if (isLongitudinal()) {

			setBounds(getX(), getY(), WIDTH, HEIGHT);
			setOuterBounds(WIDTH, HEIGHT);

			// standing sideways
		} else {
			setBounds(getX(), getY(), HEIGHT, HEIGHT);
			setOuterBounds(HEIGHT, HEIGHT);
		}

		super.move(dx, dy);
	}

	/**
	 * Display the centaur on the screen
	 */
	public void render(Screen screen) {
		super.render(screen);
		
		// default color
		int[] color = Centaur.color;

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
			xTile = 12;
		} else if (getDirection() == Direction.SOUTH) {
			xTile = 2;
		} else {
			xTile = 4 + (flip ? 3 : 0);
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
				screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + (modifier * i),
						xTile, yTile + i, getSpriteSheet(), flip, color);

				// middle
				screen.render(xOffset + modifier, yOffset + (modifier * i),
						xTile + 1, yTile + i, getSpriteSheet(), flip, color);

				// right
				screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + (modifier * i),
						xTile + 2, yTile + i, getSpriteSheet(), flip, color);
			}
		}

	}
	
	/**
	 * Centaur specific loot
	 */
	protected void dropLoot() {
		
		// drop 2x basic loot first
		super.dropLoot();
		super.dropLoot();
	}
	
	/**
	 * Sets the centaur's strength
	 */
	@Override
	public int getStrength() {
		return 8;
	}

	@Override
	public byte getId() {
		return Entity.CENTAUR;
	}

}
