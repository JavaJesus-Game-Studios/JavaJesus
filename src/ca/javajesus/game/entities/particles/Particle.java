package ca.javajesus.game.entities.particles;

import java.util.Random;

import ca.javajesus.game.JavaRectangle;
import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class Particle extends Entity {

	protected SpriteSheet sheet = SpriteSheet.particles;
	protected int tileNumber;
	protected int[] color;
	protected int width;
	protected int height;
	protected Random random = new Random();
	protected double x, y;

	public Particle(Level level, int tileNumber, int[] color, double x, double y) {
		super(level);
		this.tileNumber = tileNumber;
		this.color = color;
		this.x = x;
		this.y = y;
		this.bounds = new JavaRectangle(0, 0, this);
	}

	public void tick() {
		if (this.x > level.width * sheet.boxes || this.x < 0
				|| this.y > level.height * sheet.boxes || this.y < 0) {
			if (!(this instanceof HealthBar)) {
				level.remEntity(this);
			}
		}
	}

	public void render(Screen screen) {
		screen.render((int) this.x, (int) this.y, tileNumber, color, 1, 1,
				sheet);
	}

}
