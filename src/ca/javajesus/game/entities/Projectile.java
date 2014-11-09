package ca.javajesus.game.entities;

import java.awt.Rectangle;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Projectile extends Particle {

	private int speed;
	private double xPoint;
	private double yPoint;
	private int yOffset = 0;
	protected Rectangle hitBox;
	protected Mob mob;

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
	public Projectile(Level level, int width, int height, int tileNumber,
			int color, double x, double y, int speed, int direction, Mob mob) {
		super(level, tileNumber, color, x, y);
		this.speed = speed;
		calcSimpleDirection(direction);
		this.hitBox = new Rectangle(width, height);
		this.mob = mob;
	}

	private void calcSimpleDirection(int direction) {

		switch (direction) {
		case 0:
			xPoint = 0;
			yPoint = -1;
			yOffset++;
			break;
		case 1:
			xPoint = 0;
			yPoint = 1;
			yOffset++;
			break;
		case 2:
			xPoint = -1;
			yPoint = 0;
			break;
		case 3:
			xPoint = 1;
			yPoint = 0;
			break;
		default:
			xPoint = 0;
			yPoint = 0;
			break;

		}
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
	public Projectile(Level level, int width, int height, int tileNumber,
			int color, double x, double y, int speed, double xPos, double yPos) {
		super(level, tileNumber, color, x, y);
		this.speed = speed;
		this.hitBox = new Rectangle(width, height);
		calcSlope(xPos, yPos);
	}

	private void calcSlope(double x, double y) {
		this.yPoint = (y - this.y) / 50;
		this.xPoint = (x - this.x) / 50;
	}

	public void tick() {
		if (this.x > level.width * 32 || this.x < 0
				|| this.y > level.height * 32 || this.y < 0) {
			level.remEntity(this);
		}
		if (this.speed == 0) {
			level.remEntity(this);
		}
	}

	public void render(Screen screen) {

		this.y += speed * yPoint;
		this.x += speed * xPoint;

		screen.render(this.x, this.y, tileNumber + (yOffset * 32), color, 1, 1,
				sheet);
		hitBox.setLocation((int) this.x, (int) this.y);
		for (Entity entity : level.getEntities()) {

			if (entity instanceof Mob) {
				if (hitBox.intersects(((Mob) entity).hitBox)) {
					if (entity != mob) {
						((Mob) entity).health--;
					}
				}
			}

		}
	}

}
