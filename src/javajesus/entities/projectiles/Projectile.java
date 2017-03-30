package javajesus.entities.projectiles;

import javajesus.Hideable;
import javajesus.SoundHandler;
import javajesus.entities.Entity;
import javajesus.entities.Mob;
import javajesus.entities.SolidEntity;
import javajesus.entities.vehicles.Vehicle;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;

import javax.sound.sampled.Clip;

import level.Level;
import level.tile.Tile;
import utility.Direction;

/*
 * A projectile is a fleeting particle that moves very fast across the screen to deal damage
 */
public abstract class Projectile extends Entity implements Hideable {

	private static final long serialVersionUID = 3377536695812898799L;

	// the stats of the projectile
	private int speed, damage;

	// the change in x and y that the projectile will travel towards
	private double dx, dy;

	// the mob that fired the projectile
	private Mob mob;

	// the spritesheet
	private static final SpriteSheet sheet = SpriteSheet.particles;

	// position on the spritesheet
	protected int tileNumber;

	// do not render if the projectile is behind a building
	private boolean isBehindBuilding;
	
	// x and y coordinates should be in double precision
	private double x, y;

	/**
	 * Creates a new Projectile will a single direction
	 * 
	 * @param level
	 *            : What level does it render on
	 * @param x
	 *            : The X position it will spawn at
	 * @param y
	 *            : The Y position it will spawn at
	 * @param width
	 *            the width of the sprite
	 * @param height
	 *            the height of the sprite
	 * @param tileNumber
	 *            : the tile id on the SpriteSheet (xTile) + (yTile) *
	 *            getSpriteSheet().boxes
	 * @param speed
	 *            : The speed at which the projectile will move (player speed is
	 *            1)
	 * @param direction
	 *            : The direction it will move; Currently only 0 1 2 or 3
	 * @param mob
	 *            the mob that fired the projectile
	 * @param damage
	 *            the damage this projectile should do
	 * @param clip
	 *            the sound this projectile makes
	 */
	public Projectile(Level level, int x, int y, int width, int height, int tileNumber, int speed,
			Direction direction, Mob mob, int damage, Clip clip) {
		super(level, x, y);
		this.x = x;
		this.y = y;
		this.tileNumber = tileNumber;
		this.speed = speed;
		calcSimpleDirection(direction);
		setBounds(getX(), getY(), width, height);
		this.mob = mob;
		this.damage = damage;
		SoundHandler.fire(clip);
	}

	/**
	 * Creates a new Projectile with complex direction
	 * 
	 * @param level
	 *            : What level does it render on
	 * @param x
	 *            : The X position it will spawn at
	 * @param y
	 *            : The Y position it will spawn at
	 * @param width
	 *            the width of the sprite
	 * @param height
	 *            the height of the sprite
	 * @param tileNumber
	 *            : the tile id on the SpriteSheet (xTile) + (yTile) *
	 *            getSpriteSheet().boxes
	 * @param speed
	 *            : The speed at which the projectile will move (player speed is
	 *            1)
	 * @param direction1
	 *            : The x velocity -- currently broken, only 3 or 4
	 * @param direction2
	 *            : The y velocity - currently broken, only 1 or 0
	 * @param mob
	 *            the mob that fired the projectile
	 * @param damage
	 *            the damage this projectile should do
	 * @param clip
	 *            the sound this projectile makes
	 * 
	 */
	public Projectile(Level level, int x, int y, int width, int height, int tileNumber, int speed, int xPos,
			int yPos, Mob mob, int damage, Clip clip) {
		super(level, x, y);
		this.x = x;
		this.y = y;
		this.tileNumber = tileNumber;
		this.speed = speed;
		setBounds(getX(), getY(), width, height);
		calcChange(xPos, yPos);
		this.mob = mob;
		this.damage = damage;

		SoundHandler.fire(clip);
	}

	/**
	 * Calculates the x and y points it will move to
	 * 
	 * @param direction
	 *            the direction it was fired
	 */
	private void calcSimpleDirection(Direction direction) {
		switch (direction) {
		case SOUTH:
			dy++;
			break;
		case NORTH:
			dy--;
			break;
		case WEST:
			dx--;
			break;
		case EAST:
			dx++;
			break;
		default:
			break;

		}
	}

	/**
	 * Calculates the change in x and y when at detailed angles Finds the unit
	 * vectors between THIS coordinates and the specified coordinates Treats
	 * THIS position as the origin
	 * 
	 * @param x
	 *            the x coordinate destination
	 * @param y
	 *            the y coordinate destination
	 */
	private void calcChange(int x, int y) {

		// relative x coordinates
		double xa = x - getX();

		// relative y coordinates
		double ya = y - getY();

		// magnitude = squareroot of x^2 + y^2
		double magnitude = Math.sqrt(xa * xa + ya * ya);

		// j hat = Vx / magnitude
		dx = xa / magnitude;

		// i hat = Vy / magnitude
		dy = ya / magnitude;
		
	}
	
	int test = 0;

	/**
	 * Updates the projectile TODO levels should remove projectiles when player
	 * enters/exits buildings
	 */
	public void tick() {

		// move the projectile
		move();

		// check for collision with solid tile
		if (isOnSolidTile()) {
			onDestroyed();
			return;
		}

		isBehindBuilding = false;

		// check for collisions
		for (int i = 0; i < getLevel().getEntities().size(); i++) {
			
			Entity e = getLevel().getEntities().get(i);

			// damage a mob
			if (e instanceof Mob) {
				if (this.getBounds().intersects(e.getBounds()) && e != mob && !((Mob) e).isDead()) {
					((Mob) e).damage(damage);
					onDestroyed();
					break;
				}
				// disappear if hitting a building
			} else if (e instanceof SolidEntity) {
				if (this.getBounds().intersects(e.getBounds())) {
					if (e instanceof Vehicle) {
						((Vehicle) e).damage(damage);
					}
					onDestroyed();
					break;
				} else if (this.getBounds().intersects(((SolidEntity) e).getShadow())) {
					isBehindBuilding = true;
				}
			}
		}
	}

	/**
	 * Moves the entity in different directions Also updates the bounds
	 */
	private void move() {
		x += speed * dx;
		y += speed * dy;
		getBounds().setLocation(getX(), getY());
	}

	/**
	 * Display the projectile on the screen
	 */
	public void render(Screen screen) {

		// don't render if it isn't visible
		if (isBehindBuilding) {
			return;
		}

		screen.render(getX(), getY(), tileNumber, getColor(), sheet);
	}

	/**
	 * @return True if the projectile is on a solid tile
	 */
	private boolean isOnSolidTile() {
		Tile lastTile = getLevel().getTile(getX() >> 3, getY() >> 3);
		return lastTile.isSolid();
	}

	protected abstract int[] getColor();
	
	/**
	 * @return the X coordinate of the entity
	 */
	public int getX() {
		return (int) x;
	}

	/**
	 * @return the Y coordinate of the entity
	 */
	public int getY() {
		return (int) y;
	}

	/**
	 * @return the spritesheet
	 */
	protected SpriteSheet getSpriteSheet() {
		return sheet;
	}

	/**
	 * Executes this code when the projectile will be removed
	 */
	protected void onDestroyed() {
		getLevel().remove(this);
	}
	
	/**
	 * @return true if the projectile is behind a building
	 */
	public boolean isBehindBuilding() {
		return isBehindBuilding;
	}

}
