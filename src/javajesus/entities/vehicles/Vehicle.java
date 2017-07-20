package javajesus.entities.vehicles;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javajesus.Hideable;
import javajesus.entities.Damageable;
import javajesus.entities.Entity;
import javajesus.entities.Player;
import javajesus.entities.SolidEntity;
import javajesus.graphics.JJFont;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.level.tile.Tile;
import javajesus.utility.Direction;
import engine.Window;

/*
 * A vehicle can be ridden by the player
 * Vehicles must continue the render() method
 */
public class Vehicle extends Entity implements SolidEntity, Ridable,
		Damageable, Hideable {

	// the player that is in the vehicle
	private Player player;

	// internal timer
	private int tickCount;

	// the change in the car's velocity
	private Point acceleration = new Point(0, 0);

	// the fastest a vehicle can accelerate
	private final int MAX_ACCELERATION = 5;

	// rate at which speed decays
	// higher number = slower decay
	private static final int DECAY = 15;
	private static final int DELAY = 5;

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

	// the direction the vehicle is facing
	private Direction direction;

	// fake shadow, but used to conform to solid entity
	private static final Rectangle shadow = new Rectangle();

	// width and height based on direction
	protected int xWidth, xHeight, yWidth, yHeight;

	// whether or not the vehicle is behind a building
	private boolean isBehindBuilding;

	/**
	 * Creates a Vehicle
	 * 
	 * @param level - the level it is on
	 * @param name - the name of the vehicle
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param speed - the base speed of the vehicle
	 * @param width - the width of the vehicle
	 * @param height - the height of the vehicle
	 * @param sheet - the spritesheet of the vehicle
	 * @param maxHealth - the max health
	 */
	public Vehicle(Level level, String name, int x, int y, int speed,
			int xWidth, int xHeight, int yWidth, int yHeight,
			SpriteSheet sheet, int maxHealth) {
		super(level, x, y);

		// instance data
		this.name = name;
		this.speed = speed;
		this.sheet = sheet;
		this.maxHealth = maxHealth;
		health = maxHealth;
		this.xWidth = xWidth;
		this.xHeight = xHeight;
		this.yWidth = yWidth;
		this.yHeight = yHeight;

		// set default bounds
		setBounds(getX(), getY(), yWidth, yHeight);
	}

	/**
	 * Adds the player into the vehicle
	 * 
	 * @param player
	 * the player to drive
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
	 * @param dx - the change in x
	 * @param dy - the change in y
	 * @return true if the change in coordinates results in a solid tile
	 * collision
	 */
	private boolean hasCollided(int dx, int dy) {

		// the left bound of the mob
		int xMin = 0;

		// the right bound of the mob
		int xMax = getBounds().width;

		// the top bound of the mob
		int yMin = 0;

		// the bottom bound of the mob
		int yMax = getBounds().height;

		// check every tile in between
		for (int i = xMin; i < xMax; i += 4) {
			for (int j = yMin; j < yMax; j += 4) {
				
				// check for solid or water
				if (isSolidTile(i, j, dx, dy) || isWaterTile(i, j, dx, dy)) {
					return true;
				}
				
			}
		}
		
		// check for solid entity collisions
		Rectangle temp = new Rectangle((int) getBounds().getX() + xMin + dx,
				(int) getBounds().getY() + yMin + dy, xMax - xMin, yMax - yMin);

		// loop through all entities
		for (Entity entity : getLevel().getEntities()) {

			// check for collision with another entity
			if (entity instanceof SolidEntity
					&& temp.intersects(entity.getBounds()) && entity != this) {
				return true;
			}

		}
		return false;
	}
	
	/**
	 * Determines if the change in bounds collides
	 * 
	 * @param width - the new width
	 * @param height - the new height
	 * @return true if the change in bounds results in a solid tile
	 * collision
	 */
	private boolean hasBoundsCollided(int width, int height) {

		// the left bound of the mob
		int xMin = 0;

		// the right bound of the mob
		int xMax = width;

		// the top bound of the mob
		int yMin = 0;

		// the bottom bound of the mob
		int yMax = height;
		
		// check every tile in between
		for (int i = xMin; i < xMax; i += 4) {
			for (int j = yMin; j < yMax; j += 4) {
				
				// check for solid or water
				if (isSolidTile(i, j, 0, 0) || isWaterTile(i, j, 0, 0)) {
					return true;
				}
				
			}
		}

		// check for solid entity collisions
		Rectangle temp = new Rectangle((int) getBounds().getX(),
				(int) getBounds().getY(), width, height);

		// loop through all entities
		for (Entity entity : getLevel().getEntities()) {

			// check for collision with another entity
			if (entity instanceof SolidEntity
					&& temp.intersects(entity.getBounds()) && entity != this) {
				return true;
			}

		}
		return false;
	}
	
	/**
	 * Checks the type of tile in a new position
	 * 
	 * @param x - the x offset from current value
	 * @param y - the y offset from current value
	 * @param dx - the change in x
	 * @param dy - the change in y
	 * @return true if the new tile is solid
	 */
	protected boolean isSolidTile(int x, int y, int dx, int dy) {
		return getLevel().getTileFromEntityCoords(getX() + x + dx,
				getY() + y + dy).isSolid();
	}

	/**
	 * Checks the type of tile in a new position
	 * 
	 * @param x - the x offset from current value
	 * @param y - the y offset from current value
	 * @param dx - the change in x
	 * @param dy - the change in y
	 * @return true if the new tile is solid
	 */
	protected boolean isWaterTile(int x, int y, int dx, int dy) {
		return getLevel().getTileFromEntityCoords(getX() + x + dx,
				getY() + y + dy).getId() == Tile.WATER.getId();
	}

	/**
	 * Vehicle input listener
	 * 
	 * @param window - window to check for input
	 */
	public void input(Window window) {

		// vehicle will always try to slow down
		isXSlowingDown = isYSlowingDown = true;

		if (window.isKeyPressed(KeyEvent.VK_W)) {

			isYSlowingDown = false;
			if (Math.abs(acceleration.y - 1) < MAX_ACCELERATION
					&& tickCount % DELAY == 0) {
				acceleration.y--;
			}
		}

		if (window.isKeyPressed(KeyEvent.VK_S)) {
			isYSlowingDown = false;
			if (Math.abs(acceleration.y + 1) < MAX_ACCELERATION
					&& tickCount % DELAY == 0) {
				acceleration.y++;
			}
		}

		if (window.isKeyPressed(KeyEvent.VK_A)) {
			isXSlowingDown = false;
			if (Math.abs(acceleration.x - 1) < MAX_ACCELERATION
					&& tickCount % DELAY == 0) {
				acceleration.x--;
			}
		}

		if (window.isKeyPressed(KeyEvent.VK_D) && tickCount % DELAY == 0) {
			isXSlowingDown = false;
			if (Math.abs(acceleration.x + 1) < MAX_ACCELERATION) {
				acceleration.x++;
			}
		}

		// TODO Change the way this works to exit vehicles
		if (window.isKeyPressed(KeyEvent.VK_E)) {
			window.toggle(KeyEvent.VK_E);
			exit();
		}

	}

	/**
	 * Update the vehicle
	 */
	public void tick() {
		
		// broken cars can't move
		if (isBroken()) {
			return;
		}

		// change in x and y
		int dx = 0, dy = 0;

		// move the mob
		dx = acceleration.x * speed;
		dy = acceleration.y * speed;

		if (dx != 0 || dy != 0)
			move(dx, dy);

		if (tickCount % DECAY == 0) {
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
	 * @param dx - the total change in x
	 * @param dy - the total change in y
	 */
	public void move(int dx, int dy) {

		// default it is on foreground
		isBehindBuilding = false;
		
		// set the direction based on dominant movement
		if (acceleration.y >= Math.abs(acceleration.x)) {

			if (!hasBoundsCollided(yWidth, yHeight)) {
				setDirection(Direction.SOUTH);
				setBounds(getX(), getY(), yWidth, yHeight);
			}

		} else if (Math.abs(acceleration.y) >= Math.abs(acceleration.x)) {
			if (!hasBoundsCollided(yWidth, yHeight)) {
				setDirection(Direction.NORTH);
				setBounds(getX(), getY(), yWidth, yHeight);
			}
		} else if (acceleration.x > 0) {
			if (!hasBoundsCollided(xWidth, xHeight)) {
				setDirection(Direction.EAST);
				setBounds(getX(), getY(), xWidth, xHeight);
			}

		} else {
			if (!hasBoundsCollided(xWidth, xHeight)) {
				setDirection(Direction.WEST);
				setBounds(getX(), getY(), xWidth, xHeight);
			}
		}

		// update layer
		for (int i = 0; i < getLevel().getEntities().size(); i++) {
			Entity e = getLevel().getEntities().get(i);

			// check for shadows
			if (e instanceof SolidEntity
					&& ((SolidEntity) e).getShadow().intersects(getBounds())
					&& e != this) {
				isBehindBuilding = true;
				break;
			}
		}

		// movement
		int sign = 0;

		// assigns direction and bounds
		if (dx < 0) {
			sign = -1;
		} else if (dx > 0) {
			sign = 1;
		}

		// move pixel by pixel for maximum accuracy
		for (int i = 0; i < Math.abs(dx); i++) {

			// move if the next pixel is free
			if (!hasCollided(1 * sign, 0)) {
				super.move(1 * sign, 0);

				// has collided on x axis
			} else {
				acceleration.x = 0;
				break;
			}
		}

		// movement in y direction
		if (dy < 0) {
			sign = -1;
		} else if (dy > 0) {
			sign = 1;
		}

		// move pixel by pixel for maximum accuracy
		for (int i = 0; i < Math.abs(dy); i++) {

			// check for collision before moving
			if (!hasCollided(0, 1 * sign)) {
				super.move(0, 1 * sign);

				// has collided in y direction
			} else {
				acceleration.y = 0;
				break;
			}
		}

	}

	/**
	 * @param dir - the direction the vehicle is facing
	 */
	private void setDirection(Direction dir) {
		this.direction = dir;
	}

	/**
	 * Displays the notification on the screen
	 */
	public void render(Screen screen) {
		if (isUsed() && showCantExitVehicle) {
			JJFont.render("!", screen, player.getX() - 8, player.getY(),
					new int[] { 0xFF000000, 0xFF000000, 0xFFFFCC00 }, 1);
		}
	}

	/**
	 * The vehicle is an overhead view so it doesn't have a shadow, but is
	 * treated as a solid
	 */
	public Rectangle getShadow() {
		return shadow;
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

	/**
	 * @return true if the vehicle is moving
	 */
	public boolean isMoving() {
		return acceleration.x != 0 || acceleration.y != 0;
	}

	/**
	 * @return the damage inflicted when a mob is hit by this vehicle
	 */
	public int getDamage() {
		return 5;
	}

	/**
	 * Decreases a vehicle's health
	 * 
	 * @param damage - the value of damage
	 */
	public void damage(int damage) {
		this.health -= damage;
	}

	/**
	 * @return the vehicle's max health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * @return the vehicle's current health
	 */
	public int getCurrentHealth() {
		return health;
	}

	/**
	 * @return the direction the vehicle is facing
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @return the player driving the vehicle
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Whether or not the vehicle is behind a building
	 */
	@Override
	public boolean isBehindBuilding() {
		return isBehindBuilding;
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
