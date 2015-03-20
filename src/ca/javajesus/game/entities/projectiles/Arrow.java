package ca.javajesus.game.entities.projectiles;

import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.level.Level;

public class Arrow  extends Projectile {

	public Arrow(Level level, double x, double y, double xPos, double yPos,
			Mob mob, double damage) {
		super(level, 2, 1, 1, Colors.get(-1, -1, -1, 550), x, y, 6, xPos, yPos,
				mob, damage);
		sound.fire(sound.gunshot);
	}

	public Arrow(Level level, double x, double y, int direction, Mob mob, double damage) {
		super(level, 2, 1, 1, Colors.get(-1, -1, -1, 550), x, y, 6, direction,
				mob, damage);
		sound.fire(sound.gunshot);
	}

}
