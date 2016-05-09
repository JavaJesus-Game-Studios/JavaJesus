package game.entities;

import java.awt.Rectangle;
import java.util.Random;

import game.Hideable;
import game.entities.particles.HealthBar;
import game.entities.vehicles.Vehicle;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import level.Level;
import level.tile.Tile;
import quests.Script;
import utility.Direction;

/*
 * A mob is an entity that has complex interactions in a level with other entities
 */
public class Mob extends Entity implements Damageable, Hideable {

	private static final long serialVersionUID = -1507733585991126012L;

	// the name of the mob
	private String name;

	// the base speed of the mob
	private int speed;

	// flip value
	protected int numSteps;

	// the direction the mob is moving/facing
	private Direction movingDir = Direction.SOUTH;

	// the scaled size of the mob
	private int scale = 1;

	// the current amount of health
	private int health;

	// the max health (upper bound)
	private int maxHealth;

	// the health bar that is displayed beneath each mob
	private HealthBar bar;

	// the spritesheet each mob uses for its images
	private SpriteSheet sheet;

	// determines if the mob is currently on fire
	private boolean onFire;

	// determines if another mob is attacking this mob
	private boolean isTargeted;

	// randomly generates colors and damage
	protected static final Random random = new Random();

	// the padding around each mob where other mobs can interact
	private Rectangle outerBounds;

	// renders the mob behind the building
	protected boolean isBehindBuilding;

	// determines if the mob should have the swimming animation
	protected boolean isSwimming;

	// determines if the mob should have the swinging animation
	protected boolean isSwinging;

	// determines if the mob should have the shooting animation
	protected boolean isShooting;

	// determines if the mob should have the talking animation
	protected boolean isTalking;

	// determines if the mob is colliding with another mob
	private boolean isCollidingWithMob;

	// the hit cooldown in ticks
	private static final int HIT_COOLDOWN = 20;

	// the talking cooldown in ticks
	private static final int TALKING_COOLDOWN = Entity.secondsToTicks(5);

	// the base unit of each box on the spritesheet
	protected static final int UNIT_SIZE = 8;

	// talking cooldown
	protected int talkCount;

	// the damage value to be displayed
	protected String damageTaken = "";

	// determines if the mob was recently damaged (for displaying damage
	// indicators)
	private boolean isHit;

	// damage cooldown
	private int isHitTicks;
	protected int isHitX = 0;
	protected int isHitY = 0;
	protected int[] isHitColor = { 0xFF000000, 0xFF000000, 0xFFFF0000 };

	protected int tickCount = 0;

	// an action script a mob might follow
	private Script script;

	// the extra space, or padding, around each mob
	public static final int OUTER_BOUNDS_RANGE = 4;

	/**
	 * Creates a mob that can interact with other entities on a level
	 * 
	 * @param level
	 *            the level to place it on
	 * @param name
	 *            the name of the mob
	 * @param x
	 *            the x coordinate on the map
	 * @param y
	 *            the y coordiante on the map
	 * @param speed
	 *            the base speed of the mob (usually 1)
	 * @param width
	 *            the horizontal space of the mob
	 * @param height
	 *            the vertical space of the mob
	 * @param sheet
	 *            the spritesheet that contains the image of the mob
	 * @param defaultHealth
	 *            the starting highest health value
	 */
	public Mob(Level level, String name, int x, int y, int speed, int width, int height, SpriteSheet sheet,
			int defaultHealth) {
		super(level, x, y);

		this.name = name;
		this.speed = speed;
		this.health = defaultHealth;
		this.maxHealth = defaultHealth;
		this.setBounds(x, y, width, height);
		this.setOuterBounds(width, height);
		this.sheet = sheet;

	}

	/**
	 * Increases or decreases the mob's health
	 * 
	 * @param dh
	 *            the change in health
	 */
	public void changeHealth(int dh) {
		this.health += dh;
		if (health > maxHealth) {
			this.health = maxHealth;
		}
		if (health < 0) {
			remove();
		}
	}

	/**
	 * Replenishes the health to full
	 */
	public void heal() {
		this.health = maxHealth;
	}

