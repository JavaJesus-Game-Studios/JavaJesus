package javajesus.entities.particles;

import javajesus.entities.Damageable;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import level.Level;

/*
 * A healthbar follows underneath any entity
 */
public class HealthBar extends Particle {

	private static final long serialVersionUID = -4825483165347265874L;

	// change in y offset on spritesheet
	private int yChange;

	// segment compared to total number of health bar sprites
	private static final double SEGMENT = 1 / 13.0;

	// the entity it follows
	private Damageable entity;

	/**
	 * Creates a health bar
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param entity
	 *            the entity it tracks
	 */
	public HealthBar(Level level, int x, int y, Damageable entity) {
		super(level, x, y, 2 * SpriteSheet.particles.getNumBoxes(), new int[] { 0xFF111111, 0xFF000000, 0xFFDD0000 });
		this.entity = entity;
	}

	/**
	 * Displays the healthbar on the screen
	 */
	public void render(Screen screen) {
		
		screen.render(getX(), getY(), getTileNumber() + yChange * getSpriteSheet().getNumBoxes(), getColor(), false,
				getSpriteSheet());
		screen.render(getX() + 8, getY(), getTileNumber() + 1 + yChange * getSpriteSheet().getNumBoxes(), getColor(), false,
				getSpriteSheet());
	}

	/**
	 * Updates the healthbar 60 times per second
	 */
	public void tick() {

		if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1) {
			yChange = 0;
			getColor()[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - SEGMENT) {
			yChange = 1;
			getColor()[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 2 * SEGMENT) {
			yChange = 2;
			getColor()[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 3 * SEGMENT) {
			yChange = 3;
			getColor()[2] = 0xFF0079E0;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 4 * SEGMENT) {
			yChange = 4;
			getColor()[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 5 * SEGMENT) {
			yChange = 5;
			getColor()[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 6 * SEGMENT) {
			yChange = 6;
			getColor()[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 7 * SEGMENT) {
			yChange = 7;
			getColor()[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 8 * SEGMENT) {
			yChange = 8;
			getColor()[2] = 0xFFFF6000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 9 * SEGMENT) {
			yChange = 9;
			getColor()[2] = 0xFFE50000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 10 * SEGMENT) {
			yChange = 10;
			getColor()[2] = 0xFFE50000;
		} else if ((double) entity.getCurrentHealth() / entity.getMaxHealth() >= 1 - 11 * SEGMENT) {
			yChange = 11;
			getColor()[2] = 0xFFE50000;
		} else {
			yChange = 12;
			getColor()[2] = 0xFFE50000;
		}
	}

}