package javajesus.entities.vehicles;

import java.awt.event.KeyEvent;

import engine.Window;
import javajesus.JavaJesus;
import javajesus.entities.Mob;
import javajesus.entities.Player;
import javajesus.entities.npcs.NPC;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A horse is a npc that can be ridden by the player
 */
public class Horse extends NPC implements Ridable {

	private static final long serialVersionUID = -3649878712351708546L;

	// the player that is on the horse
	private Player player;

	// size of each box
	private static final int UNIT_SIZE = 8;

	// used for offsetting the bounds based on direction
	private static final int SHORT_SIDE = 16, LONG_SIDE = 24;

	// the xTile offset when not used
	private static final int NOT_USED_XTILE = 16;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 3;

	// the type/color of horse
	public static final int BROWN = 0, WHITE = 3, BLACK = 6;
	
	// horse speed
	private static final int HORSE_SPEED = 3;

	/**
	 * Creates a horse
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param defaultHealth
	 *            the default health
	 * @param walkPath
	 *            the walk path
	 * @param walkDistance
	 *            the walk distance
	 * @param type
	 *            the color of the horse
	 */
	public Horse(Level level, int x, int y, int defaultHealth, String walkPath, int walkDistance, int type) {
		super(level, "Horse", x, y, 1, SHORT_SIDE, LONG_SIDE, defaultHealth, null, NOT_USED_XTILE, type, walkPath,
				walkDistance);
		setSpriteSheet(SpriteSheet.horses);

	}

	/**
	 * Creates a horse
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param defaultHealth
	 *            the default health
	 * @param type
	 *            the color of the horse
	 */
	public Horse(Level level, int x, int y, int defaultHealth, int type) {
		super(level, "Horse", x, y, 1, SHORT_SIDE, LONG_SIDE, defaultHealth, null, NOT_USED_XTILE, type, "stand", 0);
		setSpriteSheet(SpriteSheet.horses);

	}
	
	public void input(Window window) {
		
		isMoving = false;

		// change in x and y
		int dx = 0, dy = 0;
		
		if (window.isKeyPressed(KeyEvent.VK_W)) {

			dy--;
		}

		if (window.isKeyPressed(KeyEvent.VK_S)) {
			dy++;
		}

		if (window.isKeyPressed(KeyEvent.VK_A)) {
			dx--;
		}

		if (window.isKeyPressed(KeyEvent.VK_D)) {
			dx++;
		}

		if (window.isKeyPressed(KeyEvent.VK_I)) {
			window.toggle(KeyEvent.VK_I);
			if (JavaJesus.inGameScreen) {
				JavaJesus.displayOverview();
			}
		}
		if (window.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			window.toggle(KeyEvent.VK_ESCAPE);
			if (JavaJesus.inGameScreen) {
				JavaJesus.displayPause();
			}
		}

		// TODO Change the way this works to exit vehicles
		if (window.isKeyPressed(KeyEvent.VK_E)) {
			window.toggle(KeyEvent.VK_E);
			exit();
		}

		if (dx != 0 || dy != 0) {
			isMoving = true;
			move(dx, dy);
		}
	}

	/**
	 * Updates the horse
	 */
	public void tick() {
		
		// check for input
		if (!isUsed()) {

			super.tick();
		}
	}

	/**
	 * Moves a horse on the level
	 * 
	 * @param dx
	 *            the total change in x
	 * @param dy
	 *            the total change in y
	 */
	public void move(int dx, int dy) {
		
		// standing upright
		if (isLongitudinal()) {

			setBounds(getX(), getY(), SHORT_SIDE, LONG_SIDE);
			setOuterBounds(SHORT_SIDE, LONG_SIDE);

			// standing sideways
		} else {
			setBounds(getX(), getY(), LONG_SIDE, LONG_SIDE);
			setOuterBounds(LONG_SIDE, LONG_SIDE);
		}
		
		if(isUsed())
			super.moveSmoothly(dx, dy);
		else
			super.move(dx, dy);
	}

