package ca.javajesus.game.entities.projectiles;

import java.awt.Rectangle;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Projectile extends Entity {

	// In the future with guns, create two vars, firerate in gun class and
	// firerate in player class. The firerate in gun class will be static and
	// final, and it will be used to countdown the ticks till next shoot, such
	// as if (gun.firerate >0) player.firerate--; then reset when it reaches 0

	private double speed, range, damage;
	/** Origin points */
	private final double xOrigin, yOrigin;
	private double xPoint, yPoint;
	private int yOffset = 0;
	protected Rectangle hitBox;
	protected Mob mob;
	protected final SpriteSheet sheet = SpriteSheet.particles;
	protected int tileNumber;
	protected int color;
	protected int width;
	protected int height;

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
		super(level);
		this.tileNumber = tileNumber;
		this.color = color;
		this.x = x;
		this.y = y;
		this.speed = speed;
		calcSimpleDirection(direction);
		this.hitBox = new Rectangle(width, height);
		this.mob = mob;

		xOrigin = x;
		yOrigin = y;
		range = 200;
		damage = 5;
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
			int color, double x, double y, int speed, double xPos, double yPos,
			Mob mob) {
		super(level);
		this.tileNumber = tileNumber;
		this.color = color;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.hitBox = new Rectangle(width, height);
		calcAngle(xPos, yPos);
		this.mob = mob;

		xOrigin = x;
		yOrigin = y;
		range = 200;
		damage = 5;
	}

	private void calcAngle(double x, double y) {
		this.yPoint = (y - this.y) / 50;
		this.xPoint = (x - this.x) / 50;
	}

	public void tick() {
		if (distance() > range)
			level.remEntity(this);
	}

	// Pythag Theorem
	private double distance() {
		double d = Math.sqrt(Math.pow(Math.abs(xOrigin - x), 2)
				+ Math.pow(Math.abs(yOrigin - y), 2));
		return d;
	}

	public void render(Screen screen) {

		if (level.tileCollision(x, y, xPoint, yPoint, 2))
			level.remEntity(this);

		this.y += speed * yPoint;
		this.x += speed * xPoint;

		screen.render(this.x, this.y, tileNumber + (yOffset * 32), color, 1, 1,
				sheet);
		hitBox.setLocation((int) this.x, (int) this.y);
		for (Entity entity : level.getEntities()) {
			if (entity instanceof SolidEntity) {
				if (hitBox.intersects(((SolidEntity) entity).bounds)) {
					level.remEntity(this);
					return;
				}
			}
			if (entity instanceof Mob) {
				Mob mobs = (Mob) entity;
				if (hitBox.intersects(mobs.hitBox)) {
					if (mobs != mob) {
						mobs.damage((int) damage, (int) damage + 2);
						level.remEntity(this);
						if (mobs.hasDied && mob instanceof Player) {
							((Player) mob).score += 10;
						}
					}
				}
			}

		}
	}
}
