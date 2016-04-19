package game.entities.projectiles;

import level.Level;
import game.SoundHandler;
import game.entities.Mob;

/*
 * A fireball projectile usually made by demons
 */
public class FireBall extends Projectile {

	private static final long serialVersionUID = -4145206809321456052L;

	// the colorset for all fireballs
	private static final int[] color = { 0xFFFFFF00, 0xFFF7790A, 0xFF880000 };

	// pixel size of a fireball
	private static final int SIZE = 6;

	public FireBall(Level level, double x, double y, int direction, Mob mob, int damage) {
		super(level, x, y, SIZE, SIZE, 0, 1, direction, mob, damage, SoundHandler.fireball);
	}

	public FireBall(Level level, double x, double y, double xPos, double yPos, Mob mob, int damage) {
		super(level, x, y, SIZE, SIZE, 0, 1, xPos, yPos, mob, damage, SoundHandler.fireball);
	}

	@Override
	protected int[] getColor() {
		return color;
	}

}
