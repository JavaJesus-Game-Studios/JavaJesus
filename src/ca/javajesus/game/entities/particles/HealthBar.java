package ca.javajesus.game.entities.particles;

import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class HealthBar extends Particle {

	private int yOffset;

	private static int healthBarColour = Colors.get(-1, 111, -1, 400);
	private Mob mob;

	public HealthBar(Level level, int tileNumber, double x, double y, Mob mob) {
		super(level, tileNumber, healthBarColour, x, y);
		this.mob = mob;
		this.yOffset = 0;
	}

	public HealthBar(Level level, int tileNumber, double x, double y, Mob mob,
			int yOffset) {
		super(level, tileNumber, healthBarColour, x, y);
		this.mob = mob;
		this.yOffset = yOffset;
	}

	public void render(Screen screen) {

		this.x = mob.x;
		this.y = mob.y + 8;

		screen.render(this.x + 3, this.y, tileNumber + 14 * 32, color, 1, 1,
				sheet);
		screen.render(this.x - 5, this.y, tileNumber + 1 + 14 * 32, color, 1,
				1, sheet);
	}

	public void setOffset(int yTileOffset) {
		this.tileNumber = yTileOffset * 32;
	}
}