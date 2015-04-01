package ca.javajesus.game.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.javajesus.game.JavaRectangle;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.level.Level;

public abstract class Entity implements Serializable {

	protected static final long serialVersionUID = 1L;

	protected SoundHandler sound;
	protected int x, y;
	protected Level level;
	protected JavaRectangle bounds;
	public List<JavaRectangle> returnObjects = new ArrayList<JavaRectangle>();

	public Entity(Level level) {
		init(level);
		sound = SoundHandler.sound;
	}

	public int getX() {
		return x;
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
	
	public JavaRectangle getBounds() {
		return bounds;
	}

	public void setHitBox(JavaRectangle bounds) {
		this.bounds = bounds;
	}

	public abstract void tick();

	public abstract void render(Screen screen);

}
