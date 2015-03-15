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
	protected int speed;
	protected int numSteps = 0;
	private boolean isMoving;
	private int movingDir = 1;
	protected int scale = 1;
	protected int width;
	protected int height;
	protected int health;
	protected int startHealth;
	protected HealthBar bar;
	private Rectangle hitBox;
	protected SpriteSheet sheet;
	protected boolean isDead;
	private boolean onFire = false;
	private boolean isTargeted = false;
	protected Random random = new Random();
	private Rectangle standBox;
	protected boolean isAvoidingCollision = false;
	protected boolean renderOnTop = false;

	protected boolean isSwimming = false;
	protected boolean isSwinging = false;
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

	public Mob(Level level, String name, int x, int y, int speed, int width,
			int height, SpriteSheet sheet, int defaultHealth) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.health = defaultHealth;
		this.startHealth = defaultHealth;
		this.setHitBox(new Rectangle(width, height));
		this.setOuterBounds(new Rectangle(width + 4, height + 4));
		this.sheet = sheet;
	}

	public Mob(Level level, String name, int x, int y, int speed, int width,
			int height, SpriteSheet sheet, int defaultHealth, int strength,
			int defense, int accuracy, int evasion) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.health = defaultHealth;
		this.startHealth = defaultHealth;
		this.setHitBox(new Rectangle(width, height));
		this.setOuterBounds(new Rectangle(width + 4, height + 4));
		this.sheet = sheet;
		this.strength = strength;
		this.defense = defense;
		this.accuracy = accuracy;
		this.evasion = evasion;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		this.name = s;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public HealthBar getHealthBar() {
		return bar;
	}
	
	public boolean renderOnTop() {
		return renderOnTop;
	}
	
	public int getScale() {
		return scale;
	}
	
	public int getNumSteps() {
		return numSteps;
	}
	
	public void changeSteps(int num) {
		numSteps += num;
	}
	
	public Rectangle getBounds() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

	public int getStartHealth() {
		return startHealth;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void changeHealth(int health) {
		this.health += health;
		if (health > startHealth) {
			this.health = startHealth;
		}
		if (health < 0) {
			kill();
		}
	}

	public void heal() {
		this.health = startHealth;
	}

	public void move(int xa, int ya, boolean jesus) {
		if (xa != 0 && ya != 0) {
			move(xa, 0, true);
			move(0, ya, true);
			numSteps--;
			return;
		}
		numSteps++;
		int xValue = 0;
		int yValue = 0;
		if (speed % 1 == 0) {
			xValue = (int) (xa * speed);
			yValue = (int) (ya * speed);
		} else {
			if (xa * speed < 0) {
				xValue = (int) (xa * speed - 1);
			}
			if (ya * speed < 0) {
				yValue = (int) (ya * speed - 1);
			}
			if (xa * speed > 0) {
				xValue = (int) (xa * speed + 1);
			}
			if (ya * speed > 0) {
				yValue = (int) (ya * speed + 1);
			}
		}
		if (jesus) {

			if (ya < 0)
				setDirection(0);
			if (ya > 0)
				setDirection(1);
			if (xa < 0)
				setDirection(2);
			if (xa > 0)
				setDirection(3);

			x += xa * speed;
			y += ya * speed;
		}
	}

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			numSteps--;
			return;
		}
		numSteps++;
		int xValue = 0;
		int yValue = 0;
		if (speed % 1 == 0) {
			xValue = (int) (xa * speed);
			yValue = (int) (ya * speed);
		} else {
			if (xa * speed < 0) {
				xValue = (int) (xa * speed - 1);
			}
			if (ya * speed < 0) {
				yValue = (int) (ya * speed - 1);
			}
			if (xa * speed > 0) {
				xValue = (int) (xa * speed + 1);
			}
			if (ya * speed > 0) {
				yValue = (int) (ya * speed + 1);
			}
		}
		if (!hasCollided(xValue, yValue)) {

			if (ya < 0)
				setDirection(0);
			if (ya > 0)
				setDirection(1);
			if (xa < 0)
				setDirection(2);
			if (xa > 0)
				setDirection(3);

			x += xa * speed;
			y += ya * speed;
		}
	}

	public abstract boolean hasCollided(int xa, int ya);

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
				if (this.getBounds().intersects(((SolidEntity) entity).shadow)) {
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
				if (this.getBounds().intersects(temp))
					return true;
			} else if (entity instanceof Vehicle && entity != this) {
				Rectangle temp;
				Vehicle vehicle = (Vehicle) entity;
				if (vehicle.getDirection() < 2) {
					temp = new Rectangle(vehicle.getBounds().width - 16,
							vehicle.getBounds().height - 8);
					temp.setLocation((int) vehicle.x - xa, (int) vehicle.y - ya
							- 8);
				} else {
					temp = new Rectangle(vehicle.getBounds().width - 8,
							vehicle.getBounds().height - 16);
					temp.setLocation((int) vehicle.x - xa - 3, (int) vehicle.y
							- ya - 4);
				}

				if (this.getBounds().intersects(temp))
					return true;
			} else if (entity instanceof FireEntity
					&& this.getBounds()
							.intersects(((FireEntity) entity).getBounds())) {
				setOnFire(true);
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
			if (this.getBounds().intersects(mob.getBounds()) && !mob.isAvoidingCollision)
				return true;
		}
		isAvoidingCollision = false;
		return false;
	}

	protected boolean isMobCollision(int xa, int ya) {
		for (Mob mob : level.getMobs()) {
			if (mob != this) {
				Rectangle temp = new Rectangle(mob.getBounds().width,
						mob.getBounds().height);
				temp.setLocation((int) mob.x - 2 * xa, (int) mob.y - 2 * ya);
				if (this.getBounds().intersects(temp))
					return true;
			}

		}
		return false;
	}

	protected void moveAroundMobCollision() {

		int xa = 0;
		int ya = 0;

		for (Mob mob : level.getMobs()) {
			if (mob == this || !(this.getBounds().intersects(mob.getBounds())))
				continue;

			Rectangle intersection = getBounds().intersection(mob.getBounds());
			double xx = intersection.getCenterX();
			double yy = intersection.getCenterY();
			if ((int) xx >= (int) this.getBounds().getCenterX()) {
				xa--;
			}
			if ((int) xx < (int) this.getBounds().getCenterX()) {
				xa++;
			}
			if ((int) yy >= (int) this.getBounds().getCenterY()) {
				ya--;
			}
			if ((int) yy < (int) this.getBounds().getCenterY()) {
				ya++;
			}

			if (xa != 0 || ya != 0) {
				break;
			}
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			isAvoidingCollision = true;
			setMoving(true);
		} else {
			setMoving(false);
		}
	}

	public String toString() {
		return name;
	}

	/** Triggers the death animation and closure */
	public void kill() {

		isDead = true;
		if (!(this instanceof Vehicle)) {
			this.getBounds().setSize(0, 0);
			this.getBounds().setLocation(0, 0);
			this.getOuterBounds().setSize(0, 0);
			this.getOuterBounds().setLocation(0, 0);
		}
		level.remEntity(this);
		level.addEntity(this, 0);
		isHit = false;
		isTalking = false;
		this.setTargeted(false);
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

	public void speak(Player player) {
	}

	public int getDirection() {
		return movingDir;
	}

	public void setDirection(int movingDir) {
		this.movingDir = movingDir;
	}

	public Rectangle getOuterBounds() {
		return standBox;
	}

	public void setOuterBounds(Rectangle standBox) {
		this.standBox = standBox;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public boolean isTargeted() {
		return isTargeted;
	}

	public void setTargeted(boolean isTargeted) {
		this.isTargeted = isTargeted;
	}

	public boolean isOnFire() {
		return onFire;
	}

	public void setOnFire(boolean onFire) {
		this.onFire = onFire;
	}
}
