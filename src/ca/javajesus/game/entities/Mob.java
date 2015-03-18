package ca.javajesus.game.entities;

import java.awt.Rectangle;
import java.util.Random;

import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.entities.structures.Transporter;
import ca.javajesus.game.entities.vehicles.Vehicle;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.JJFont;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;
import ca.javajesus.level.tile.Tile;

public class Mob extends Entity {

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

	protected int tickCount = 0;

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

	public void move(int xa, int ya) {
		if (tickCount % 2 == 0) {
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
			if (!hasCollided(xValue, 0)) {
				if (xa < 0)
					setDirection(2);
				if (xa > 0)
					setDirection(3);
	
				x += xa * speed;
			}
			if (!hasCollided(0, yValue)) {
				if (ya < 0)
					setDirection(0);
				if (ya > 0)
					setDirection(1);
				y += ya * speed;
			}
		}
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
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
				temp.setLocation(((SolidEntity) entity).bounds.x - 3 * xa,
						((SolidEntity) entity).bounds.y - 3 * ya);
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
					&& this.getBounds().intersects(
							((FireEntity) entity).getBounds())) {
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
			if (this.getBounds().intersects(mob.getBounds())
					&& !mob.isAvoidingCollision)
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
				temp.setLocation(mob.x - 2 * xa, mob.y - 2 * ya);
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
			if (!(this.getBounds().intersects(mob.getBounds())) || mob == this)
				continue;

			Rectangle intersection = getBounds().intersection(mob.getBounds());
			double xx = intersection.getCenterX();
			double yy = intersection.getCenterY();
			if (xx >= this.getBounds().getCenterX()) {
				xa--;
			}
			if (xx < this.getBounds().getCenterX()) {
				xa++;
			}
			if (yy >= this.getBounds().getCenterY()) {
				ya--;
			}
			if (yy < this.getBounds().getCenterY()) {
				ya++;
			}

			if (xa != 0 || ya != 0) {
				break;
			}
		}
		if (xa != 0 || ya != 0) {
			isAvoidingCollision = true;
			setMoving(true);
			move(xa, ya);
		} else {
			setMoving(false);
		}
	}

	public void tick() {

		tickCount++;

		if (isTalking) {
			talkCount++;
			if (talkCount > 350) {
				talkCount = 0;
				isTalking = false;
			}
		}

		if (isHit) {
			isHitTicks++;
			if (isHitTicks > 20) {
				isHitTicks = 0;
				isHit = false;
			}
		}

		if (level.getTile(x >> 3, y >> 3).getId() == 3) {
			isSwimming = true;
		} else {
			isSwimming = false;
		}

		if (isMobCollision()) {
			moveAroundMobCollision();
			return;
		}

	}

	public void render(Screen screen) {
		this.getBounds().setLocation(this.x - (this.width / 2),
				this.y - (this.height / 2));
		this.getOuterBounds().setLocation(
				this.x - (int) getBounds().getWidth() / 2 - 2,
				this.y - (int) getBounds().getHeight() / 2 - 2);

		int modifier = 8 * scale;
		double xOffset = x - modifier / 2.0;
		double yOffset = y - modifier / 2.0 - 4;

		if (isSwimming) {
			if (isOnFire()) {
				setOnFire(false);
			}
			int waterColour = 0;
			yOffset += 4;
			if (tickCount % 60 < 15) {
				waterColour = Colors.get(-1, 225, -1, -1);
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;
				waterColour = Colors.get(-1, 115, 225, -1);
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				waterColour = Colors.get(-1, 115, -1, -1);
			} else {
				yOffset -= 1;
				waterColour = Colors.get(-1, 225, 225, -1);
			}
			screen.render(xOffset, yOffset + 3, 0 + 10 * 32, waterColour, 0x00,
					1, sheet);
			screen.render(xOffset + 8, yOffset + 3, 0 + 10 * 32, waterColour,
					0x01, 1, sheet);
		}

		// Handles fire animation
		if (isOnFire()) {
			int firecolor = Colors.get(-1, Colors.fromHex("#F7790A"), 540, -1);

			screen.render(xOffset + 3, yOffset, this.level.fireList.get(0)
					.getXTile() + 15 * 32, firecolor, 0, 2, SpriteSheet.tiles);

		}

		if (isTalking) {
			JJFont.render(name, screen, (int) xOffset
					- ((name.length() - 1) / 2 * 8), (int) yOffset - 10,
					Colors.get(-1, -1, -1, Colors.fromHex("#FFCC00")), 1);
		}

		if (isHit) {
			JJFont.render(damageTaken, screen, (int) xOffset + isHitX,
					(int) yOffset - 10 + isHitY, isHitColor, 1);
		}
	}

	public String toString() {
		return name;
	}

	/** Triggers the death animation and closure */
	public void kill() {

		isDead = true;
		this.getBounds().setSize(0, 0);
		this.getBounds().setLocation(0, 0);
		this.getOuterBounds().setSize(0, 0);
		this.getOuterBounds().setLocation(0, 0);
		level.remEntity(this);
		level.addEntity(this, 0);
		level.killList.add(this);
		isHit = false;
		isTalking = false;
		this.setTargeted(false);
	}

	public void checkTile(double x, double y) {

	}

	public void damage(int a, int b) {
		int damage = random.nextInt(b - a + 1) + a;
		damage -= defense;
		if (damage < 0) {
			damage = 0;
		}
		if (damage > 0)
			this.health -= damage;
		damageTaken = String.valueOf(damage);
		isHit = true;
		isHitX = random.nextInt(10) - 5;
		isHitY = random.nextInt(6) - 3;
		isHitColor = Colors.get(-1, -1, -1, random.nextInt(200));
	}

	public void damage(int d) {
		damage(d, d);
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

	public void setHealth(int h) {
	    health = h;
	}
}
