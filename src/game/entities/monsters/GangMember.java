package game.entities.monsters;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import game.ChatHandler;
import game.SoundHandler;
import game.entities.LongRange;
import game.entities.Mob;
import game.entities.Player;
import game.entities.projectiles.Bullet;
import game.graphics.Screen;
import level.Level;
import utility.Direction;

/*
 * A generic gang member to attack others
 */
public class GangMember extends Monster implements LongRange {

	private static final long serialVersionUID = 3322532159669147419L;

	// the range the gang member will stand when shooting
	private Ellipse2D.Double standRange;

	// dimensions of the gang member
	private static final int WIDTH = 16, HEIGHT = 16;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	// color set of a gang member
	private static final int[] color = { 0xFF111111, 0xFFFFFFFF, 0xFFEDC5AB };

	// types of gang members
	public static final int TRIAD = 0, RUSSIAN = 1;

	/**
	 * Creates a gang member
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param speed
	 *            the base speed
	 * @param health
	 *            the base health
	 * @param type
	 *            the appearance, either GangMember.TRIAD or GangMember.RUSSIAN
	 */
	public GangMember(Level level, int x, int y, int speed, int health, int type) {
		super(level, "Gangster", x, y, speed, WIDTH, HEIGHT, 1, health, 100);

		// sets the appropriate y tile on the pixel sheet
		getType(type);

		// creates the standing range
		standRange = new Ellipse2D.Double(getX() - RADIUS / 4, getY() - RADIUS / 4, RADIUS / 2, RADIUS / 2);

	}

	/**
	 * Moves a monster on the level
	 * 
	 * @param dx
	 *            the total change in x
	 * @param dy
	 *            the total change in y
	 */
	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);

		standRange.setFrame(getX() - RADIUS / 4, getY() - RADIUS / 4, RADIUS / 2, RADIUS / 2);
	}

	/**
	 * @param type
	 *            The type of gang member to render
	 */
	private void getType(int type) {
		switch (type) {
		case TRIAD:
			yTile = 3;
			break;
		default:
			yTile = 10;
			break;
		}
	}

	/**
	 * Displays the Demon to the screen
	 */
	public void render(Screen screen) {
		super.render(screen);

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE * getScale();

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal position on the spritesheet
		int xTile = 0;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile = 8;
		} else if (getDirection() == Direction.SOUTH) {
			xTile = 0;
		} else {
			xTile = 4 + (flip ? 2 : 0);
			flip = getDirection() == Direction.WEST;
		}

		// dead has an absolute position
		if (isDead()) {
			xTile = 12;
		}

		// shooting has an absolute position
		if (isShooting) {
			xTile = 14;
			if (getDirection() == Direction.NORTH) {
				xTile += 12;
			}
			if (getDirection() == Direction.SOUTH) {
				xTile += 6;
			}
		}

		// Upper body 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile + yTile * getSpriteSheet().boxes, color,
				flip, getScale(), getSpriteSheet());

		// Upper body 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset,
				(xTile + 1) + yTile * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		// Lower Body 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier,
				xTile + (yTile + 1) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier,
				(xTile + 1) + (yTile + 1) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

	}

	/**
	 * Shoots a bullet at a target Uses dummy parameters to conform to Mob class
	 */
	@Override
	public void attack(int fake, int fake2, Mob other) {

		getLevel().add(new Bullet(getLevel(), getX(), getY(), target.getX(), target.getY(), this, getStrength(),
				SoundHandler.revolver));
	}

	/**
	 * Sets the Gangster's strength
	 */
	@Override
	public int getStrength() {
		return 5;
	}

	/**
	 * Text to player
	 */
	public void speak(Player player) {
		isTalking = true;
		ChatHandler.displayText("I'm in charge here.", Color.white);
		return;
	}

	/**
	 * The long distance range
	 */
	@Override
	public Ellipse2D.Double getRange() {
		return standRange;
	}

}
