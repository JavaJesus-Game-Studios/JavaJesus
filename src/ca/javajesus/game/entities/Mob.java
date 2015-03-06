package ca.javajesus.game.entities;

import java.awt.Rectangle;
import java.util.Random;

import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.entities.structures.Transporter;
import ca.javajesus.game.entities.vehicles.Vehicle;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;
import ca.javajesus.level.tile.Tile;

public abstract class Mob extends Entity {

	protected String name;
	protected int color;
	protected double speed;
	protected int numSteps = 0;
	public boolean isMoving;
	public int movingDir = 1;
	protected int scale = 1;
	public int width;
	public int height;
	public double health;
	public double startHealth;
	public HealthBar bar;
	public Rectangle hitBox;
	protected SpriteSheet sheet;
	public boolean isDead;
	public boolean onFire = false;
	public boolean isTargeted = false;
	protected Random random = new Random();
	public Rectangle standBox;
	protected boolean isAvoidingCollision = false;
	public boolean renderOnTop = false;

	protected boolean isSwimming = false;
	public boolean isSwinging = false;
	protected boolean isShooting = false;

	protected boolean isTalking = false;
	protected int talkCount = 0;

	protected String damageTaken = "";
	protected boolean isHit = false;
	protected int isHitTicks = 0;
	protected int isHitX = 0;
	protected int isHitY = 0;
	protected int isHitColor = 0;

	public int strength, defense, accuracy, evasion;

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
	}

	public Mob(Level level, String name, double x, double y, int speed,
			int width, int height, SpriteSheet sheet, double defaultHealth,
			int strength, int defense, int accuracy, int evasion) {
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
		this.strength = strength;
		this.defense = defense;
		this.accuracy = accuracy;
		this.evasion = evasion;
	}

	public void addHealth(double health) {
		this.health += health;
		if (health > startHealth) {
			this.health = startHealth;
		}
	}

	public void heal() {
		this.health = startHealth;
	}

	public double getStartHealth() {
		return startHealth;
	}

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			numSteps--;
			return;
		}
		numSteps++;
		if (!hasCollided((int) (xa * speed), (int) (ya * speed))) {
			if (ya < 0)
				movingDir = 0;
			if (ya > 0)
				movingDir = 1;
			if (xa < 0)
				movingDir = 2;
			if (xa > 0)
				movingDir = 3;

			x += xa * speed;
			y += ya * speed;
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
		renderOnTop = true;
		if (bar != null)
			bar.renderOnTop = true;
		for (Entity entity : level.getEntities()) {
			if (entity instanceof SolidEntity
					&& !(entity instanceof Transporter)) {
				if (this.hitBox.intersects(((SolidEntity) entity).shadow)) {
					renderOnTop = false;
					if (bar != null)
						bar.renderOnTop = false;
				}
				Rectangle temp = new Rectangle(
						((SolidEntity) entity).bounds.width,
						((SolidEntity) entity).bounds.height - 8);
				temp.setLocation(
						(int) ((SolidEntity) entity).bounds.x - 3 * xa,
						(int) ((SolidEntity) entity).bounds.y - 3 * ya);
				if (this.hitBox.intersects(temp))
					return true;
			} else if (entity instanceof Vehicle && entity != this) {
				Rectangle temp;
				Vehicle vehicle = (Vehicle) entity;
				if (vehicle.movingDir < 2) {
					temp = new Rectangle(vehicle.hitBox.width - 16,
							vehicle.hitBox.height - 8);
					temp.setLocation((int) vehicle.x - xa, (int) vehicle.y - ya
							- 8);
				} else {
					temp = new Rectangle(vehicle.hitBox.width - 8,
							vehicle.hitBox.height - 16);
					temp.setLocation((int) vehicle.x - xa - 3, (int) vehicle.y - ya - 4);
				}

				if (this.hitBox.intersects(temp))
					return true;
			} else if (entity instanceof FireEntity
					&& this.hitBox.intersects(((FireEntity) entity).hitBox)) {
				onFire = true;
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
			if (this.hitBox.intersects(mob.hitBox) && !mob.isAvoidingCollision)
				return true;
		}
		isAvoidingCollision = false;
		return false;
	}

	protected boolean isMobCollision(int xa, int ya) {
		for (Mob mob : level.getMobs()) {
			if (mob != this) {
				Rectangle temp = new Rectangle(mob.hitBox.width,
						mob.hitBox.height);
				temp.setLocation((int) mob.x - 2 * xa, (int) mob.y - 2 * ya);
				if (this.hitBox.intersects(temp))
					return true;
			}

		}
		return false;
	}

	protected void moveAroundMobCollision() {

		int xa = 0;
		int ya = 0;

		for (Mob mob : level.getMobs()) {
			if (mob == this || !(this.hitBox.intersects(mob.hitBox)))
				continue;

			Rectangle intersection = hitBox.intersection(mob.hitBox);
			double xx = intersection.getCenterX();
			double yy = intersection.getCenterY();
			if ((int) xx >= (int) this.hitBox.getCenterX()) {
				xa--;
			}
			if ((int) xx < (int) this.hitBox.getCenterX()) {
				xa++;
			}
			if ((int) yy >= (int) this.hitBox.getCenterY()) {
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
			move(xa, ya);
			isAvoidingCollision = true;
			isMoving = true;
		} else {
			isMoving = false;
		}
	}

	public String toString() {
		return name;
	}

	/** Triggers the death animation and closure */
	public void kill() {

		isDead = true;
		hitBox.setBounds(0, 0, 0, 0);
		level.remEntity(this);
		level.getMobs().add(0, this);
		isHit = false;
		isTalking = false;
		this.isTargeted = false;
	}

	public void checkTile(double x, double y) {

	}

	public void damage(int a, int b) {
		int damage = random.nextInt(b - a + 1) + a;
		damage -= defense;
		if (damage > 0)
			this.health -= damage;
		damageTaken = String.valueOf(damage);
		isHit = true;
		isHitX = random.nextInt(10) - 5;
		isHitY = random.nextInt(6) - 3;
		isHitColor = Colors.get(-1, -1, -1, random.nextInt(200));
	}

	public void damage(double a, double b) {
		a *= 10;
		b *= 10;
		double damage = random.nextInt((int) b - (int) a + 1) + (int) a;
		damage = damage / 10.0 - defense;
		if (damage > 0)
			this.health -= damage;
		damageTaken = String.valueOf(damage);
		isHit = true;
		isHitX = random.nextInt(10) - 5;
		isHitY = random.nextInt(6) - 3;
		isHitColor = Colors.get(-1, -1, -1, random.nextInt(200));
	}

	public void damage(double d) {
		double damage = d;
		damage -= defense;
		if (damage > 0)
			this.health -= damage;
		damageTaken = String.valueOf(damage);
		isHit = true;
		isHitX = random.nextInt(10) - 5;
		isHitY = random.nextInt(6) - 3;
		isHitColor = Colors.get(-1, -1, -1, random.nextInt(200));
	}

	public void setName(String s) {
		this.name = s;
	}

	public void speak(Player player) {
	}
}
