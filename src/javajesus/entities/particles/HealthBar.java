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

	// serialization
	private static final long serialVersionUID = -4825483165347265874L;

	// change in y offset on spritesheet
	private int yOffset;

	// starting y tile on the spritesheet
	private int yTile;

	// segment compared to total number of health bar sprites
	private static final double SEGMENT = 1 / 13.0;

	// the entity it follows
	private final Damageable entity;

	// Spritesheet of the health bar
	private final static SpriteSheet sheet = SpriteSheet.particles;

	// color of the healthbar
	private final static int[] color = { 0xFF111111, 0xFF000000, 0xFFDD0000 };

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

		// instance data
		this.entity = entity;
		this.yTile = 2;
	}

	/**
	 * Displays the healthbar on the screen
	 */
	public void render(Screen screen) {

		// health bar has a left and right component
		screen.render(getX(), getY(), (yTile + yOffset) * sheet.getNumBoxes(), color, false, sheet);
		screen.render(getX() + 8, getY(), 1 + (yTile + yOffset) * sheet.getNumBoxes(), color, false, sheet);
	}

	/**
	 * Updates the healthbar 60 times per second
	 */
	public void tick() {

		// divide the healthbar into 13 chunks with a different color
		if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1) {
			yOffset = 0;
			color[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - SEGMENT) {
			yOffset = 1;
			color[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 2 * SEGMENT) {
			yOffset = 2;
			color[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 3 * SEGMENT) {
			yOffset = 3;
			color[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 4 * SEGMENT) {
			yOffset = 4;
			color[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 5 * SEGMENT) {
			yOffset = 5;
			color[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 6 * SEGMENT) {
			yOffset = 6;
			color[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 7 * SEGMENT) {
			yOffset = 7;
			color[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 8 * SEGMENT) {
			yOffset = 8;
			color[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 9 * SEGMENT) {
			yOffset = 9;
			color[2] = 0xFFE50000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 10 * SEGMENT) {
			yOffset = 10;
			color[2] = 0xFFE50000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 11 * SEGMENT) {
			yOffset = 11;
			color[2] = 0xFFE50000;
		} else {
			yOffset = 12;
			color[2] = 0xFFE50000;
		}
	}

}