package game.entities.particles;

import game.entities.Entity;
import game.graphics.Screen;
import game.graphics.SpriteSheet;

import java.awt.Rectangle;
import java.util.Random;

import level.Level;

public class Particle extends Entity {

	private static final long serialVersionUID = -1910855426543317119L;
	
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
		this.bounds = new Rectangle(0, 0);
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
	
	public String toString() {
		return "Particle";
	}

}
