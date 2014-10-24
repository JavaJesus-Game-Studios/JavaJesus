package ca.javajesus.game.entities;

import ca.javajesus.game.gfx.Colours;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class HealthBar extends Particle {

	private int yOffset;

	private static int healthBarColour = Colours.get(-1, 111, -1, 400);
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
		this.y = mob.y - 18 + yOffset;

		screen.render(this.x + 3, this.y, tileNumber, color, 1, 1, sheet);
		screen.render(this.x - 5, this.y, tileNumber + 1, color, 1, 1, sheet);
	}

	public void setOffset(int yTileOffset) {
		this.tileNumber = yTileOffset * 32;
	}
}