package ca.javajesus.game.entities;

import ca.javajesus.game.InputHandler;
import ca.javajesus.game.entities.monsters.Demon;
import ca.javajesus.game.entities.particles.HealthPack;
import ca.javajesus.game.entities.particles.projectiles.Bullet;
import ca.javajesus.game.entities.particles.projectiles.Projectile;
import ca.javajesus.game.entities.vehicles.Vehicle;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;
import ca.javajesus.level.Level1;

public class Player extends Mob {

	private InputHandler input;
	private int colour = Colors.get(-1, 111, 300, 543);
	private int scale = 1;
	protected boolean isSwimming = false;
	public boolean isSwinging = false;
	protected boolean isShooting = false;
	private int tickCount = 0;
	private boolean changeLevel;
	private double scaledSpeed;
	/** The current stage of the animation */
	private int swingTick = 0;
	private int swingTickCount = 0;
	private boolean cooldown = true;
	public int gunType = 4;
	private boolean genericCooldown;
	private Sword sword;
	public boolean isDriving;
	protected Vehicle vehicle;

	public Player(Level level, double x, double y, InputHandler input) {
		super(level, "player", x, y, 1, 14, 16, SpriteSheet.player, 1000);
		this.input = input;
	}

	public double getPlayerVelocity() {
		return velocity;
	}

	public Sword getSword() {
		return sword;
	}

	public Level getLevel() {
		if (level == null) {
			return new Level1("/levels/water_test_level.png");
		}
		return level;
	}

	public void tick() {

		if (!isDriving) {
			updateHealth();
		}

		int xa = 0;
		int ya = 0;
		if (input.t.isPressed()) {
			if (!genericCooldown) {
				level.addEntity(new Demon(level, "Demon", (int) this.x,
						(int) this.y, 1));
			}
			genericCooldown = true;
		}
		if (input.f.isPressed()) {
			if (!isSwinging && !isSwimming && !isDriving)
				isShooting = true;
		}
		if (input.space.isPressed()) {
			if (!isShooting && !isSwimming && !isDriving)
				isSwinging = true;
		}

		if (input.r.isPressed()) {
			changeLevel = true;
		}
		if (input.up.isPressed()) {
			ya--;
		}
		if (input.down.isPressed()) {
			ya++;
		}
		if (input.left.isPressed()) {
			xa--;
		}
		if (input.right.isPressed()) {
			xa++;
		}

		if (input.e.isPressed()) {
			if (!genericCooldown) {
				if (isDriving) {
					isDriving = false;
				} else {
					for (Entity entity : level.getEntities()) {
						if (entity instanceof Vehicle) {
							if (this.hitBox
									.intersects(((Vehicle) entity).hitBox)) {
								this.vehicle = (Vehicle) entity;
								this.x = vehicle.x;
								this.y = vehicle.y;
								isDriving = true;
							}
						}
					}
				}
			}
			genericCooldown = true;
		}

		if (input.h.isPressed()) {
			level.addEntity(new HealthPack(level, this.x, this.y));
		}
		if (isSwimming) {
			scaledSpeed = 0.35;
		} else if (input.shift.isPressed() && !isDriving) {
			scaledSpeed = 3;
		} else if (isDriving && input.shift.isPressed()) {
			scaledSpeed = 7;
		} else if (isDriving) {
			scaledSpeed = 5;
		} else {
			scaledSpeed = 1;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya, scaledSpeed);
			isMoving = true;
		} else {
			isMoving = false;
		}
		int xx = (int) x;
		int yy = (int) y;
		if (level.getTile(xx >> 3, yy >> 3).getId() == 3) {
			isSwimming = true;
		}
		if (isSwimming && level.getTile(xx >> 3, yy >> 3).getId() != 3) {
			isSwimming = false;
		}
		tickCount++;

		// The current time in ticks in which an attack is started
		if (isSwinging || isShooting) {
			swingTickCount++;
		}

		// Determines the intervals at which the position increases
		if (isSwinging) {
			if (swingTickCount % 60 <= 12) {
				swingTick = 0;
			} else if (swingTickCount % 60 <= 24) {
				swingTick = 1;
			} else if (swingTickCount % 60 <= 36) {
				swingTick = 2;
			}
		}

