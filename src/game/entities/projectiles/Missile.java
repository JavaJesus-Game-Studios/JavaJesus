package game.entities.projectiles;

import javax.sound.sampled.Clip;
import game.SoundHandler;
import game.entities.Mob;
import game.entities.particles.Explosion;
import level.Level;
import utility.Direction;

/*
 * A missile explodes on impact
 */
public class Missile extends Projectile {

	private static final long serialVersionUID = 4423384187877615283L;

	// the colorset for all fireballs
	private static final int[] color = { 0xFF000000, 0xFF5B5B5B, 0xFFFFEA02 };

	/**
	 * Creates a missile
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
	 *            the damage of this missile
	 */
	public Missile(Level level, int x, int y, int xPos, int yPos, Mob mob, int damage) {
		super(level, x, y, 6, 6, 1, 5, xPos, yPos, mob, damage, SoundHandler.fireball);

		adjustOffset(mob);
	}

	/**
	 * Creates a missile with a simple direction
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param direction
	 *            the direction the missile should move
	 * @param mob
	 *            the mob that is firing
	 * @param damage
	 *            the damage of this missile
	 */
	public Missile(Level level, int x, int y, Direction direction, Mob mob, int damage, Clip clip) {
		super(level, x, y, 6, 6, 1, 5, direction, mob, damage, SoundHandler.fireball);

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
			this.tileNumber = 3 + 3 * getSpriteSheet().boxes;
			return;
		case SOUTH:
			this.tileNumber = 3 + 2 * getSpriteSheet().boxes;
			return;
		case EAST:
			this.tileNumber = 3 + 1 * getSpriteSheet().boxes;
			return;
		default:
			this.tileNumber = 4 + 1 * getSpriteSheet().boxes;
			return;
		}
	}

	@Override
	protected void onDestroyed() {

		getLevel().add(new Explosion(getLevel(), getX() + 4, getY() + 4));
		super.onDestroyed();

	}

	@Override
	protected int[] getColor() {
		return color;
	}

}
