package ca.javajesus.game.entities.projectiles;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.level.Level;

public class Bullet extends Projectile {

	public Bullet(Level level, double x, double y, double xPos, double yPos,
			Mob mob) {
		super(level, 2, 1, 1, Colors.get(-1, -1, -1, 550), x, y, 6, xPos, yPos,
				mob);
		sound.play(SoundHandler.sound.gunshot);
	}

	public Bullet(Level level, double x, double y, int direction, Mob mob) {
		super(level, 2, 1, 1, Colors.get(-1, -1, -1, 550), x, y, 6, direction,
				mob);
		sound.play(SoundHandler.sound.gunshot);
	}

}
