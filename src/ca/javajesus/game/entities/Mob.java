package ca.javajesus.game.entities;

import java.awt.Rectangle;
import java.util.Random;

import ca.javajesus.game.JavaRectangle;
import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.entities.vehicles.Vehicle;
import ca.javajesus.game.graphics.JJFont;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;
import ca.javajesus.level.tile.Tile;
import ca.javajesus.quests.Script;

public class Mob extends Entity {

	private static final long serialVersionUID = -1507733585991126012L;
	
	protected String name;
	protected int[] color;
	protected int speed;
	protected int numSteps = 0;
	private boolean isMoving;
	private Direction movingDir = Direction.SOUTH;
	protected int scale = 1;
	protected int width;
	protected int height;
	protected int health;
	protected int startHealth;
	protected HealthBar bar;
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
	protected int[] isHitColor = { 0xFF000000, 0xFF000000, 0xFFFF0000 };

	protected int tickCount = 0;
	public Script script;

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
		this.setHitBox(new JavaRectangle(width, height, this));
		this.setOuterBounds(new Rectangle(width + 4, height + 4));
		this.getBounds().setLocation(this.x - (this.width / 2),
				this.y - (this.height / 2));
		this.getOuterBounds().setLocation(
				this.x - (int) getBounds().getWidth() / 2 - 2,
				this.y - (int) getBounds().getHeight() / 2 - 2);
		this.sheet = sheet;
		// Game.quad.retrieve(returnObjects, this.bounds);
	}

	public enum Direction {
		NORTH, SOUTH, EAST, WEST
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
			setDirection(Direction.NORTH);
		if (ya > 0)
			setDirection(Direction.SOUTH);
		if (xa < 0)
			setDirection(Direction.WEST);
		if (xa > 0)
			setDirection(Direction.EAST);

		x += xa * speed;
		y += ya * speed;
	}

	public void move(int xa, int ya) {
		numSteps++;
		if (tickCount % 2 == 0) {
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
					setDirection(Direction.WEST);
				if (xa > 0)
					setDirection(Direction.EAST);

				x += xa * speed;
			}
			if (!hasCollided(0, yValue)) {
				if (ya < 0)
					setDirection(Direction.NORTH);
				if (ya > 0)
					setDirection(Direction.SOUTH);
				y += ya * speed;
			}
		}
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = -4;
		int xMax = 3;
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
		Tile lastTile = level.getTile((this.x + x) >> 3, (this.y + y) >> 3);
		Tile newTile = level.getTile((this.x + x + xa) >> 3,
				(this.y + y + ya) >> 3);
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

	public boolean isSolidEntityCollision(int xa, int ya) {
		renderOnTop = true;
		// for (JavaRectangle r : returnObjects) {
		for (Entity entity : level.getEntities()) {
			if (entity instanceof SolidEntity
					&& !(entity instanceof Transporter)) {
				if (this.getBounds().intersects(((SolidEntity) entity).shadow)) {
					renderOnTop = false;
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
				if (isLongitudinal(vehicle.getDirection())) {
					temp = new Rectangle(vehicle.getBounds().width - 16,
							vehicle.getBounds().height - 8);
					temp.setLocation(vehicle.x - xa - 8, vehicle.y - ya - 20);
				} else {
					temp = new Rectangle(vehicle.getBounds().width - 8,
							vehicle.getBounds().height - 16);
					temp.setLocation(vehicle.x - xa - 12, vehicle.y - ya - 10);
				}

				if (this.getBounds().intersects(temp))
					return true;
			} else if (entity instanceof FireEntity
					&& this.getBounds().intersects((entity.getBounds()))) {
				setOnFire(true);
			}

		}
		return false;
	}

	protected boolean isMobCollision() {
		if (level == null || isDead) {
			return false;
		}
		for (Mob mob : level.getMobs()) {
			if (mob == this)
				continue;
			if (this.getBounds().intersects(mob.getBounds())
					&& !mob.isAvoidingCollision && !mob.isDead)
				return true;
		}
		isAvoidingCollision = false;
		return false;
	}

	public boolean isMobCollision(int xa, int ya) {
		for (Mob mob : level.getMobs()) {
			if (mob != this && !mob.isDead) {
				Rectangle temp = new Rectangle(mob.getBounds().width + 2 * xa,
						mob.getBounds().height + 2 * ya);
				temp.setLocation(mob.x - xa, mob.y - ya);
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
			if (!(this.getBounds().intersects(mob.getBounds())) || mob == this || mob.isDead)
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
		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)) {
			isAvoidingCollision = true;
			setMoving(true);
			move(xa, ya);
		} else {
			setMoving(false);
		}
	}

	public void tick() {

		if (isDead)
			return;

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

		if (level.getTile(x >> 3, (y + 3) >> 3).getId() == 3) {
			isSwimming = true;
		} else {
			isSwimming = false;
		}

		if (isMobCollision()) {
			moveAroundMobCollision();
			return;
		}

		bar.tick();

	}

	public void render(Screen screen) {
		this.getBounds().setLocation(this.x - (this.width / 2),
				this.y - (this.height / 2));
		this.getOuterBounds().setLocation(
				this.x - (int) getBounds().getWidth() / 2 - 2,
				this.y - (int) getBounds().getHeight() / 2 - 2);

		int modifier = 8 * scale;
		int xOffset = x - modifier;
		int yOffset = y - modifier;

		if (isSwimming) {
			if (isOnFire()) {
				setOnFire(false);
			}
			int[] waterColor = { 0xFF5A52FF, 0xFF000000, 0xFF000000 };
			yOffset += 4;
			if (tickCount % 60 < 15) {
				waterColor[0] = 0xFF5266FF;
				waterColor[1] = 0xFF000000;
				waterColor[2] = 0xFF000000;
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;
				waterColor[0] = 0xFF3D54FF;
				waterColor[1] = 0xFF5266FF;
				waterColor[2] = 0xFF000000;
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				waterColor[0] = 0xFF3D54FF;
				waterColor[1] = 0xFF000000;
				waterColor[2] = 0xFF000000;
			} else {
				yOffset -= 1;
				waterColor[0] = 0xFF5266FF;
				waterColor[1] = 0xFF5266FF;
				waterColor[2] = 0xFF000000;
			}
			screen.render(xOffset, yOffset + 3, 0 + 10 * sheet.boxes,
					waterColor, 0x00, 1, sheet);
			screen.render(xOffset + 8, yOffset + 3, 0 + 10 * sheet.boxes,
					waterColor, 0x01, 1, sheet);
		}

		// Handles fire animation
		if (isOnFire()) {
			int[] firecolor = { 0xFFF7790A, 0xFFF72808, 0xFF000000 };

			screen.render(xOffset + 3, yOffset, this.level.fireList.get(0)
					.getXTile() + 15 * sheet.boxes, firecolor, 0, 2,
					SpriteSheet.tiles);

		}

		if (!isDead) {

			if (isTalking) {
				JJFont.render(name, screen, (int) xOffset
						- ((name.length() - 1) / 2 * 8), (int) yOffset - 10,
						new int[] { 0xFF000000, 0xFF000000, 0xFFFFCC00 }, 1);
			}

			if (isHit) {
				JJFont.render(damageTaken, screen, (int) xOffset + isHitX,
						(int) yOffset - 10 + isHitY, isHitColor, 1);
			}
		}
	}

	public String toString() {
		return name;
	}

	/** Triggers the death animation and closure */
	public void kill() {

		isDead = true;
		//this.getBounds().setSize(0, 0);
		//this.getBounds().setLocation(0, 0);
		//this.getOuterBounds().setSize(0, 0);
		//this.getOuterBounds().setLocation(0, 0);
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
		if (health <= 0) {
			kill();
		}
	}

	public void damage(int d) {
		damage(d, d);
	}

	public void speak(Player player) {
	}

	public Direction getDirection() {
		return movingDir;
	}

	public boolean isLatitudinal(Direction dir) {
		if (dir == Direction.EAST || dir == Direction.WEST) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isLongitudinal(Direction dir) {
		if (dir == Direction.NORTH || dir == Direction.SOUTH) {
			return true;
		} else {
			return false;
		}
	}

	public void setDirection(Direction dir) {
		this.movingDir = dir;
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

	public int getStartHealth() {
		return startHealth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int h) {
		health = h;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
