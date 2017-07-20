package javajesus.entities.projectiles;

import javax.sound.sampled.Clip;

import javajesus.Hideable;
import javajesus.SoundHandler;
import javajesus.entities.Damageable;
import javajesus.entities.Entity;
import javajesus.entities.Mob;
import javajesus.entities.SolidEntity;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A projectile is a fleeting entity that moves very fast across the screen to deal damage
 */
public abstract class Projectile extends Entity implements Hideable {

	// the stats of the projectile
	private int speed, damage;

	// the change in x and y that the projectile will travel towards
	private double dx, dy;

	// the mob that fired the projectile
	private final Mob mob;

	// the spritesheet
	private static final SpriteSheet sheet = SpriteSheet.projectiles;

	// position on the spritesheet
	private int xTile, yTile;

	// do not render if the projectile is behind a building
	private boolean isBehindBuilding;
	
	// x and y coordinates should be in double precision
	private double x, y;
	
	// color of the projectile
	private final int[] color;

	/**
	 * Creates a new Projectile with a single direction
	 * 
	 * @param level -  What level it renders on
	 * @param x - The X position it will spawn at
	 * @param y - The Y position it will spawn at
	 * @param lateralWidth - the pixel width of the EAST - WEST alignment
	 * @param lateralHeight - the pixel height of the EAST - WEST alignment
	 * @param longitudinalWidth - the pixel width of the NORTH - SOUTH alignment
	 * @param longitudinalHeight - the pixel height of the NORTH - SOUTH alignment
	 * @param xTile - the x tile on the spritesheet
	 * @param yTile - the FIRST y tile on the spritesheet
	 * @param speed - The speed at which the projectile will move
	 * @param direction -  The direction it will move; NORTH, SOUTH, EAST, or WEST
	 * @param mob - the mob that fired the projectile
	 * @param damage - the damage this projectile should do on impact
	 * @param color - color set of the projectile
	 * @param clip - the sound this projectile makes on fire
	 */
	public Projectile(final Level level, int x, int y, int lateralWidth, int lateralHeight, int longitudinalWidth,
	        int longitudinalHeight, int xTile, int yTile, int speed, Direction direction, final Mob mob, int damage,
	        final int[] color, final Clip clip) {
		super(level, x, y);
		
		// play a sound
		SoundHandler.fire(clip);
		
		// instance data
		this.x = x;
		this.y = y;
		this.xTile = xTile;
		this.yTile = yTile;
		this.speed = speed;
		this.mob = mob;
		this.damage = damage;
		this.color = color;
		
		// calculate the direction it will move to
		calcDirection(direction);
		adjustSpriteDirection();
		
		// set the bounds
		setBounds(getX(), getY(), lateralWidth, lateralHeight, longitudinalWidth, longitudinalHeight);
	}

	/**
	 * Creates a new Projectile with complex direction
	 * 
	 * @param level -  What level it renders on
	 * @param x - The X position it will spawn at
	 * @param y - The Y position it will spawn at
	 * @param lateralWidth - the pixel width of the EAST - WEST alignment
	 * @param lateralHeight - the pixel height of the EAST - WEST alignment
	 * @param longitudinalWidth - the pixel width of the NORTH - SOUTH alignment
	 * @param longitudinalHeight - the pixel height of the NORTH - SOUTH alignment
	 * @param xTile - the x tile on the spritesheet
	 * @param yTile - the FIRST y tile on the spritesheet
	 * @param speed - The speed at which the projectile will move
	 * @param direction -  The direction it will move; NORTH, SOUTH, EAST, or WEST
	 * @param xPos - the x coordinate it will travel to
	 * @param yPos -  the y coordinate it will travel to
	 * @param mob - the mob that fired the projectile
	 * @param damage - the damage this projectile should do on impact
	 * @param color - color set of the projectile
	 * @param clip - the sound this projectile makes on fire
	 */
	public Projectile(final Level level, int x, int y, int lateralWidth, int lateralHeight, int longitudinalWidth,
	        int longitudinalHeight, int xTile, int yTile, int speed, int xPos, int yPos, final Mob mob, int damage,
	        final int[] color, final Clip clip) {
		super(level, x, y);
		
		// play a sound
		SoundHandler.fire(clip);
		
		// instance data
		this.x = x;
		this.y = y;
		this.xTile = xTile;
		this.yTile = yTile;
		this.speed = speed;
		this.mob = mob;
		this.damage = damage;
		this.color = color;
		
		// calculate the direction it will travel to
		calcDirection(xPos, yPos);
		adjustSpriteDirection();
		
		// set the bounds
		setBounds(getX(), getY(), lateralWidth, lateralHeight, longitudinalWidth, longitudinalHeight);
	}

