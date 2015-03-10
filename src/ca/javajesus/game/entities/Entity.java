package ca.javajesus.game.entities;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public abstract class Entity {
	
	protected SoundHandler sound;
	public double x, y;
	protected Level level;
	protected boolean isSolid;
	
	public Entity(Level level){
		init(level);
		sound = SoundHandler.sound;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public final void init(Level level){
		this.level = level;
	}
		
	public abstract void tick();
	
	public abstract void render(Screen screen);
	
}
