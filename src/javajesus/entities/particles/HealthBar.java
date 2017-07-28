package javajesus.entities.particles;

import javajesus.entities.Damageable;
import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * A health bar follows underneath any entity
 */
public class HealthBar extends Entity {

	// change in x offset on spritesheet
	private int xOffset;

	// starting x tile on the spritesheet
	private int xTile;

	// segment compared to total number of health bar sprites
	private static final double SEGMENT = 1 / 13.0;

	// the entity it follows
	private final Damageable entity;

	// Spritesheet of the health bar
	private final static SpriteSheet sheet = SpriteSheet.statusBars;

	// color of the healthbar
	private final int[] color = { 0xFF111111, 0xFF000000, 0xFFDD0000 };

	/**
	 * Creates a health bar
	 * 
	 * @param level - the level it is on
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param entity - the entity it tracks
	 */
	public HealthBar(Level level, int x, int y, Damageable entity) {
		super(level, x, y);
		
		// set bounds'
		setBounds(x, y, 16, 8);

		// instance data
		this.entity = entity;
		this.xTile = 0;
		
	}

	/**
	 * Displays the healthbar on the screen
	 */
	public void render(Screen screen) {

		// health bar has a left and right component
		screen.render(getX(), getY(), xTile +xOffset, 0, sheet, false, color);
		screen.render(getX()+ 8, getY(),xTile + 1+ xOffset ,0, sheet, false, color);
	}

	/**
	 * Updates the healthbar 60 times per second
	 */
	public void tick() {

		// divide the healthbar into 13 chunks with a different color
		if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1) {
			xOffset = 0;
			color[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - SEGMENT) {
			xOffset = 2;
			color[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 2 * SEGMENT) {
			xOffset = 4;
			color[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 3 * SEGMENT) {
			xOffset = 6;
			color[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 4 * SEGMENT) {
			xOffset = 8;
			color[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 5 * SEGMENT) {
			xOffset = 10;
			color[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 6 * SEGMENT) {
			xOffset = 12;
			color[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 7 * SEGMENT) {
			xOffset = 14;
			color[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 8 * SEGMENT) {
			xOffset = 16;
			color[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 9 * SEGMENT) {
			xOffset = 18;
			color[2] = 0xFFE50000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 10 * SEGMENT) {
			xOffset = 20;
			color[2] = 0xFFE50000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 11 * SEGMENT) {
			xOffset = 22;
			color[2] = 0xFFE50000;
		} else {
			xOffset = 24;
			color[2] = 0xFFE50000;
		}
	}

	/**
	 * Health Bars won't be saved
	 */
	@Override
	public byte getId() {
		return -1;
	}

	@Override
	public long getData() {
		return 0;
	}

}