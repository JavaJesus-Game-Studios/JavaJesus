package game.entities.projectiles;

import game.SoundHandler;
import game.entities.Mob;
import level.Level;
import utility.Direction;

/*
 * an arrow shot by native americans
 */
public class Arrow extends Projectile {

	private static final long serialVersionUID = 4965425722844381258L;

	// the colorset for all arrows
	private static final int[] color = { 0xFF000000, 0xFF000000, 0xFFFFFF00 };

	/**
	 * Creates an arrow
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param xPos
	 *            the x coord to move to
	 * @param yPos
	 *            the y coord to move to
	 * @param mob
	 *            the mob that is firing
	 * @param damage
	 *            the damage of this arrow
	 */
	public Arrow(Level level, double x, double y, int xPos, int yPos, Mob mob, int damage) {
		super(level, x, y, 2, 1, 1, 6, xPos, yPos, mob, damage, SoundHandler.laser);

		adjustOffset(mob);
	}

	/**
	 * Creates a arrow with a simple direction
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param direction
	 *            the direction the arrow should move
	 * @param mob
	 *            the mob that is firing
	 * @param damage
	 *            the damage of this arrow
	 */
	public Arrow(Level level, double x, double y, Direction direction, Mob mob, int damage) {
		super(level, x, y, 2, 1, 1, 6, direction, mob, damage, SoundHandler.laser);

		adjustOffset(mob);

	}

	/**
	 * Adjusts the offset of the arrow
	 * 
	 * @param mob
	 *            the mob that is firing
	 */
	private void adjustOffset(Mob mob) {
		switch (mob.getDirection()) {
		case NORTH:
			this.tileNumber = 2 + 2 * getSpriteSheet().boxes;
			return;
		case SOUTH:
			this.tileNumber = 2 + 1 * getSpriteSheet().boxes;
			return;
		case WEST:
			this.tileNumber = 2 + 3 * getSpriteSheet().boxes;
			return;
		default:
			this.tileNumber = 2 + 0 * getSpriteSheet().boxes;
			return;
		}
	}

	@Override
	protected int[] getColor() {
		return color;
	}

}
