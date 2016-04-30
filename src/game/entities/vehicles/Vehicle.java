package game.entities.vehicles;

import game.Display;
import game.InputHandler;
import game.entities.Entity;
import game.entities.FireEntity;
import game.entities.Mob;
import game.entities.Player;
import game.entities.SolidEntity;
import game.graphics.JJFont;
import game.graphics.Screen;
import game.graphics.SpriteSheet;

import java.awt.Point;
import java.awt.Rectangle;

import level.Level;
import level.tile.Tile;
import utility.Direction;

/*
 * A vehicle can be ridden by the player
 * Vehicles must continue the render() method
 */
public abstract class Vehicle extends Entity implements SolidEntity, Ridable {

	private static final long serialVersionUID = -190937210314016793L;

	// the player that is in the vehicle
	private Player player;

	// internal timer
	private int tickCount;

	// the change in the car's velocity
	private Point acceleration = new Point(0, 0);

	// the delay between time increments
	private int DELAY = 10;

	// the fastest a vehicle can accelerate
	private final int MAX_ACCELERATION = 5;

	// whether or not acceleration is negative
	private boolean isXSlowingDown = true, isYSlowingDown = true;

	// whether or not to notify the player can't exit the vehicle here
	private boolean showCantExitVehicle = false;

	// internal timer to display the notification
	private int exitTickCount;

	// the spritesheet to use
	private SpriteSheet sheet;

	// the name of the vehicle
	private String name;

	// the speed of the vehicle
	private int speed;

	// the current amount of health
	private int health;

	// the max health (upper bound)
	private int maxHealth;

	/**
	 * Creates a Vehicle
	 * 
	 * @param level
	 *            the level it is on
	 * @param name
	 *            the name of the vehicle
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param speed
	 *            the base speed of the vehicle
	 * @param width
	 *            the width of the vehicle
	 * @param height
	 *            the height of the vehicle
	 * @param sheet
	 *            the spritesheet of the vehicle
	 * @param maxHealth
	 *            the max health
	 */
	public Vehicle(Level level, String name, int x, int y, int speed, int width, int height, SpriteSheet sheet,
			int maxHealth) {
		super(level, x, y);

		this.name = name;
		this.speed = speed;
		this.sheet = sheet;
		this.maxHealth = maxHealth;

		setBounds(getX(), getY(), width, height);
	}

	/**
	 * Adds the player into the vehicle
	 * 
	 * @param player
	 *            the player to drive
	 */
	public void drive(Player player) {
		this.player = player;
	}

	/**
	 * Removes the player from the vehicle
	 */
	public void exit() {
		player.exitVehicle();
		this.player = null;
	}

