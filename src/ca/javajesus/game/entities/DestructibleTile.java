package ca.javajesus.game.entities;

import java.awt.Rectangle;
import java.util.Random;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class DestructibleTile extends Entity {

	private int health;
	private Rectangle hitBox;
	protected int healthTickCount = 0;
	protected int color;
	private int tileNumber;

	private Random random = new Random();

	public DestructibleTile(Level level, int x, int y, int defaultHealth) {
		super(level);
		this.isSolid = true;
		this.x = x;
		this.y = y;
		this.hitBox = new Rectangle(8, 8);
		this.hitBox.setLocation(x, y);
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
