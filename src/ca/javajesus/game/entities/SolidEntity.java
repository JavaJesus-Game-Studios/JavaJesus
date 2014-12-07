package ca.javajesus.game.entities;

import java.awt.Rectangle;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class SolidEntity extends Entity {

	public final Rectangle bounds;
	public final double x, y;

	public SolidEntity(Level level, double x, double y, int width, int height) {
		super(level);
		this.isSolid = true;
		this.x = x;
		this.y = y;
		this.bounds = new Rectangle(width, height);
		this.bounds.setLocation((int) x, (int) y);
	}

	public void tick() {

	}

	public void render(Screen screen) {

	}

}
