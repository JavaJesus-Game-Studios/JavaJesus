package game.entities.projectiles;

import game.entities.Mob;

import javax.sound.sampled.Clip;

import level.Level;

/*
 * A bullet projectile
 */
public class Bullet extends Projectile {

	private static final long serialVersionUID = 6386517640048489710L;

	// the colorset for all fireballs
	private static final int[] color = { 0xFF000000, 0xFF000000, 0xFFFFFF00 };

	// pixel size of a fireball
	private static final int SIZE = 2;

	public Bullet(Level level, double x, double y, double xPos, double yPos, Mob mob, double damage, Clip clip) {
		super(level, x, y, SIZE, SIZE, 1, 6, xPos, yPos, mob, damage, clip);
	}

	public Bullet(Level level, double x, double y, int direction, Mob mob, double damage, Clip clip) {
		super(level, x, y, SIZE, SIZE, 1, 6, direction, mob, damage, clip);
	}

	/**
	 * @return the colorset of the bullet
	 */
	@Override
	protected int[] getColor() {
		return color;
	}

}
