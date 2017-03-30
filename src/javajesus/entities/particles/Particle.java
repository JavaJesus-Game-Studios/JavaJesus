package javajesus.entities.particles;

import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
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
	public Particle(Level level, int x, int y, int tileNumber, int[] color) {
		super(level, x, y);
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
