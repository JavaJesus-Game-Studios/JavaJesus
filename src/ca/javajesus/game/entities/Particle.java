package ca.javajesus.game.entities;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Particle extends Entity {
	protected final SpriteSheet sheet = SpriteSheet.particles;
	protected int tileNumber;
	protected int color;
	
	public Particle(Level level, int tileNumber, int color, double x, double y) {
		super(level);
		this.tileNumber = tileNumber;
		this.color = color;
		this.x = x;
		this.y = y;
	}

	public void tick() {
		
	}

	public void render(Screen screen) {
		screen.render(this.x, this.y, tileNumber, color, 1, 1, sheet);
	}

}