		// Player cannot attack again during this interval -- hence calls a
		// cooldown inbetween
		if (isSwinging && swingTickCount % 60 == 0) {
			cooldown = false;
		} else if (isShooting && swingTickCount % 10 == 0) {
			cooldown = false;
		} else {
			cooldown = true;
		}
		if (genericCooldown) {
			if (tickCount % 100 == 0) {
				genericCooldown = false;
			}
		}

	}

	public void render(Screen screen) {

		if (isDriving) {
			this.vehicle.x = this.x;
			this.vehicle.y = this.y;
			this.vehicle.movingDir = this.movingDir;
			if (this.vehicle.hasDied) {
				isDriving = false;
			}
			return;
		}

		this.hitBox.setLocation((int) this.x, (int) this.y - 8);
		if (changeLevel) {
			level.remEntity(this);
			init(screen.getGame().randomLevel);
			screen.getGame().updateLevel();
			changeLevel = false;
			level.addEntity(this);
			level.addEntity(bar);
		}

		int xTile = 0;
		int yTile = 0;
		int walkingAnimationSpeed = 4;
		if (scaledSpeed == 3) {
			numSteps++;
		}

		int flipTop = (numSteps >> walkingAnimationSpeed) & 1;
		int flipBottom = (numSteps >> walkingAnimationSpeed) & 1;

		int flipAttack1 = (swingTick >> walkingAnimationSpeed) & 1;
		int flipAttack2 = (swingTick >> walkingAnimationSpeed) & 1;

		int swingModifier = 0;

		if (movingDir == 0) {
			xTile += 10;
		}
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingAnimationSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
			flipBottom = (movingDir - 1) % 2;
			flipAttack1 = (movingDir - 1) % 2;
			flipAttack2 = (movingDir - 1) % 2;
		}

		// Determines the sprite position at the designated swingTick count
		switch (swingTick) {
		case 1:
			swingModifier = 2;
			break;
		case 2:
			swingModifier = 4;
			break;

		case 3:
			swingModifier = 6;
			break;

		default:
			swingModifier = 0;
			break;
		}

		int modifier = 8 * scale;
		double xOffset = x - modifier / 2.0;
		double yOffset = y - modifier / 2.0 - 4;

		// Handles swimming animation
		if (isSwimming) {
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
		if (onFire) {
			int fireColour = 0;
			if (tickCount % 60 < 15) {
				fireColour = Colors.get(Colors.toHex("#F51F07"),
						Colors.toHex("#F7790A"), 540, -1);
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				fireColour = Colors.get(Colors.toHex("#F51F07"),
						Colors.toHex("#F7790A"), 540, -1);
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				fireColour = Colors.get(Colors.toHex("#F51F07"),
						Colors.toHex("#F7790A"), 540, -1);
			} else {
				fireColour = Colors.get(Colors.toHex("#F51F07"),
						Colors.toHex("#F7790A"), 540, -1);
			}
			screen.render(xOffset, yOffset + 3, 0 + 11 * 32, fireColour, 0x00,
					1, sheet);
			screen.render(xOffset + 8, yOffset + 3, 0 + 11 * 32, fireColour,
					0x01, 1, sheet);
		}

		// Normal Player movement -- Not Attacking Anything
		if (!(isSwinging || isShooting)) {
			// Upper body 1
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, colour, flipTop, scale, sheet);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, colour, flipTop, scale, sheet);

			if (!isSwimming) {
				// Lower Body 1
				screen.render(xOffset + (modifier * flipBottom), yOffset
						+ modifier, xTile + (yTile + 1) * 32, colour,
						flipBottom, scale, sheet);
				// Lower Body 2
				screen.render(xOffset + modifier - (modifier * flipBottom),
						yOffset + modifier, (xTile + 1) + (yTile + 1) * 32,
						colour, flipBottom, scale, sheet);

			}
		}

		// Handles Shooting Animation
		if (isShooting) {
			xTile = gunType;
			yTile = 2;

			if (movingDir == 1) {
				yTile += 2;
			}
			if (movingDir == 0) {
				yTile += 2;
				xTile += 16;
			}

			// Upper Body 1
			screen.render(xOffset + (modifier * flipAttack1), yOffset, xTile
					+ yTile * 32, colour, flipAttack1, scale, sheet);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flipAttack1),
					yOffset, (xTile + 1) + yTile * 32, colour, flipAttack1,
					scale, sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * flipAttack2), yOffset
					+ modifier, xTile + (yTile + 1) * 32, colour, flipAttack2,
					scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipAttack2),
					yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour,
					flipAttack2, scale, sheet);

			if (!cooldown) {
				int bulletOffset = 0;
				if (movingDir == 2) {
					bulletOffset = -7;
				}
				if (movingDir == 0) {
					bulletOffset += 1;
				}
				if (movingDir == 1) {
					bulletOffset -= 11;
				}

				level.addEntity(new Bullet(level, (this.x + 1 + bulletOffset),
						(this.y - 2), movingDir, this));
				isShooting = false;
				swingTickCount = 0;
			}

		}

		// Handles Swinging Animation
		if (isSwinging) {

			if (swingTickCount == 1) {
				sword = new Sword(level, 0, colour, this.x, this.y, this);
				level.addEntity(sword);
			}

			xTile = swingModifier;
			yTile = 4;

			// Upper Body 1
			screen.render(xOffset + (modifier * flipAttack1), yOffset, xTile
					+ yTile * 32, colour, flipAttack1, scale, sheet);

			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flipAttack1),
					yOffset, (xTile + 1) + yTile * 32, colour, flipAttack1,
					scale, sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * flipAttack2), yOffset
					+ modifier, xTile + (yTile + 1) * 32, colour, flipAttack2,
					scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipAttack2),
					yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour,
					flipAttack2, scale, sheet);

			// Stops Sword Animation after this amount of ticks
			if (swingTickCount % 60 == 48) {
				isSwinging = false;
				swingTickCount = 0;
			}

		}
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;

		if (isDriving) {
			for (int x = xMin; x < xMax; x++) {
				if (isSolidTile(xa, ya, x, yMin)
						|| isWaterTile(xa, ya, x, yMin)) {
					return true;
				}
			}
			for (int x = xMin; x < xMax; x++) {
				if (isSolidTile(xa, ya, x, yMax)
						|| isWaterTile(xa, ya, x, yMax)) {
					return true;
				}
			}
			for (int y = yMin; y < yMax; y++) {
				if (isSolidTile(xa, ya, xMin, y)
						|| isWaterTile(xa, ya, xMin, y)) {
					return true;
				}
			}
			for (int y = yMin; y < yMax; y++) {
				if (isSolidTile(xa, ya, xMax, y)
						|| isWaterTile(xa, ya, xMax, y)) {
					return true;
				}
			}
		}

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
