package ca.javajesus.game.entities.projectiles;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.level.Level;

public class FireBall extends Projectile {

	public FireBall(Level level, double x, double y, int direction, Mob mob) {
		super(level, 6, 6, 0, new int[] { 0xFFFFFF00, 0xFFF7790A, 0xFF880000 },
				x, y, 3, direction, mob, 3, SoundHandler.sound.fireball);
	}

	public FireBall(Level level, double x, double y, double xPos, double yPos,
			Mob mob) {
		super(level, 6, 6, 0, new int[] { 0xFFFFFF00, 0xFFF7790A, 0xFF880000 },
				x, y, 3, xPos, yPos, mob, 3, SoundHandler.sound.fireball);
	}

}