	/**
	 * Moves a mob on the map
	 * 
	 * @param dx
	 *            the change in x
	 * @param dy
	 *            the change in y
	 * @param canClip
	 *            whether or not it can move through solids
	 */
	protected void move(int dx, int dy, boolean canClip) {

		numSteps++;

		if (dx < 0)
			setDirection(Direction.WEST);
		else if (dx > 0)
			setDirection(Direction.EAST);
		if (dy < 0)
			setDirection(Direction.NORTH);
		else if (dy > 0)
			setDirection(Direction.SOUTH);

		super.move(dx, dy);
		this.moveOuterBounds(dx, dy);
	}

	/**
	 * Moves a mob on the level
	 * 
	 * @param dx
	 *            the total change in x
	 * @param dy
	 *            the total change in y
	 */
	public void move(int dx, int dy) {

		numSteps++;

		int sign = 0;
		if (dx < 0) {
			setDirection(Direction.WEST);
			sign = -1;
		} else if (dx > 0) {
			setDirection(Direction.EAST);
			sign = 1;
		}

		// move pixel by pixel for maximum accuracy
		for (int i = 0; i < Math.abs(dx); i++) {
			if (!hasCollided(1 * sign, 0)) {
				super.move(1 * sign, 0);
				if (isSolidEntityCollision()) {
					super.move(-1 * sign, 0);
				}
				this.moveOuterBounds(1 * sign, 0);
			} else {
				break;
			}
		}

		if (dy < 0) {
			setDirection(Direction.NORTH);
			sign = -1;
		} else if (dy > 0) {
			setDirection(Direction.SOUTH);
			sign = 1;
		}

		// move pixel by pixel for maximum accuracy
		for (int i = 0; i < Math.abs(dy); i++) {
			if (!hasCollided(0, 1 * sign)) {
				super.move(0, 1 * sign);
				if (isSolidEntityCollision()) {
					super.move(0, -1 * sign);
					
					// TODO bugfix for walking south behind buildings
					if (sign == 1)
						isBehindBuilding = true;
				}
				this.moveOuterBounds(0, 1 * sign);
			} else {
				break;
			}
		}

		isCollidingWithMob = getMobCollision() != null;
	}