	/**
	 * Determines if the change in x or y results in a solid tile collision
	 * 
	 * @param dx
	 *            the change in x
	 * @param dy
	 *            the change in y
	 * @return true if the change in coordinates results in a solid tile
	 *         collision
	 */
	private boolean hasCollided(int dx, int dy) {

		// the left bound of the mob
		int xMin = 0;

		// the right bound of the mob
		int xMax = getBounds().width;

		// the top bound of the mob
		int yMin = getBounds().width / 2;

		// the bottom bound of the mob
		int yMax = getBounds().width;

		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(dx, dy, x, yMin) || isSolidTile(dx, dy, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(dx, dy, xMin, y) || isSolidTile(dx, dy, xMax, y)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the position is a solid tile
	 * 
	 * @param dx
	 *            the new x
	 * @param dy
	 *            the new y
	 * @param x
	 *            the x offset
	 * @param y
	 *            the y offset
	 * @return true if the new tile is solid
	 */
	private boolean isSolidTile(int dx, int dy, int x, int y) {

		// tile the mob is on (bottom half)
		Tile lastTile = getLevel().getTile((getX() + x) >> 3, (getY() + y) >> 3);

		// tile the mob will move to
		Tile newTile = getLevel().getTile((getX() + x + dx) >> 3, (getY() + y + dy) >> 3);

		// checking the last tile allows the player to move through solids if
		// already on a solid
		if (!lastTile.equals(newTile) && newTile.isSolid()) {
			return true;
		}
		return false;
	}

	/**
	 * Update the vehicle
	 */
	public void tick() {

		// change in x and y
		int dx = 0, dy = 0;

		// vehicle will always try to slow down
		isXSlowingDown = isYSlowingDown = true;
		
		// check for input
		if (isUsed()) {

			if (player.getInput().w.isPressed()) {

				isYSlowingDown = false;
				if (Math.abs(acceleration.y - 1) < MAX_ACCELERATION && tickCount % DELAY == 0) {
					acceleration.y--;
				}
			}

			if (player.getInput().s.isPressed()) {
				isYSlowingDown = false;
				if (Math.abs(acceleration.y + 1) < MAX_ACCELERATION && tickCount % DELAY == 0) {
					acceleration.y++;
				}
			}

			if (player.getInput().a.isPressed()) {
				isXSlowingDown = false;
				if (Math.abs(acceleration.x - 1) < MAX_ACCELERATION && tickCount % DELAY == 0) {
					acceleration.x--;
				}
			}

			if (player.getInput().d.isPressed()) {
				isXSlowingDown = false;
				if (Math.abs(acceleration.x + 1) < MAX_ACCELERATION && tickCount % DELAY == 0) {
					acceleration.x++;
				}
			}

			if (player.getInput().i.isPressed()) {
				player.getInput().i.toggle(false);
				if (Display.inGameScreen) {
					Display.displayInventory();
				}
			}
			if (player.getInput().esc.isPressed()) {
				player.getInput().esc.toggle(false);
				if (Display.inGameScreen) {
					Display.displayPause();
				}
			}
			
			// TODO Change the way this works to exit vehicles
			if (player.getInput().e.isPressed()) {
				int distance = -6;
				if (this instanceof Car) {
					distance = -15;
				}
				player.getInput().e.toggle(false);
				exit();

			}

		}

		// broken cars can't move
		if (isBroken()) {
			return;
		}

		xa += acceleration.x;
		ya += acceleration.y;

		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa * (int) speed, ya * (int) speed)) {
			move(xa, ya);
			setMoving(true);
			if (isUsed)
				player.setMoving(true);
		} else {
			if (isSolidEntityCollision(xa * (int) speed, 0)) {
				acceleration.x = 0;
			}
			if (isSolidEntityCollision(0, ya * (int) speed)) {
				acceleration.y = 0;
			}
			setMoving(false);
			if (isUsed)
				player.setMoving(false);
		}
		
		if (isMobCollision() && this.isMoving()) {
			for (Mob mob : level.getMobs()) {
				if (!(mob == this || mob instanceof Player)) {
					if (mob.getBounds().intersects(this.getBounds()))
						mob.damage(3, 15);
				}
			}
		}

		if (tickCount % DELAY == 0) {
			if (isXSlowingDown) {
				if (acceleration.x > 0) {
					acceleration.x--;
				}
				if (acceleration.x < 0) {
					acceleration.x++;
				}
			}
			if (isYSlowingDown) {
				if (acceleration.y > 0) {
					acceleration.y--;
				}
				if (acceleration.y < 0) {
					acceleration.y++;
				}
			}
		}

		tickCount++;
		if (showCantExitVehicle) {
			exitTickCount++;
			if (exitTickCount >= 60) {
				showCantExitVehicle = false;
				exitTickCount = 0;
			}
		}
	}
	
	/**
	 * Moves a vehicle on the level
	 * 
	 * @param dx
	 *            the total change in x
	 * @param dy
	 *            the total change in y
	 */
	public void move(int dx, int dy) {

		int sign = 0;
		if (dx < 0) {
			sign = 1;
		} else if (dx > 0) {
			sign = -1;
		}

		// move pixel by pixel for maximum accuracy
		for (int i = 0; i < Math.abs(dx); i++) {
			if (!hasCollided(1 * sign, 0)) {
				super.move(1 * sign, 0);
				if (isSolidEntityCollision()) {
					super.move(-1 * sign, 0);
				}
			} else {
				break;
			}
		}

		if (dy < 0) {
			sign = 1;
		} else if (dy > 0) {
			sign = -1;
		}

		// move pixel by pixel for maximum accuracy
		for (int i = 0; i < Math.abs(dy); i++) {
			if (!hasCollided(0, 1 * sign)) {
				super.move(0, 1 * sign);
				if (isSolidEntityCollision()) {
					super.move(0, -1 * sign);
				}
			} else {
				break;
			}
		}
	}
	
	/**
	 * Checks if the new position collides with a solid entity
	 * 
	 * @param dx
	 *            the change in x
	 * @param dy
	 *            the change in y
	 * @return true if the new position will create a collision
	 */
	private boolean isSolidEntityCollision() {

		// loop through the buildings/possible entities
		for (Entity entity : getLevel().getEntities()) {
			if (entity instanceof SolidEntity && getBounds().intersects(entity.getBounds())) {
				return true;
			} 
		}

		return false;
	}

	/**
	 * Displays the notification on the screen
	 */
	public void render(Screen screen) {
		if (isUsed() && showCantExitVehicle) {
			JJFont.render("!", screen, player.getX() - 20, player.getY(),
					new int[] { 0xFF000000, 0xFF000000, 0xFFFFCC00 }, 1);
		}
	}

	/**
	 * The vehicle is an overhead view so it doesn't have a shadow, but is
	 * treated as a solid
	 */
	public Rectangle getShadow() {
		return null;
	}

	/**
	 * @return true if there is a player inside
	 */
	public boolean isUsed() {
		return player != null;
	}

	/**
	 * Determines if vehicle is broken or not
	 * 
	 * @return true if broken
	 */
	public boolean isBroken() {
		return health <= 0;
	}

	/**
	 * @return the vehicle's spritesheet
	 */
	protected SpriteSheet getSpriteSheet() {
		return sheet;
	}

	/**
	 * @return The vehicle's name
	 */
	public String getName() {
		return name;
	}

}
