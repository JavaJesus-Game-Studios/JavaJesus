package game.entities;

import game.SoundHandler;
import game.graphics.Screen;

import java.awt.Rectangle;
import java.io.Serializable;

import level.Level;

public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 7152333070540764158L;

	protected static transient SoundHandler sound;
	protected int x, y;
	protected Level level;
	protected Rectangle bounds;

	public Entity(Level level) {
		init(level);
		sound = SoundHandler.sound;
		bounds = new Rectangle(0, 0);
	}

	public int getX() {
		return x;
	}

	public static void initSound() {
		sound = SoundHandler.sound;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Level getLevel() {
		return level;
	}

	public final void init(Level level) {
		this.level = level;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setHitBox(Rectangle bounds) {
		this.bounds = bounds;
	}

	public abstract void tick();

	public abstract void render(Screen screen);

}
