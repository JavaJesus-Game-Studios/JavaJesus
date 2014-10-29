package ca.javajesus.game.entities;

import java.awt.Rectangle;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Projectile extends Particle {

	private int speed;
	private int direction;
	private int direction2;
	private final Rectangle BOX = new Rectangle(10, 10);

	/**
	 * Creates a new Projectile will a single direction
	 * 
	 * @param level
	 *            : What level does it render on
	 * @param tileNumber
	 *            : the tile id on the SpriteSheet (xTile) + (yTile) * 32
	 * @param color
	 *            : gets the color, use Colours.get()
	 * @param x
	 *            : The X position it will spawn at
	 * @param y
	 *            : The Y position it will spawn at
	 * @param speed
	 *            : The speed at which the projectile will move (player speed is
	 *            1)
	 * @param direction
	 *            : The direction it will move; Currently only 0 1 2 or 3
	 */
	public Projectile(Level level, int tileNumber, int color, double x,
			double y, int speed, int direction) {
		super(level, tileNumber, color, x, y);
		this.speed = speed;
		this.direction = direction;
		this.direction2 = 4;
	}

	/**
	 * Creates a new Projectile with complex direction
	 * 
	 * @param level
	 *            : What level does it render on
	 * @param tileNumber
	 *            : the tile id on the SpriteSheet (xTile) + (yTile) * 32
	 * @param color
	 *            : gets the color, use Colours.get()
	 * @param x
	 *            : The X position it will spawn at
	 * @param y
	 *            : The Y position it will spawn at
	 * @param speed
	 *            : The speed at which the projectile will move (player speed is
	 *            1)
	 * @param direction1
	 *            : The x velocity -- currently broken, only 3 or 4
	 * @param direction2
	 *            : The y velocity - currently broken, only 1 or 0
	 */
	public Projectile(Level level, int tileNumber, int color, double x,
			double y, int speed, int direction1, int direction2) {
		super(level, tileNumber, color, x, y);
		this.speed = speed;
		this.direction = direction1;
		this.direction2 = direction2;
	}

	public void tick() {
		if (this.x > level.width * 32 || this.x < 0
				|| this.y > level.height * 32 || this.y < 0) {
			level.remEntity(this);
		}
		if (this.speed == 0 || (direction == 4 && direction2 == 4)) {
			level.remEntity(this);
		}
	}

	public void render(Screen screen) {
		switch (direction) {
		case 0: {
			this.y -= speed;
			break;
		}
		case 1: {
			this.y += speed;
			break;
		}
		case 2: {
			this.x -= speed;
			break;
		}
		case 3: {
			this.x += speed;
			break;
		}
		default:
			break;
		}
		switch (direction2) {
		case 0: {
			this.y -= speed;
			break;
		}
		case 1: {
			this.y += speed;
			break;
		}
		case 2: {
			this.x -= speed;
			break;
		}
		case 3: {
			this.x += speed;
			break;
		}
		default:
			break;
		}
		screen.render(this.x, this.y, tileNumber, color, 1, 1, sheet);
		BOX.setLocation((int) this.x, (int) this.y);
		for (Entity entity : level.getEntities()) {

			if (entity instanceof Mob) {
				if (BOX.intersects(((Mob) entity).hitBox)) {
					((Mob) entity).health--;
				}
			}

		}
	}

}
