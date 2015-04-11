package ca.javajesus.game.entities.projectiles;

import java.util.Random;

import javax.sound.sampled.Clip;

import ca.javajesus.game.JavaRectangle;
import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.vehicles.Vehicle;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;
import ca.javajesus.level.tile.Tile;

public class Projectile extends Entity {

	// In the future with guns, create two vars, firerate in gun class and
	// firerate in player class. The firerate in gun class will be static and
	// final, and it will be used to countdown the ticks till next shoot, such
	// as if (gun.firerate >0) player.firerate--; then reset when it reaches 0

	protected double speed;
	private double range;
	protected double damage;
	/** Origin points */
	private final double xOrigin, yOrigin;
	protected double xPoint;
	protected double yPoint;
	protected int yOffset = 0;
	public Mob mob;
	protected SpriteSheet sheet = SpriteSheet.particles;
	protected int tileNumber;
	protected int[] color;
	protected int width;
	protected int height;
	public boolean renderOnTop = false;
	protected double x, y;
	
	/**
	 * Creates a new Projectile will a single direction
	 * 
	 * @param level
	 *            : What level does it render on
	 * @param tileNumber
	 *            : the tile id on the SpriteSheet (xTile) + (yTile) *
	 *            sheet.boxes
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
			int[] color, double x, double y, int speed, int direction, Mob mob,
			double damage, Clip clip) {
		super(level);
		this.tileNumber = tileNumber;
		this.color = color;
		this.x = x;
		this.y = y;
		this.speed = speed;
		calcSimpleDirection(direction);
		this.bounds = new JavaRectangle(width, height, this);
		this.bounds.setLocation((int) x, (int) y);
		this.mob = mob;
		xOrigin = x;
		yOrigin = y;
		range = 200;
		this.damage = damage;
		sound.fire(clip);
	}

	private void calcSimpleDirection(int direction) {
		Random rand = new Random();
		switch (direction) {
		case 0:
			xPoint = (rand.nextDouble() - rand.nextDouble()) / 10;
			yPoint = -1;
			yOffset++;
			break;
		case 1:
			xPoint = (rand.nextDouble() - rand.nextDouble()) / 10;
			yPoint = 1;
			yOffset++;
			break;
		case 2:
			xPoint = -1;
			yPoint = (rand.nextDouble() - rand.nextDouble()) / 10;
			break;
		case 3:
			xPoint = 1;
			yPoint = (rand.nextDouble() - rand.nextDouble()) / 10;
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
	 *            : the tile id on the SpriteSheet (xTile) + (yTile) *
	 *            sheet.boxes
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
			int[] color, double x, double y, int speed, double xPos,
			double yPos, Mob mob, double damage, Clip clip) {
		super(level);
		this.tileNumber = tileNumber;
		this.color = color;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.bounds = new JavaRectangle(width, height, this);
		this.bounds.setLocation((int) x, (int) y);
		calcAngle(xPos, yPos);
		this.mob = mob;

		xOrigin = x;
		yOrigin = y;
		range = 200;
		this.damage = damage;
		
		sound.fire(clip);
	}

	private void calcAngle(double x, double y) {
		double angle = Math.atan((x - this.x) / (y - this.y));
		// this.yPoint = Math.cos(angle);
		// this.xPoint = Math.sin(angle);
		if (y - this.y < 0) {
			this.yPoint = -Math.cos(angle);
			this.xPoint = -Math.sin(angle);
		} else {
			this.yPoint = Math.cos(angle);
			this.xPoint = Math.sin(angle);
		}
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

		renderOnTop = true;

		if (hasCollided((int) x, (int) y)) {
			level.remEntity(this);
			return;
		}

		this.y += speed * yPoint;
		this.x += speed * xPoint;

		bounds.setLocation((int) this.x - (this.width / 2), (int) this.y
				- (this.height / 2));
		for (Entity entity : level.getEntities()) {
			if (entity instanceof SolidEntity) {
				if (bounds.intersects(((SolidEntity) entity).getBounds())) {
					level.remEntity(this);
					return;
				} else if (bounds.intersects(((SolidEntity) entity).shadow)) {
					renderOnTop = false;
				}
			}
			if (entity instanceof Mob) {
				Mob mobs = (Mob) entity;
				if (bounds.intersects(mobs.getBounds())) {
					if (mobs != mob) {
						if (mobs instanceof Vehicle) {
							mobs.damage((int) damage, (int) damage + 4);
							level.remEntity(this);
						} else if (!mobs.isDead()) {
							mobs.damage((int) damage, (int) damage + 4);
							level.remEntity(this);
							if (mobs.getHealth() < 0 && mob instanceof Player) {
								((Player) mob).score += 10;
							}
						}
					}
				}
			}

		}
		screen.render((int) this.x, (int) this.y, tileNumber
				+ (yOffset * sheet.boxes), color, 1, 1, sheet);
	}

	protected boolean isSolidTile(int xa, int ya, int x, int y) {
		if (level == null) {
			return false;
		}
		int xx = (int) this.x;
		int yy = (int) this.y;
		Tile lastTile = level.getTile((xx + x) >> 3, (yy + y) >> 3);
		// Tile newTile = level.getTile((xx + x + xa) >> 3, (yy + y + ya) >> 3);
		if (lastTile.isSolid()) {
			return true;
		}
		return false;
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 1;
		int xMax = 2;
		int yMin = 1;
		int yMax = 2;

		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}

		return false;
	}

}
