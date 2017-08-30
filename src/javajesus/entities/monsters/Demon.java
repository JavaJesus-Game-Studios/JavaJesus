package javajesus.entities.monsters;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.Random;

import javajesus.JavaJesus;
import javajesus.entities.Damageable;
import javajesus.entities.Entity;
import javajesus.entities.LongRange;
import javajesus.entities.Pickup;
import javajesus.entities.Type;
import javajesus.entities.projectiles.FireBall;
import javajesus.graphics.Screen;
import javajesus.items.Item;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A generic demon that populates most of the JavaJesus
 */
public class Demon extends Monster implements LongRange, Type {

	// dimensions of the demon
	private static final int WIDTH = 16, HEIGHT = 24;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 3;
	
	// base stats
	private static final int BASE_STRENGTH = 5, BASE_DEFENSE = 0;

	// color set of a demon
	private static final int[] color = { 0xFF111111, 0xFF700000, 0xFFDBA800, 0 , 0 };
	
	//types of Demon
	public static final byte IMP = 0, WARRIOR = 1;
	
	// attack range of demons
	protected static final int RADIUS = 20 * 8;
	
	//which type the Demon is
	private byte type;
	
	// the range the shooter will stand back when shooting
	private Ellipse2D.Double standRange;

	/**
	 * Creates a Demon
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param speed - how fast the demon moves
	 * @param health - the base health
	 */
	public Demon(Level level, int x, int y, int speed, int health, byte type) {
		super(level, "Demon", x, y, speed, WIDTH, HEIGHT, 0, health, 50);

		// set the standing box
		this.standRange = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2, RADIUS, RADIUS);
		
		// set fire immunity
		setFireImmune(true);
		
		// sets the appropriate y tile on the pixel sheet
		this.type = type;
		update(type);
	}
	
	/**
	 * Creates a Demon
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param health - the base health
	 */
	public Demon(Level level, int x, int y, int health, byte type) {
		this(level, x, y, 1, health, type);
	}
	
	/**
	 * Creates a Demon Warrior
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param health - the base health
	 */
	public Demon(Level level, int x, int y) {
		this(level, x, y, 1, 100, WARRIOR);
	}
	/**
	 * @param type - The type of Demon to render
	 */
	private void update(int type) {
		switch (type) {
		case WARRIOR:
			yTile = 0;
			break;
		default:
			//yTile = 3;
		}
	}

	/**
	 * Displays the Demon to the screen
	 */
	public void render(Screen screen) {
		super.render(screen);
		
		// default color
		int[] color = Demon.color;
		
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

		// attacking animation
		if (isShooting)
			xTile += 12;

		// dead has an absolute position
		if (isDead()) {
			if (isLongitudinal()) {
				setDirection(Direction.WEST);
			}
			xTile = 24;
		}

		// only a living demon has a top layer
		if (!isDead()) {

			// Upper body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile, yTile, getSpriteSheet(), flip, color);

			// Upper body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset,
					xTile + 1, yTile, getSpriteSheet(), flip, color);
		}

		// Middle Body 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier,
				xTile, yTile + 1, getSpriteSheet(), flip, color);

		// Middle Body 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier,
				xTile + 1, yTile + 1, getSpriteSheet(), flip, color);

		// Lower Body 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier,
				xTile, yTile + 2, getSpriteSheet(), flip, color);

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier,
				xTile + 1, yTile + 2, getSpriteSheet(), flip, color);

		// dead bodies have an extended segment
		if (isDead()) {

			int offset = getDirection() == Direction.WEST ? -16 : 0;

			// Middle Body 3
			screen.render(xOffset + offset + 2 * modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier,
					xTile + 2, yTile + 1, getSpriteSheet(), flip, color);

			// Lower Body 3
			screen.render(xOffset + offset + 2 * modifier - (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier,
					xTile + 2, yTile + 2, getSpriteSheet(), flip, color);

		}

	}

	/**
	 * Demon specific loot
	 */
	protected void dropLoot() {
		
		// random value for % chance
		int value = (new Random()).nextInt(100);
		
		// 40% chance of revolver, 15% health, 3% horned armor
		if (value < 40) {
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8), getY() + JavaJesus.getRandomOffset(16),Item.revolverAmmo, 24));
		} else if (value < 65) {
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8), getY() + JavaJesus.getRandomOffset(16), Item.quickHealthPack, true));
		} else if (value < 68) {
			getLevel().add(new Pickup(getLevel(), getX() + JavaJesus.getRandomOffset(8), getY() + JavaJesus.getRandomOffset(16), Item.shotgunAmmo,12));
		}
	}
	
	/**
	 * Deals damage to another mob
	 * Calculated by getStrength() +
	 * a random number in the range
	 * 
	 * @param range - random offset to add to strength
	 * @param other - the other mob to attack
	 */
	@Override
	public void attack(int range, Damageable other) {
		
		getLevel().add(new FireBall(getLevel(), getX() + WIDTH / 2, getY() + HEIGHT / 2,
				target.getX() + (int) target.getBounds().getWidth() / 2,
				target.getY() + (int) target.getBounds().getHeight() / 2, this, getStrength() + random.nextInt(range)));
	}

	/**
	 * Moves a monster on the level
	 * 
	 * @param dx - the total change in x
	 * @param dy - the total change in y
	 */
	public void move(int dx, int dy) {

		standRange.setFrame(getX() - RADIUS / 2, getY() - RADIUS / 2, RADIUS, RADIUS);

		super.move(dx, dy);
	}
	
	

	@Override
	public Double getRange() {
		return standRange;
	}
	
	@Override
	public int getStrength() {
		return Math.round(BASE_STRENGTH * JavaJesus.difficulty);
	}

	@Override
	public int getDefense() {
		return Math.round(BASE_DEFENSE * JavaJesus.difficulty);
	}

	@Override
	public byte getId() {
		return Entity.DEMON;
	}

	@Override
	public byte getType() {
		return type;
	}

	@Override
	public void setType(byte type) {
		this.type = type;
		update(type);
	}

}
