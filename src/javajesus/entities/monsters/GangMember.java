package javajesus.entities.monsters;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import javajesus.JavaJesus;
import javajesus.MessageHandler;
import javajesus.SoundHandler;
import javajesus.dataIO.EntityData;
import javajesus.entities.Damageable;
import javajesus.entities.Entity;
import javajesus.entities.LongRange;
import javajesus.entities.Player;
import javajesus.entities.Type;
import javajesus.entities.projectiles.Bullet;
import javajesus.graphics.Screen;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A generic gang member to attack others
 */
public class GangMember extends Monster implements LongRange, Type {

	// the range the gang member will stand when shooting
	private Ellipse2D.Double standRange;

	// dimensions of the gang member
	private static final int WIDTH = 16, HEIGHT = 16;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;
	
	// base stats
	private static final int BASE_STRENGTH = 1, BASE_DEFENSE = 1;

	// color set of a gang member
	private static final int[] color = { 0xFF111111, 0xFFFFFFFF, 0xFFEDC5AB, 0xFF1c1510, 0xFF262878 };

	// types of gang members
	public static final byte TRIAD = 0, RUSSIAN = 1, MEANSTREETS = 2;
	
	// which type it is
	private byte type;

	/**
	 * Creates a gang member
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param speed - the base speed
	 * @param health - the base health
	 * @param type - the appearance, either GangMember.TRIAD or GangMember.RUSSIAN
	 */
	public GangMember(Level level, int x, int y, int speed, int health, byte type) {
		super(level, "Gangster", x, y, speed, WIDTH, HEIGHT, 13, health, 100);

		// sets the appropriate y tile on the pixel sheet
		this.type = type;
		update(type);

		// creates the standing range
		this.standRange = new Ellipse2D.Double(x - (RADIUS * 8) / 4, y - (RADIUS * 8) / 4, (RADIUS * 8) / 2, (RADIUS * 8) / 2);

	}
	
	/**
	 * Creates a gang member
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param type - the appearance, either GangMember.TRIAD or GangMember.RUSSIAN or GangMember. MEANSTREETS
	 */
	public GangMember(Level level, int x, int y, byte type) {
		this(level, x, y, 1, 100, type);
	}

	/**
	 * Moves a monster on the level
	 * 
	 * @param dx - the total change in x
	 * @param dy - the total change in y
	 */
	public void move(int dx, int dy) {

		standRange.setFrame(getX() - RADIUS / 4, getY() - RADIUS / 4, RADIUS / 2, RADIUS / 2);
		
		super.move(dx, dy);
	}

	/**
	 * @param type - The type of gang member to render
	 */
	private void update(int type) {
		switch (type) {
		case TRIAD:
			yTile = 13;
			break;
		case RUSSIAN:
			yTile = 15;
			break;
		default:
			yTile = 19;
			break;
		}
	}

	/**
	 * Displays the Gang Member to the screen
	 */
	public void render(Screen screen) {
		
		super.render(screen);
		
		// default color
		int[] color = GangMember.color;

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
			xTile = 8;
			if (!isMoving) {
				xTile += ((tickCount % 120 <= 60) ? 22 : 0);
			}
		} else if (getDirection() == Direction.SOUTH) {
			xTile = 0;
			if (!isMoving) {
				xTile += ((tickCount % 120 <= 60) ? 26 : 0);
			}
		} else {
			xTile = 4 + (flip ? 2 : 0);
			flip = getDirection() == Direction.WEST;
			if (!isMoving) {
				xTile += ((tickCount % 120 <= 60) ? 24 : 0);
			}
		}

		// dead has an absolute position
		if (isDead()) {
			xTile = 12;
		}

		// shooting has an absolute position
		if (isShooting) {
			if (getDirection() == Direction.NORTH) {
				xTile = 22;
				if (!isMoving) {
					xTile += ((tickCount % 120 <= 60) ? 14 : 0);
				}
			} else if (getDirection() == Direction.SOUTH) {
				xTile = 14;
				if (!isMoving) {
					xTile += ((tickCount % 120 <= 60) ? 18 : 0);
				}
			} else {
				xTile = 18 + (flip ? 2 : 0);
				flip = getDirection() == Direction.WEST;
				if (!isMoving) {
					xTile += ((tickCount % 120 <= 60) ? 16 : 0);
				}
			}
		}

		// Upper body 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile, yTile, getSpriteSheet(), flip, color);

		// Upper body 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset, xTile + 1, yTile, getSpriteSheet(),
		        flip, color);

		// Lower Body 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile, yTile + 1, getSpriteSheet(),
		        flip, color);

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile + 1, yTile + 1,
		        getSpriteSheet(), flip, color);


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

		getLevel().add(new Bullet(getLevel(), getX(), getY(), target.getX(), target.getY(), this, getStrength() + random.nextInt(range),
				SoundHandler.revolver));
	}

	@Override
	public int getStrength() {
		return Math.round(BASE_STRENGTH * JavaJesus.difficulty);
	}

	@Override
	public int getDefense() {
		return Math.round(BASE_DEFENSE * JavaJesus.difficulty);
	}

	/**
	 * Text to player
	 */
	public void speak(Player player) {
		isTalking = true;
		MessageHandler.displayText("I'm in charge here.", Color.white);
		return;
	}

	/**
	 * The long distance range
	 */
	@Override
	public Ellipse2D.Double getRange() {
		return standRange;
	}
	
	/**
	 * Gang member specific loot
	 */
	protected void dropLoot() {
		// drop basic loot first
		super.dropLoot();
	}

	@Override
	public byte getId() {
		return Entity.GANG_MEMBER;
	}
	
	@Override
	public long getData() {
		return EntityData.type4(getX(), getY(), getMaxHealth(), type);
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