	/**
	 * Determines if the change in x or y results in a solid tile collision
	 * 
	 * @param dx
	 *            the change in x
	 * @param dy
	 *            the change in y
	 * @return true if the change in coordinates results in a solid tile
	 *         collision
	 */
	protected boolean hasCollided(int dx, int dy) {

		// the left bound of the mob
		//TODO These offsets are for player specifically
		int xMin = 4;

		// the right bound of the mob
		int xMax = getBounds().width - 6;

		// the top bound of the mob
		int yMin = getBounds().width / 2;

		// the bottom bound of the mob
		int yMax = getBounds().width;

		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(dx, dy, x, yMin) || isSolidTile(dx, dy, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(dx, dy, xMin, y) || isSolidTile(dx, dy, xMax, y)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the position is a solid tile
	 * 
	 * @param dx
	 *            the new x
	 * @param dy
	 *            the new y
	 * @param x
	 *            the x offset
	 * @param y
	 *            the y offset
	 * @return true if the new tile is solid
	 */
	protected boolean isSolidTile(int dx, int dy, int x, int y) {

		// tile the mob is on (bottom half)
		Tile lastTile = getLevel().getTile((getX() + x) >> 3, (getY() + y) >> 3);

		// tile the mob will move to
		Tile newTile = getLevel().getTile((getX() + x + dx) >> 3, (getY() + y + dy) >> 3);

		// checking the last tile allows the player to move through solids if
		// already on a solid
		if (!lastTile.equals(newTile) && newTile.isSolid()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the position is a water tile
	 * 
	 * @param dx
	 *            the new x
	 * @param dy
	 *            the new y
	 * @param x
	 *            the x offset
	 * @param y
	 *            the y offset
	 * @return true if the new tile is water
	 */
	protected boolean isWaterTile(int dx, int dy, int x, int y) {

		// tile the mob is on (bottom half)
		Tile lastTile = getLevel().getTile((getX() + x) >> 3, (getY() + y) >> 3);

		// tile the mob will move to
		Tile newTile = getLevel().getTile((getX() + x + dx) >> 3, (getY() + y + dy) >> 3);

		// water entry looks a bit jumpy TODO
		if (!lastTile.equals(newTile) && newTile.equals(Tile.WATER)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the new position collides with a solid entity
	 * 
	 * @param dx
	 *            the change in x
	 * @param dy
	 *            the change in y
	 * @return true if the new position will create a collision
	 */
	private boolean isSolidEntityCollision() {

		isBehindBuilding = false;
		
		try {

		// loop through the buildings/possible entities
		for (Entity entity : getLevel().getEntities()) {

			if (entity instanceof SolidEntity) {

				SolidEntity building = (SolidEntity) entity;
				
				
				
				if (getBounds().intersects(building.getShadow())) {
					isBehindBuilding = true;
				} else if (getBounds().intersects(entity.getBounds())) {
					if (entity instanceof Vehicle) {
						damage(((Vehicle) entity).getDamage());
					}
					return true;
				}

			} else if (entity instanceof FireEntity && getBounds().intersects((entity.getBounds()))) {
				setOnFire(true);
			}

		}
		} catch (Exception e) {
			System.err.println(this.getName() + getBounds());
			e.getMessage();
		}

		return false;
	}

	/**
	 * Determines if a mob's bounds are intersecting another mob's bounds as it
	 * is
	 * 
	 * @return the other mob that is colliding with this mob, null if there
	 *         isn't one
	 */
	protected Mob getMobCollision() {

		// loop through the list of mobs
		for (Mob mob : getLevel().getMobs()) {
			if (mob == this)
				continue;
			// TODO Mobs might get stuck over the bounds of mobs that are dead
			// Fix: Remove dead mobs from the mob list
			if (this.getBounds().intersects(mob.getBounds()))
				return mob;
		}
		return null;
	}

	/**
	 * Checks if a mob will collide with another mob at the new location
	 * 
	 * @param dx
	 *            the change in x
	 * @param dy
	 *            the change in y
	 * @return true if a mob is already occupying that space
	 */
	public boolean isMobCollision(int dx, int dy) {

		// create a temporary range box shifted by dx and dy
		Rectangle range = new Rectangle(getX() + dx, getY() + dy, getBounds().width, getBounds().height);

		for (Mob mob : getLevel().getMobs()) {
			if (range.intersects(mob.getBounds()) && mob != this)
				return true;
		}

		return false;
	}

	/**
	 * Forces a mob to move out of a collision
	 */
	protected void moveAroundMobCollision() {

		Mob other = getMobCollision();

		// if there is no collision, do nothing
		if (other == null)
			return;

		// the direction the mob should go to escape
		int dx = 0;
		int dy = 0;

		// where the two mobs are colliding
		Rectangle intersection = getBounds().intersection(other.getBounds());

		// the center intersection points
		double xx = intersection.getCenterX();
		double yy = intersection.getCenterY();

		// get the optimal direction to escape
		if (xx >= this.getBounds().getCenterX()) {
			dx--;
		}
		if (xx < this.getBounds().getCenterX()) {
			dx++;
		}
		if (yy >= this.getBounds().getCenterY()) {
			dy--;
		}
		if (yy < this.getBounds().getCenterY()) {
			dy++;
		}

		this.move(dx, dy);
	}

	/**
	 * Updates a mob 60 times per second
	 */
	public void tick() {

		tickCount++;

		// talking cooldown loop
		if (isTalking) {
			talkCount++;
			if (talkCount > TALKING_COOLDOWN) {
				talkCount = 0;
				isTalking = false;
			}
		}

		// damage cooldown loop
		if (isHit) {
			isHitTicks++;
			if (isHitTicks > HIT_COOLDOWN) {
				isHitTicks = 0;
				isHit = false;
			}
		}

		// checks if the mob is on water
		isSwimming = getLevel().getTile(getX() >> 3, (getY() + getBounds().height / 2) >> 3).equals(Tile.WATER);

		if (isSwimming && isOnFire()) {
			setOnFire(false);
		}

		// automate movement with a script
		if (script != null && !script.isCompleted()) {

			// change in x and y
			int dx = 0, dy = 0;

			if (script.getDestination().getX() > getX()) {
				dx++;
			} else if (script.getDestination().getX() < getX()) {
				dx--;
			}

			if (script.getDestination().getY() > getY()) {
				dy++;
			} else if (script.getDestination().getY() < getY()) {
				dy--;
			}
			if (dx != 0 || dy != 0) {
				move(dx, dy);
			}
			return;
		}

		// force the mob to move around the mob collision
		if (isCollidingWithMob && !(this instanceof Player)) {
			moveAroundMobCollision();
			return;
		}

	}

	/**
	 * Displays the pixels of the mob to the screen
	 */
	public void render(Screen screen) {

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE * scale;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// render only the water animation down the bottom half
		if (isSwimming) {
			int[] waterColor = { 0xFF5A52FF, 0xFF000000, 0xFF000000 };
			// TODO look into this offset, might fix awkward water entrance?
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
			// TODO Look into yoffset + 3 and false = 0x00, true = 0x01
			// originally
			screen.render(xOffset, yOffset, 0 + 10 * sheet.boxes, waterColor, false, 1, sheet);
			screen.render(xOffset + 8, yOffset, 0 + 10 * sheet.boxes, waterColor, true, 1, sheet);
		}

		// Handles fire animation
		if (isOnFire()) {

			int[] firecolor = { 0xFFF7790A, 0xFFF72808, 0xFF000000 };

			screen.render(xOffset, yOffset, FireEntity.xTile + 15 * sheet.boxes, firecolor, false, 2,
					SpriteSheet.tiles);

		}

		// displays text overhead
		if (isTalking) {
			getLevel().renderFont(name, screen, xOffset, yOffset - modifier,
					new int[] { 0xFF000000, 0xFF000000, 0xFFFFCC00 }, 1);
		}

		// displays damage indicators overhead
		if (isHit) {
			getLevel().renderFont(damageTaken, screen, xOffset + isHitX, yOffset - modifier + isHitY, isHitColor, 1);
		}
	}

	/**
	 * Returns basic information about the mob
	 */
	public String toString() {
		return "Type: " + name + "\n" + super.toString();
	}

	/**
	 * Triggers the death animation and closure TODO replace the mob with a
	 * dummy dead mob to fix bound glitch TODO special case for player, do that
	 * in Player class
	 */
	public void remove() {

		getLevel().remove(this);
		isHit = false;
		isTalking = false;
		setTargeted(false);
	}

	/**
	 * Deals damage to another mob
	 * 
	 * @param min
	 *            the minimum damage dealt
	 * @param max
	 *            the maximum damage dealt
	 * @param other
	 *            the other mob to attack
	 */
	public void attack(int min, int max, Mob other) {
		other.damage(random.nextInt(max - min + 1) + min);
	}

	/**
	 * Displays an indicator that shows damage done to THIS MOB when attacked
	 * 
	 * @param damage
	 *            the damage inflicted to THIS mob
	 */
	public void damage(int damage) {

		doDamageToHealth(damage);

		damageTaken = String.valueOf(damage);
		isHit = true;

		// random offsets for damage indicators
		isHitX = random.nextInt(10) - 5;
		isHitY = random.nextInt(6) - 3;

		if (health <= 0) {
			remove();
		}
	}

	/**
	 * Decreases a mob's health Can be overridden for other stats
	 * 
	 * @param damage
	 *            the value of damage
	 */
	protected void doDamageToHealth(int damage) {
		this.health -= damage;
	}

	// TODO change to player.speak(Mob)
	public void speak(Player player) {
	}

	/**
	 * @return the direction the mob is facing/moving
	 */
	public Direction getDirection() {
		return movingDir;
	}

	/**
	 * @return true if the mob is aligned horizontally
	 */
	public boolean isLatitudinal() {
		return getDirection() == Direction.EAST || getDirection() == Direction.WEST;
	}

	/**
	 * @return true if the mob is aligned vertically
	 */
	public boolean isLongitudinal() {
		return getDirection() == Direction.NORTH || getDirection() == Direction.SOUTH;
	}

	/**
	 * Changes the direction of the mob
	 * 
	 * @param dir
	 *            the direction the mob should face
	 */
	public void setDirection(Direction dir) {
		this.movingDir = dir;
	}

	/**
	 * @return the outer range of interaction
	 */
	public Rectangle getOuterBounds() {
		return outerBounds;
	}

	/**
	 * Changes the outer bounds range
	 * 
	 * @param width
	 *            width of the mob
	 * @param height
	 *            height of the mob
	 */
	protected void setOuterBounds(int width, int height) {
		outerBounds = new Rectangle(getX() - OUTER_BOUNDS_RANGE, getY() - OUTER_BOUNDS_RANGE,
				width + OUTER_BOUNDS_RANGE * 2, height + OUTER_BOUNDS_RANGE * 2);
	}

	/**
	 * Updates the location of the outer range
	 * 
	 * @param dx
	 *            the change in x
	 * @param dy
	 *            the change in y
	 */
	protected void moveOuterBounds(int dx, int dy) {
		outerBounds.setLocation((int) outerBounds.getX() + dx, (int) outerBounds.getY() + dy);
	}

	/**
	 * Moves the mob to the specified x and y coord Also updates the bounds and
	 * outer bounds
	 * 
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	protected void moveTo(int x, int y) {
		super.moveTo(x, y);
		outerBounds.setLocation(x - OUTER_BOUNDS_RANGE, y - OUTER_BOUNDS_RANGE);
	}

	/**
	 * @return true if another mob is targeting this mob
	 */
	public boolean isTargeted() {
		return isTargeted;
	}

	/**
	 * Sets the mob as being targeted by another
	 * 
	 * @param isTargeted
	 */
	public void setTargeted(boolean isTargeted) {
		this.isTargeted = isTargeted;
	}

	/**
	 * @return true if the mob is on fire
	 */
	public boolean isOnFire() {
		return onFire;
	}

	/**
	 * Toggles whether the mob is on fire
	 * 
	 * @param onFire
	 *            value of on fire or not
	 */
	public void setOnFire(boolean onFire) {
		this.onFire = onFire;
	}

	/**
	 * @return The mob's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the mob's name
	 * 
	 * @param s
	 *            the new name
	 */
	public void setName(String s) {
		this.name = s;
	}

	/**
	 * @return the mob's speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Changes the mob's speed
	 * 
	 * @param speed
	 *            the new speed
	 */
	protected void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Determines if the mob is alive or not
	 * 
	 * @return true if dead
	 */
	public boolean isDead() {
		return health <= 0;
	}

	/**
	 * @return the mob's health bar
	 */
	protected HealthBar getHealthBar() {
		return bar;
	}

	/**
	 * @return create the mob's health bar
	 */
	protected void createHealthBar() {
		bar = new HealthBar(getLevel(), getX(), getY() + (int) getBounds().getHeight() + 2, this);
		getLevel().add(bar);
	}

	/**
	 * @return true if the mob is behind a solid building
	 */
	public boolean isBehindBuilding() {
		return isBehindBuilding;
	}

	/**
	 * @return the mob's max health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Changes the mob's max health
	 */
	public void setMaxHealth(int health) {
		maxHealth = health;
	}

	/**
	 * @return the mob's current health
	 */
	public int getCurrentHealth() {
		return health;
	}

	/**
	 * @return the rendering scale of the mob
	 */
	public int getScale() {
		return scale;
	}

	/**
	 * @return the mob's spritesheet
	 */
	protected SpriteSheet getSpriteSheet() {
		return sheet;
	}

	/**
	 * Sets the mob's spritesheet to this sheet
	 */
	protected void setSpriteSheet(SpriteSheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * @return the mob's script
	 */
	public Script getScript() {
		return script;
	}

	/**
	 * @param script
	 *            the new script for the mob
	 */
	public void setScript(Script script) {
		this.script = script;
	}

}
