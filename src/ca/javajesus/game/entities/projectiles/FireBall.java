package ca.javajesus.game.entities.projectiles;

import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.level.Level;

public class FireBall extends Projectile {

	public FireBall(Level level, double x, double y, int direction, Mob mob) {
		super(level, 6, 6, 0,
				Colors.get(-1, 550, Colors.toHex("#F7790A"), 300), x, y, 3,
				direction, mob);
	}

	public FireBall(Level level, double x, double y, double xPos, double yPos,
			Mob mob) {
		super(level, 6, 6, 0,
				Colors.get(-1, 550, Colors.toHex("#F7790A"), 300), x, y, 3,
				xPos, yPos, mob);
	}

}
