package ca.javajesus.game.entities;

import java.awt.Rectangle;
//import java.util.Random;
import java.util.Random;

import ca.javajesus.game.entities.monsters.Demon;
import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;
import ca.javajesus.level.tile.Tile;

public abstract class Mob extends Entity {

	protected String name;
	protected double speed;
	protected int numSteps = 0;
	public boolean isMoving;
	public int movingDir = 1;
	protected int scale = 1;
	public double velocity;
	public int width;
	public int height;
	public double health;
	public double startHealth;
	public Rectangle hitBox;
	protected SpriteSheet sheet;
	protected HealthBar bar;
	public boolean hasDied;
	protected boolean onFire = false;
	protected int healthTickCount = 0;
	protected int lastDirection = 0;
	protected boolean movingRandomly = false;
	protected double scaledSpeed;
	public boolean isTargeted = false;
	protected Rectangle standBox;

	protected int strength, defense, accuracy, evasion;

	private Random random = new Random();

	public Mob(Level level, String name, double x, double y, int speed,
			int width, int height, SpriteSheet sheet, double defaultHealth) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.health = defaultHealth;
		this.startHealth = defaultHealth;
		this.hitBox = new Rectangle(width, height);
		this.standBox = new Rectangle(width + 4, height + 4);
		this.sheet = sheet;
		if (this instanceof Demon) {
			bar = new HealthBar(level, 0 + 2 * 32, this.x, this.y, this, -10);
		} else {
			bar = new HealthBar(level, 0 + 2 * 32, this.x, this.y, this);
		}
		if (level != null) {
			level.addEntity(bar);
		}
		this.defense = 1;
	}

	public void move(int xa, int ya, double speed) {
		velocity = this.speed * speed;
		if (xa != 0 && ya != 0) {
			move(xa, 0, velocity);
			move(0, ya, velocity);
			numSteps--;
			return;
		}
		numSteps++;
		if (!hasCollided(xa, ya)) {
			if (ya < 0)
				movingDir = 0;
			if (ya > 0)
				movingDir = 1;
			if (xa < 0)
				movingDir = 2;
			if (xa > 0)
				movingDir = 3;

			x += xa * velocity;
			y += ya * velocity;
		}
	}

	public abstract boolean hasCollided(int xa, int ya);

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	protected boolean isSolidTile(int xa, int ya, int x, int y) {
		if (level == null) {
			return false;
		}
		int xx = (int) this.x;
		int yy = (int) this.y;
		Tile lastTile = level.getTile((xx + x) >> 3, (yy + y) >> 3);
		Tile newTile = level.getTile((xx + x + xa) >> 3, (yy + y + ya) >> 3);
		if (!lastTile.equals(newTile) && newTile.isSolid()) {
			return true;
		}
		return false;
	}

	protected boolean isDamageTile(int xa, int ya, int x, int y) {
		if (level == null) {
			return false;
		}
		int xx = (int) this.x;
		int yy = (int) this.y;
		Tile lastTile = level.getTile((xx + x) >> 3, (yy + y) >> 3);
		Tile newTile = level.getTile((xx + x + xa) >> 3, (yy + y + ya) >> 3);
		if (!lastTile.equals(newTile) && newTile.isSolid()) {
			return true;
		}
		return false;
	}

	protected boolean isWaterTile(int xa, int ya, int x, int y) {
		if (level == null) {
			return false;
		}
		int xx = (int) this.x;
		int yy = (int) this.y;
		Tile lastTile = level.getTile((xx + x) >> 3, (yy + y) >> 3);
		Tile newTile = level.getTile((xx + x + xa) >> 3, (yy + y + ya) >> 3);
		if (!lastTile.equals(newTile) && newTile.equals(Tile.WATER)) {
			return true;
		}
		return false;
	}

	protected boolean isSolidEntityCollision(int xa, int ya) {
		for (Entity entity : level.getEntities()) {
			if (entity instanceof SolidEntity) {
				Rectangle temp = new Rectangle(
						((SolidEntity) entity).bounds.width,
						((SolidEntity) entity).bounds.height - 8);
				temp.setLocation((int) ((SolidEntity) entity).x - 2 * xa,
						(int) ((SolidEntity) entity).y - 2 * ya);
				if (this.hitBox.intersects(temp))
					return true;
			}

		}
		return false;
	}

	protected boolean isMobCollision() {
		if (level == null) {
			return false;
		}
		for (Mob mob : level.getMobs()) {
			if (mob == this)
				continue;
			if (this.hitBox.intersects(mob.hitBox))
				return true;
		}
		return false;
	}

	protected void moveAroundMobCollision() {

		int xa = 0;
		int ya = 0;

		for (Mob mob : level.getMobs()) {
			if (mob == this)
				continue;

			Rectangle intersection = hitBox.intersection(mob.hitBox);
			double xx = intersection.getCenterX();
			double yy = intersection.getCenterY();
			if ((int) xx > (int) this.hitBox.getCenterX()) {
				xa--;
			}
			if ((int) xx < (int) this.hitBox.getCenterX()) {
				xa++;
			}
			if ((int) yy > (int) this.hitBox.getCenterY()) {
				ya--;
			}
			if ((int) yy < (int) this.hitBox.getCenterY()) {
				ya++;
			}

			if (xa != 0 || ya != 0) {
				break;
			}
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya, scaledSpeed);
			isMoving = true;
		} else {
			isMoving = false;
		}
	}

	public String toString() {
		return name;
	}

	/** Updates the Health Bar */
	public void updateHealth() {

		checkTile(this.x, this.y);

		if (onFire) {
			healthTickCount++;
			health -= 0.2;
		}

		if (healthTickCount == 200 && onFire) {
			onFire = false;
			healthTickCount = 0;
		}

		if ((health > 11 * startHealth / 12.0) && (health <= startHealth)) {
			bar.setOffset(2);
		} else if ((health > 10 * startHealth / 12.0)
				&& (health <= 11 * startHealth / 12.0)) {
			bar.setOffset(3);
		} else if ((health > 9 * startHealth / 12.0)
				&& (health <= 10 * startHealth / 12.0)) {
			bar.setOffset(4);
		} else if ((health > 8 * startHealth / 12.0)
				&& (health <= 9 * startHealth / 12.0)) {
			bar.setOffset(5);
		} else if ((health > 7 * startHealth / 12.0)
				&& (health <= 8 * startHealth / 12.0)) {
			bar.setOffset(6);
		} else if ((health > 6 * startHealth / 12.0)
				&& (health <= 7 * startHealth / 12.0)) {
			bar.setOffset(7);
		} else if ((health > 5 * startHealth / 12.0)
				&& (health <= 6 * startHealth / 12.0)) {
			bar.setOffset(8);
		} else if ((health > 4 * startHealth / 12.0)
				&& (health <= 5 * startHealth / 12.0)) {
			bar.setOffset(9);
		} else if ((health > 3 * startHealth / 12.0)
				&& (health <= 4 * startHealth / 12.0)) {
			bar.setOffset(10);
		} else if ((health > 2 * startHealth / 12.0)
				&& (health <= 3 * startHealth / 12.0)) {
			bar.setOffset(11);
		} else if ((health > 100 / 12.0) && (health <= 200 / 12.0)) {
			bar.setOffset(12);
		} else {
			bar.setOffset(13);
		}

		if (health <= 0) {
			hasDied = true;
			die();
		}

	}

	/** Triggers the death animation and closure */
	public void die() {

		level.remEntity(bar);
		level.remEntity(this);
		this.isTargeted = false;
	}

	public void checkTile(double x, double y) {
		if (level.getTile((int) x / 8, (int) y / 8) == Tile.FIRE) {
			onFire = true;
			healthTickCount = 0;
		}
	}

	public void damage(int a, int b) {
		int damage = random.nextInt(b - a + 1) + a;
		damage -= defense;
		if (damage > 0)
			this.health -= damage;
	}

	public void damage(int a) {
		int damage = a;
		damage -= defense;
		if (damage > 0)
			this.health -= damage;
	}
}
