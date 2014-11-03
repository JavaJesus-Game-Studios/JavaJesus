package ca.javajesus.game.entities;

import java.awt.Rectangle;
//import java.util.Random;

import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;
import ca.javajesus.level.tile.Tile;

public abstract class Mob extends Entity {

	protected String name;
	protected double speed;
	protected int numSteps = 0;
	protected boolean isMoving;
	protected int movingDir = 1;
	protected int scale = 1;
	public double velocity;
	public int width;
	public int height;
	protected double health;
	protected Rectangle hitBox;
	protected SpriteSheet sheet;
	protected HealthBar bar;
	public boolean hasDied;
	protected boolean onFire = false;
	protected int healthTickCount = 0;

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
		this.hitBox = new Rectangle(width, height);
		this.sheet = sheet;
		if (this instanceof Demon) {
			bar = new HealthBar(level, 0 + 2 * 32, this.x, this.y, this, -10);
		} else {
			bar = new HealthBar(level, 0 + 2 * 32, this.x, this.y, this);
		}
		if (level != null) {
			level.addEntity(bar);
		}
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

	protected boolean isMobCollision() {
		if (level == null) {
			return false;
		}
		for (int i = 0; i < level.getEntities().size(); i++) {
			Mob entity;
			if (level.getEntities().get(i) instanceof Mob) {
				entity = (Mob) level.getEntities().get(i);
				if (entity == this) {
					continue;
				}
			} else {
				continue;
			}
			if (this.hitBox.intersects(entity.hitBox)) {
				return true;
			}

		}
		return false;
	}

	protected void moveRandomly() {
		int xa = 0;
		int ya = 0;
		switch (movingDir) {
		case 0:
			ya++;
			break;
		case 1:
			ya--;
			break;
		case 2:
			xa--;
			break;
		case 3:
			xa++;
			break;
		default:
			break;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya, this.speed);
			isMoving = true;
		} else {
			isMoving = false;
		}
	}

	public String getName() {
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
		
		if ((health > 1100 / 7.0) && (health <= 100)) {
			bar.setOffset(2);
		} else if ((health > 1000 / 12.0) && (health <= 1100 / 12.0)) {
			bar.setOffset(3);
		} else if ((health > 900 / 12.0) && (health <= 1000 / 12.0)) {
			bar.setOffset(4);
		} else if ((health > 800 / 12.0) && (health <= 900 / 12.0)) {
			bar.setOffset(5);
		} else if ((health > 700 / 12.0) && (health <= 800 / 12.0)) {
			bar.setOffset(6);
		} else if ((health > 600 / 12.0) && (health <= 700 / 12.0)) {
			bar.setOffset(7);
		} else if ((health > 500 / 12.0) && (health <= 600 / 12.0)) {
	        bar.setOffset(8);
	    } else if ((health > 400 / 12.0) && (health <= 500 / 12.0)) {
	        bar.setOffset(9);
	    } else if ((health > 300 / 12.0) && (health <= 400 / 12.0)) {
	        bar.setOffset(10);
	    } else if ((health > 200 / 12.0) && (health <= 300 / 12.0)) {
	        bar.setOffset(11);
	    } else if ((health > 100 / 12.0) && (health <= 200 / 12.0)) {
	        bar.setOffset(12);
	    } else if ((health > 100 / 12.0) && (health <= 200 / 12.0)) {
	        bar.setOffset(13);
		} else {
			bar.setOffset(14);
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

	}

	public void checkTile(double x, double y) {
		if (level.getTile((int) x / 8, (int) y /8) == Tile.FIRE) {
			onFire = true;
			healthTickCount = 0;
		}
	}
}
