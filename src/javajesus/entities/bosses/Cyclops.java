package javajesus.entities.bosses;

import java.util.Random;

import javajesus.JavaJesus;
import javajesus.entities.Entity;
import javajesus.entities.Pickup;
import javajesus.entities.monsters.Monster;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.items.Item;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A Cyclops is a powerful Boss that strikes fear into any foe
 */
public class Cyclops extends Monster {

	// dimensions of the cyclops
	private static final int WIDTH = 32, HEIGHT = 48;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	// base stats
	private static final int BASE_STRENGTH = 5, BASE_DEFENSE = 1;

	// color set of a cyclops
	private static final int[] color = { 0xFF111111, 0xFFe8c094, 0xFFFFFFFF, 0xFF3c2b1b, 0xFF663501 };

	/**
	 * Creates a cyclops
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param speed - how fast the cyclops moves
	 * @param health - the base health
	 */
	public Cyclops(Level level, int x, int y, int speed, int health) {
		super(level, "Cyclops", x, y, speed, WIDTH, HEIGHT, 4, health, 60);
		setSpriteSheet(SpriteSheet.bosses);
		setOuterBoundsRange(4);
	}

	/**
	 * Creates a cyclops
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param health - the base health
	 */
	public Cyclops(Level level, int x, int y, int health) {
		this(level, x, y, 1, health);
	}

	/**
	 * Creates a cyclops
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 */
	public Cyclops(Level level, int x, int y) {
		this(level, x, y, 1, 3000);
	}

	/**
	 * Displays the cyclops to the screen
	 */
	public void render(Screen screen) {
		super.render(screen);

		// default color
		int[] color = Cyclops.color;

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
		// Cyclops has an asymmetric drumstick so it fake flips!
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile = 20;
			if (isMoving) {
				xTile += 4 + (flip ? 4 : 0);
				flip = false;
			}
			if (isShooting) {
				xTile = 41;
				flip = false;
			}
		} else if (getDirection() == Direction.SOUTH) {
			xTile = 0;
			if (isMoving) {
				xTile += 4 + (flip ? 4 : 0);
				flip = false;
			}
			if (isShooting) {
				xTile = 32;
				flip = false;
			}
		} else {
			xTile = 12 + (isMoving && flip ? 4 : 0);
			if (isShooting) {
				xTile = 36;
			}
			flip = getDirection() == Direction.WEST;
		}

		// dead has an absolute position
		if (isDead()) {
			xTile = 45;

			for (int i = 3; i < 6; i++) {
				// left left
				screen.render(xOffset + (modifier * (flip ? 5 : 0)), yOffset + i * modifier, xTile, yTile + i,
				        getSpriteSheet(), flip, color);

				// left center
				screen.render(xOffset + modifier + (modifier * (flip ? 3 : 0)), yOffset + i * modifier, xTile + 1,
				        yTile + i, getSpriteSheet(), flip, color);

				// left right
				screen.render(xOffset + 2 * modifier - (modifier * (flip ? 1 : 0)), yOffset + i * modifier, xTile + 2,
				        yTile + i, getSpriteSheet(), flip, color);

				// right left
				screen.render(xOffset + 3 * modifier - (modifier * (flip ? 1 : 0)), yOffset + i * modifier, xTile + 3,
				        yTile + i, getSpriteSheet(), flip, color);

				// right center
				screen.render(xOffset + 4 * modifier - (modifier * (flip ? 3 : 0)), yOffset + i * modifier, xTile + 4,
				        yTile + i, getSpriteSheet(), flip, color);

				// right right
				screen.render(xOffset + 5 * modifier - (modifier * (flip ? 5 : 0)), yOffset + i * modifier, xTile + 5,
				        yTile + i, getSpriteSheet(), flip, color);
			}

			/// normal rendering
		} else {

			// draw all 6 rows
			for (int i = 0; i < 6; i++) {

				// left
				screen.render(xOffset + (modifier * (flip ? 3 : 0)), yOffset + i * modifier, xTile, yTile + i,
				        getSpriteSheet(), flip, color);

				// left center
				screen.render(xOffset + modifier + (modifier * (flip ? 1 : 0)), yOffset + i * modifier, xTile + 1,
				        yTile + i, getSpriteSheet(), flip, color);

				// right center
				screen.render(xOffset + 2 * modifier - (modifier * (flip ? 1 : 0)), yOffset + i * modifier, xTile + 2,
				        yTile + i, getSpriteSheet(), flip, color);

				// right
				screen.render(xOffset + 3 * modifier - (modifier * (flip ? 3 : 0)), yOffset + i * modifier, xTile + 3,
				        yTile + i, getSpriteSheet(), flip, color);

			}

		}

	}

	/**
	 * Cyclops specific loot
	 */
	protected void dropLoot() {

		// drop 4x basic loot first
		for (int i = 0; i < 4; i++) {
			super.dropLoot();
		}

		// random value for % chance
		int value = (new Random()).nextInt(100);

		// 5% chance of istrahiim armor, 20% large health
		if (value < 5) {
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(40),
			        getY() + JavaJesus.getRandomOffset(40), Item.arrowAmmo));
		} else if (value < 25) {
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(40),
			        getY() + JavaJesus.getRandomOffset(40), Item.strongHealthPack));
		}
	}

	@Override
	public byte getId() {
		return Entity.CYCLOPS;
	}

	@Override
	public int getStrength() {
		return Math.round(BASE_STRENGTH * JavaJesus.difficulty);
	}

	@Override
	public int getDefense() {
		return Math.round(BASE_DEFENSE * JavaJesus.difficulty);
	}

}
