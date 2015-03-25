package ca.javajesus.game.entities.projectiles;

import ca.javajesus.game.entities.Mob;
import ca.javajesus.level.Level;

public class Laser extends Projectile {

	public Laser(Level level, double x, double y, double xPos, double yPos,
			Mob mob, double damage) {
		super(level, 2, 1, 1, new int[] { 0xFF000000, 0xFF000000, 0xFFD80202 }, x, y, 6, xPos, yPos,
				mob, damage);
		sound.fire(sound.gunshot);
	}

	public Laser(Level level, double x, double y, int direction, Mob mob, double damage) {
		super(level, 2, 1, 1, new int[] { 0xFF000000, 0xFF000000, 0xFFD80202 }, x, y, 6, direction,
				mob, damage);
		sound.fire(sound.gunshot);
	}

}