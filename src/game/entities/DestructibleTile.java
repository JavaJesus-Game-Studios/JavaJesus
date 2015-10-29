package game.entities;

import game.graphics.Screen;
import game.graphics.SpriteSheet;

import java.awt.Rectangle;
import java.util.Random;

import level.Level;

public class DestructibleTile extends Entity {

	private static final long serialVersionUID = 3662337682501677071L;
	
	private int health;
	protected int healthTickCount = 0;
	protected int[] color;
	private int tileNumber;

	private Random random = new Random();

	public DestructibleTile(Level level, int x, int y, int defaultHealth) {
		super(level);
		this.x = x;
		this.y = y;
		this.bounds = new Rectangle(8, 8);
		this.bounds.setLocation(x, y);
		this.health = defaultHealth;

	}

	public void tick() {

	}

	public void render(Screen screen) {
		screen.render(x, y, tileNumber, color, 0, 1, SpriteSheet.tiles);
	}

	/** Triggers the death animation and closure */
	public void kill() {

		level.remEntity(this);
	}

	public void damage(int a, int b) {
		int damage = random.nextInt(b - a + 1) + a;
		if (damage > 0)
			this.health -= damage;
		if (health <= 0) {
			kill();
		}
	}

}
