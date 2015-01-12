package ca.javajesus.game.entities;

import java.awt.Rectangle;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class SolidEntity extends Entity {

	public final Rectangle bounds;
	public final Rectangle shadow;
	public final double x, y;
	protected int color;

	public SolidEntity(Level level, double x, double y, int width, int height) {
		super(level);
		this.isSolid = true;
		this.x = x;
		this.y = y;
		this.shadow = new Rectangle(width, (height / 2));
		this.shadow.setLocation((int) x, (int) y);
		this.bounds = new Rectangle(width, (height / 2) - 8);
		this.bounds.setLocation((int) x, (int) y + shadow.height);
	}

	public void tick() {

	}

	public void render(Screen screen) {

	}

}