	/**
	 * Displays the horse on the screen
	 */
	public void render(Screen screen) {

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE * getScale();

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal position on the spritesheet
		int xTile = this.xTile;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;
		
		// adjust spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile += 12;
			if (isMoving) {
				xTile += 2;
			}
		} else if (getDirection() == Direction.SOUTH) {
			if (isMoving) {
				xTile += 2;
			}
		} else {
			xTile += 4;
			if (isMoving) {
				xTile += (flip ? 4 : 0);
			}
			flip = getDirection() == Direction.WEST;
		}

		// standing vertical
		if (isLongitudinal()) {
			// Upper body
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile + yTile * getSpriteSheet().getNumBoxes(),
					getColor(), flip, getScale(), getSpriteSheet());

			// Upper body
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset,
					(xTile + 1) + yTile * getSpriteSheet().getNumBoxes(), getColor(), flip, getScale(), getSpriteSheet());

			// Middle Body
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier,
					xTile + (yTile + 1) * getSpriteSheet().getNumBoxes(), getColor(), flip, getScale(), getSpriteSheet());

			// Middle Body
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier,
					(xTile + 1) + (yTile + 1) * getSpriteSheet().getNumBoxes(), getColor(), flip, getScale(), getSpriteSheet());

			// Lower Body
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier,
					xTile + (yTile + 2) * getSpriteSheet().getNumBoxes(), getColor(), flip, getScale(), getSpriteSheet());

			// Lower Body
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier,
					(xTile + 1) + (yTile + 2) * getSpriteSheet().getNumBoxes(), getColor(), flip, getScale(), getSpriteSheet());
		}
		// standing horizontal
		else {

			// loop through top to bottom
			for (int i = 0; i < 3; i++) {

				// left
				screen.render(xOffset + (modifier * (flip ? 3 : 0)), yOffset + (modifier * i),
						xTile + (yTile + i) * getSpriteSheet().getNumBoxes(), getColor(), flip, getScale(), getSpriteSheet());

				// middle left
				screen.render(xOffset + modifier + (modifier * (flip ? 1 : 0)), yOffset + (modifier * i),
						(xTile + 1) + (yTile + i) * getSpriteSheet().getNumBoxes(), getColor(), flip, getScale(),
						getSpriteSheet());

				// middle right
				screen.render(xOffset + 2 * modifier - (modifier * (flip ? 1 : 0)), yOffset + (modifier * i),
						(xTile + 2) + (yTile + i) * getSpriteSheet().getNumBoxes(), getColor(), flip, getScale(),
						getSpriteSheet());

				// right
				screen.render(xOffset + 3 * modifier - (modifier * (flip ? 3 : 0)), yOffset + (modifier * i),
						(xTile + 3) + (yTile + i) * getSpriteSheet().getNumBoxes(), getColor(), flip, getScale(),
						getSpriteSheet());
			}
		}

	}
	
	/**
	 * Determines if a mob's bounds are intersecting another mob's bounds as it
	 * is
	 * 
	 * @return the other mob that is colliding with this mob, null if there
	 *         isn't one
	 */
	@Override
	protected Mob getMobCollision() {

		// loop through the list of mobs
		for (Mob mob : getLevel().getMobs()) {
			if (mob == this)
				continue;
			if (this.getBounds().intersects(mob.getBounds()) && !mob.isDead() && (!isUsed() || mob != player))
				return mob;
		}
		return null;
	}

	@Override
	public void drive(Player player) {
		this.player = player;
		this.setColor(player.getColor());
		xTile = 0;
		this.setSpeed(HORSE_SPEED);
	}

	@Override
	public void exit() {
		player.exitVehicle();
		player = null;
		xTile = NOT_USED_XTILE;
		this.setSpeed(1);
	}

	@Override
	public boolean isUsed() {
		return player != null;
	}

}
