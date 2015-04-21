package ca.javajesus.game.entities.projectiles;

import javax.sound.sampled.Clip;

import ca.javajesus.game.entities.Mob;
import ca.javajesus.level.Level;

public class Laser extends Projectile {

	private static final long serialVersionUID = -8622210634865875442L;

	public Laser(Level level, double x, double y, double xPos, double yPos,
			Mob mob, double damage, Clip clip) {
		super(level, 2, 1, 1, new int[] { 0xFF000000, 0xFF000000, 0xFFD80202 }, x, y, 6, xPos, yPos,
				mob, damage, clip);
	}

	public Laser(Level level, double x, double y, int direction, Mob mob, double damage, Clip clip) {
		super(level, 2, 1, 1, new int[] { 0xFF000000, 0xFF000000, 0xFFD80202 }, x, y, 6, direction,
				mob, damage, clip);
	}

}