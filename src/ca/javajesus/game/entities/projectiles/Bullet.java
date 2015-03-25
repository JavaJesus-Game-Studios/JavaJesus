package ca.javajesus.game.entities.projectiles;

import ca.javajesus.game.entities.Mob;
import ca.javajesus.level.Level;

public class Bullet extends Projectile {

	public Bullet(Level level, double x, double y, double xPos, double yPos,
			Mob mob, double damage) {
		super(level, 2, 1, 1, new int[] { 0xFF000000, 0xFF000000, 0xFFFFFF00 },
				x, y, 6, xPos, yPos, mob, damage);
		sound.fire(sound.gunshot);
	}

	public Bullet(Level level, double x, double y, int direction, Mob mob,
			double damage) {
		super(level, 2, 1, 1, new int[] { 0xFF000000, 0xFF000000, 0xFFFFFF00 },
				x, y, 6, direction, mob, damage);
		sound.fire(sound.gunshot);
	}

}