	/**
	 * Calculates the x and y points it will move to
	 * 
	 * @param direction - the direction it was fired
	 */
	private void calcDirection(Direction direction) {
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
	 * Calculates the change in x and y when at detailed angles 
	 * Finds the unit vectors between this  projectile's coordinates and the specified coordinates 
	 * Treats this projectile's position as the origin
	 * 
	 * @param x - the x coordinate destination
	 * @param y - the y coordinate destination
	 */
	private void calcDirection(int x, int y) {

		// distance along the x axis
		double xa = x - getX();

		// distance along the y axis
		double ya = y - getY();

		// pythagorean theorem
		double magnitude = Math.sqrt(xa * xa + ya * ya);

		// j hat = Vx / magnitude
		dx = xa / magnitude;

		// i hat = Vy / magnitude
		dy = ya / magnitude;
		
	}
	
	/**
	 * Sets the correct bounds of the projectile
	 * Must be called AFTER DIRECTION HAS BEEN CALCULATED
	 * 
	 * @param x - x location
	 * @param y - y location
	 * @param xWidth - the pixel width of the EAST - WEST alignment
	 * @param xHeight - the pixel height of the EAST - WEST alignment
	 * @param yWidth - the pixel width of the NORTH - SOUTH alignment
	 * @param yHeight - the pixel width of the NORTH - SOUTH alignment
	 */
	private void setBounds(int x, int y, int xWidth, int xHeight, int yWidth, int yHeight) {
		
		// east - west has priority
		if (dx != 0) {
			setBounds(x, y, xWidth, xHeight);
		} else {
			setBounds(x, y, yWidth, yHeight);
		}
	}
	
	/**
	 * Adjusts the offset of the projectile based on the direction
	 * Must be called AFTER direction is set
	 */
	private void adjustSpriteDirection() {
		
		// east
		if (dx > 0) {
			yTile += 0;
			
			// west
		} else if (dx < 0) {
			yTile += 1;
			
			// north
		} else if (dy < 0) {
			yTile += 2;
			
			// south
		} else {
			yTile += 3;
		}
	}
	
	/**
	 * Updates the projectile
	 */
	public void tick() {

		// move the projectile
		move();

		// check for collision with solid tile
		if (isOnSolidTile()) {
			destroy();
			return;
		}

		isBehindBuilding = false;

		// check for collisions
		for (int i = 0; i < getLevel().getDamageables().size(); i++) {
			
			Damageable e = getLevel().getDamageables().get(i);
			
			if (this.getBounds().intersects(e.getBounds()) && e != mob) {
				e.damage(damage);
				
				// for rendering behind buildings
				if (e instanceof SolidEntity) {
					if (this.getBounds().intersects(((SolidEntity) e).getShadow())) {
						isBehindBuilding = true;
					}
				}
				destroy();
			}
		}
	}

	/**
	 * Moves the entity in different directions 
	 * Also updates the bounds
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

		screen.render(getX(), getY(), xTile, yTile, sheet, false, color);
	}

	/**
	 * @return True if the projectile is on a solid tile
	 */
	private boolean isOnSolidTile() {
		return getLevel().getTileFromEntityCoords(getX(), getY()).isSolid();
	}

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
	 * Executes this code when the projectile will be removed
	 */
	protected void destroy() {
		getLevel().remove(this);
	}
	
	/**
	 * @return true if the projectile is behind a building
	 */
	public boolean isBehindBuilding() {
		return isBehindBuilding;
	}
	
	/**
	 * Projectiles won't be saved
	 */
	@Override
	public byte getId() {
		return -1;
	}

}
