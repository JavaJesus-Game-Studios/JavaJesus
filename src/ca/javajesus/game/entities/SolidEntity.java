package ca.javajesus.game.entities;

import java.awt.Rectangle;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class SolidEntity extends Entity {

	public Rectangle bounds;
	public Rectangle shadow;
	protected int width;
	protected int height;
	//public final int x, y;
	protected int color;

	public SolidEntity(Level level, int x, int y, int width, int height) {
		super(level);
		this.isSolid = true;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.shadow = new Rectangle(width, (height / 2));
		this.shadow.setLocation(x, y);
		this.bounds = new Rectangle(width, (height / 2) - 8);
		this.bounds.setLocation(x, y + shadow.height);
	}

	public void tick() {

	}

	public void render(Screen screen) {

	}

}
