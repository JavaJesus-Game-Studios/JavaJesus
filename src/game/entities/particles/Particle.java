package game.entities.particles;

import game.entities.Entity;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import level.Level;

/*
 * A Particle is an entity that cannot interact with others with the same detail as mobs
 * A particle is not weaponized, mostly aesthetic
 */
public class Particle extends Entity {

	private static final long serialVersionUID = -1910855426543317119L;

	// spritesheet the particle is on
	private SpriteSheet sheet = SpriteSheet.particles;

	// spritesheet coordinate: xTile + yTile * getSpriteSheet().boxes
	private int tileNumber;

	// colorset for the particle
	private int[] color;

	// coordinates in double precision
	private double x, y;

	/**
	 * Creates a particle
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord it is on
	 * @param y
	 *            the y coord it is on
	 * @param tileNumber
	 *            the spritesheet coord: xTile + yTile * sheet.boxes
	 * @param color
	 *            the colorset
	 */
	public Particle(Level level, double x, double y, int tileNumber, int[] color) {
		super(level, (int) x, (int) y);
		this.tileNumber = tileNumber;
		this.color = color;
	}

	/**
	 * No real update function
	 */
	public void tick() {
	}

	/**
	 * Displays the particle to the screen
	 */
	public void render(Screen screen) {
		screen.render(getX(), getY(), tileNumber, color, sheet);
	}

	/**
	 * Returns the name of the particle
	 */
	public String toString() {
		return "Particle";
	}

	/**
	 * @return the mob's spritesheet
	 */
	protected SpriteSheet getSpriteSheet() {
		return sheet;
	}

	/**
	 * Sets the mob's spritesheet to this sheet
	 */
	protected void setSpriteSheet(SpriteSheet sheet) {
		this.sheet = sheet;
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
	 * Changes the entity horizontally
	 * 
	 * @param x
	 *            the location to move the entity
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Changes the entity vertically
	 * 
	 * @param y
	 *            the location to move the entity
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the tile number
	 */
	protected int getTileNumber() {
		return tileNumber;
	}
	
	/**
	 * @param num the new tile number
	 */
	protected void setTileNumber(int num) {
		tileNumber = num;
	}

	/**
	 * @return the colorset
	 */
	protected int[] getColor() {
		return color;
	}

}
