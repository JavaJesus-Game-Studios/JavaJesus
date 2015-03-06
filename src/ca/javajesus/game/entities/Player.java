package ca.javajesus.game.entities;

import javax.sound.sampled.Clip;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.monsters.Demon;
import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.entities.projectiles.Bullet;
import ca.javajesus.game.entities.vehicles.Vehicle;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.JJFont;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.items.Gun;
import ca.javajesus.items.Inventory;
import ca.javajesus.level.Level;
import ca.javajesus.level.Level1;
import ca.javajesus.level.tile.Tile;

public class Player extends Mob {

	public InputHandler input;
	protected int color = Colors.get(-1, Colors.fromHex("#343434"),
			Colors.fromHex("#FF0000"), Colors.fromHex("#FFCC99"));
	protected int shirtColor = Colors.fromHex("#FF0000");
	protected int skinColor = Colors.fromHex("#FFCC99");
	protected int hairColor = Colors.fromHex("#343434");
	private int tickCount = 0;
	private boolean canChangeLevel;
	private Level nextLevel;
	private int swingTickCount = 0;
	public Gun gun;
	private boolean genericCooldown;
	public boolean isDriving;
	public Vehicle vehicle;
	protected int shootingDir;
	public int score;
	public int swordType = 0;
	public int yTile = 0;
	public Inventory inventory;
	public double stamina;
	public double startStamina;
	public boolean isTired;
	public final int swordColor = Colors.get(-1, Colors.fromHex("#f2f3f9"), -1,
			Colors.fromHex("#d6d7dc"));

	public Player(Level level, double x, double y, InputHandler input) {
		super(level, "", x, y, 1, 14, 16, SpriteSheet.player, 100);
		this.input = input;
		this.score = 0;
		this.inventory = new Inventory();
		gun = inventory.getGun();
		this.bar = new HealthBar(level, 0 + 2 * 32, this.x, this.y, this, 8);
		if (level != null)
			level.addEntity(bar);
		isTired = false;
		startStamina = 200;
		stamina = startStamina;
	}

	public Level getLevel() {
		if (level == null) {
			return new Level1();
		}
		return level;
	}

	public void changeLevel(Level level) {
		if (input.e.isPressed()) {
			this.level.getBackgroundMusic().stop();
			this.level.getBackgroundMusic().setFramePosition(0);
			this.nextLevel = level;
			this.canChangeLevel = true;
			sound.play(SoundHandler.sound.click);
			level.getBackgroundMusic().loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void tick() {

		if (isDriving) {
			return;
		}

		if (isHit) {
			isHitTicks++;
			if (isHitTicks > 20) {
				isHitTicks = 0;
				isHit = false;
			}
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
		if (input.up.isPressed() || input.down.isPressed()
				|| input.left.isPressed() || input.right.isPressed()) {
			if (!isSwinging && !isSwimming && !isDriving && !this.gun.isReloading)
				isShooting = true;
		} else {
			isShooting = false;
		}
		if (input.space.isPressed()) {
			if (!isShooting && !isSwimming && !isDriving && !isSwinging) {
				isSwinging = true;
			}
		}

		if (input.h.isPressed()) {
			for (Mob m : level.getMobs()) {
				m.isTalking = true;
			}
		}
		if (input.w.isPressed()) {
			ya--;
			if (isSolidEntityCollision(0, ya)) {
				ya++;
			}
		}
		if (input.s.isPressed()) {
			ya++;
			if (isSolidEntityCollision(0, ya)) {
				ya--;
			}
		}
		if (input.a.isPressed()) {
			xa--;
			if (isSolidEntityCollision(xa, 0)) {
				xa++;
			}
		}
		if (input.d.isPressed()) {
			xa++;
			if (isSolidEntityCollision(xa, 0)) {
				xa--;
			}
		}
		if (input.r.isPressed()) {
			if (gun != null) {
				gun.reload();
				input.r.toggle(false);
			}
		}
		if (input.i.isPressed()) {
			input.i.toggle(false);
			if (Game.inGameScreen) {
				Game.displayInventory();
			}
		}
		if (input.esc.isPressed()) {
			input.esc.toggle(false);
			if (Game.inGameScreen) {
				Game.displayPause();
			}
		}
		if (input.up.isPressed()) {
			shootingDir = 0;
		}
		if (input.down.isPressed()) {
			shootingDir = 1;
		}
		if (input.left.isPressed()) {
			shootingDir = 2;
		}
		if (input.right.isPressed()) {
			shootingDir = 3;
		}
		if (input.e.isPressed()) {
			if (!isDriving) {
				for (Entity entity : level.getEntities()) {
					if (entity instanceof Vehicle) {
						if (this.hitBox.intersects(((Vehicle) entity).hitBox)) {
							this.vehicle = (Vehicle) entity;
							this.vehicle.addPlayer(this);
							this.x = vehicle.x;
							this.y = vehicle.y;
							isDriving = true;
							vehicle.isUsed = true;
							level.remEntity(this.bar);
							input.e.toggle(false);
							return;
						}
					}
					if (entity instanceof Mob && !(entity instanceof Player)) {
						if (this.standBox.intersects(((Mob) entity).standBox)) {
							((Mob) entity).speak(this);
							input.e.toggle(false);
						}
					}
				}
			}
		}
		if (isSwimming) {
			speed = 0.35;
			if (!sound.swimming.isRunning())
				sound.play(SoundHandler.sound.swimming);
		} else if (input.shift.isPressed() && !isDriving && !isTired
				&& isMoving && !isShooting) {
			speed = 3;
			stamina--;
			if (stamina <= 0)
				isTired = true;
		} else if (isDriving && input.shift.isPressed()) {
			speed = 7;
		} else if (isDriving) {
			speed = 5;
		} else {
			speed = 1;
		}
		if (!isMoving && stamina < startStamina && !isShooting) {
			stamina += 0.5;
			isTired = false;
		} else if (isMoving && !input.shift.isPressed()) {
			stamina += 0.1;
			isTired = false;
		}

		if ((xa != 0 || ya != 0)
				&& !isSolidEntityCollision((int) (xa * speed),
						(int) (ya * speed)) && !isDriving && speed > 1) {
			move(xa, ya);
			isMoving = true;
		} else if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isDriving) {
			move(xa, ya);
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

		if (gun != null) {
			gun.tick();
		}

		// Swinging cooldown
		if (swingTickCount > 30) {
			isSwinging = false;
			swingTickCount = 0;
		}

		if (genericCooldown) {
			if (tickCount % 100 == 0) {
				genericCooldown = false;
			}
		}

	}

	public void render(Screen screen) {

		if (isDriving) {
			this.x = vehicle.x;
			this.y = vehicle.y;
			if (this.vehicle.isDead) {
				isDriving = false;
				vehicle.isUsed = false;
				vehicle.remPlayer();
			}
			return;
		}

		this.hitBox.setLocation((int) this.x - 7, (int) this.y - 8);
		this.standBox.setLocation((int) this.x - 9, (int) this.y - 10);
		if (canChangeLevel) {
			level.remEntity(this);
			level.remEntity(bar);
			init(nextLevel);
			screen.getGame().updateLevel();
			level.init();
			canChangeLevel = false;
			level.addEntity(this);
			level.addEntity(bar);
			this.x = level.spawnPoint.x;
			this.y = level.spawnPoint.y;
			input.e.toggle(false);
		}

		if (health < 20) {
			screen.getGame().redScreen();
		}

		int xTile = 0;
		int yTile = this.yTile;
		int walkingAnimationSpeed = 4;
		if (speed == 3) {
			numSteps++;
		}

		int flipTop = (numSteps >> walkingAnimationSpeed) & 1;
		int flipBottom = (numSteps >> walkingAnimationSpeed) & 1;

		if (movingDir == 0) {
			xTile += 10;
			if (!isMoving) {
				xTile = 8;
			}
		}
		if (movingDir == 1) {
			xTile += 2;
			if (!isMoving) {
				xTile = 0;
			}
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingAnimationSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
			flipBottom = (movingDir - 1) % 2;
			if (!isMoving) {
				xTile = 4;
			}
		}

		int modifier = 8 * scale;
		double xOffset = x - modifier / 2.0;
		double yOffset = y - modifier / 2.0 - 4;

		// Handles swimming animation
		if (isSwimming) {
			if (onFire) {
				onFire = false;
			}
			int watercolor = 0;
			yOffset += 4;
			if (tickCount % 60 < 15) {
				watercolor = Colors.get(-1, 225, -1, -1);
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;
				watercolor = Colors.get(-1, 115, 225, -1);
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				watercolor = Colors.get(-1, 115, -1, -1);
			} else {
				yOffset -= 1;
				watercolor = Colors.get(-1, 225, 225, -1);
			}
			screen.render(xOffset, yOffset + 3, 0 + 10 * 32, watercolor, 0x00,
					1, sheet);
			screen.render(xOffset + 8, yOffset + 3, 0 + 10 * 32, watercolor,
					0x01, 1, sheet);
		}

		// Handles fire animation
		if (onFire) {
			int firecolor = Colors.get(-1, Colors.fromHex("#F7790A"), 540, -1);

			screen.render(xOffset + 4, yOffset - 8, this.level.fireList.get(0)
					.getXTile() + 15 * 32, firecolor, 0, 1, SpriteSheet.tiles);

		}

		// Normal Player movement -- Not Attacking Anything
		if (!isShooting && !isSwinging) {
			// Upper body 1
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, color, flipTop, scale, sheet);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, color, flipTop, scale, sheet);

			if (!isSwimming) {
				// Lower Body 1
				screen.render(xOffset + (modifier * flipBottom), yOffset
						+ modifier, xTile + (yTile + 1) * 32, color,
						flipBottom, scale, sheet);
				// Lower Body 2
				screen.render(xOffset + modifier - (modifier * flipBottom),
						yOffset + modifier, (xTile + 1) + (yTile + 1) * 32,
						color, flipBottom, scale, sheet);

			}
		}

		// Handles Shooting Animation
		if (isShooting) {
			xTile = gun.gunType * 4;
			yTile = 2;
			if (defense > 2) {
				yTile += 12;
			}

			if (shootingDir == 1) {
				yTile += 2;
			}
			if (shootingDir == 0) {
				yTile += 2;
				xTile += 16;
			} else if (shootingDir > 1) {
				xTile += ((numSteps >> walkingAnimationSpeed) & 1) * 2;
				flipTop = (shootingDir - 1) % 2;
				flipBottom = (shootingDir - 1) % 2;
			}

			// Upper Body 1
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, color, flipTop, scale, sheet);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, color, flipTop, scale, sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * flipBottom),
					yOffset + modifier, xTile + (yTile + 1) * 32, color,
					flipBottom, scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * 32, color,
					flipBottom, scale, sheet);

			int bulletOffset = -4;
			if (shootingDir == 2) {
				bulletOffset = -7;
			}

			if (stamina > 0 && gun != null) {
				gun.fire(level, this.x + bulletOffset, this.y - 2, shootingDir,
						this);
			}

			if (gun.isReloading) {
				isShooting = false;
			}
			swingTickCount = 0;

		}

		// Handles Swinging Animation
		if (isSwinging) {
			xTile = swordType;
			yTile = 0;

			if (movingDir == 0) {
				xTile = 2;
			} else if (movingDir > 1) {
				xTile = 4;
				flipTop = (movingDir - 1) % 2;
				flipBottom = (movingDir - 1) % 2;
			}

			// Upper Body 1
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, color, flipTop, scale, SpriteSheet.swords);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, color, flipTop, scale,
					SpriteSheet.swords);

			// Lower Body 1
			screen.render(xOffset + (modifier * flipBottom),
					yOffset + modifier, xTile + (yTile + 1) * 32, color,
					flipBottom, scale, SpriteSheet.swords);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * 32, color,
					flipBottom, scale, SpriteSheet.swords);

			if (movingDir < 2) {
				// Lower Body 1
				screen.render(xOffset + (modifier * flipBottom), yOffset + 2
						* modifier, xTile + (yTile + 2) * 32, color,
						flipBottom, scale, SpriteSheet.swords);

				// Lower Body 2
				screen.render(xOffset + modifier - (modifier * flipBottom),
						yOffset + 2 * modifier, (xTile + 1) + (yTile + 2) * 32,
						color, flipBottom, scale, SpriteSheet.swords);
			} else {
				int num = 0;
				if (movingDir == 2) {
					num = 16;
				}
				// Upper Body 2
				screen.render(xOffset + 2 * modifier - num
						- (modifier * flipTop), yOffset, (xTile + 2) + yTile
						* 32, color, flipTop, scale, SpriteSheet.swords);

				// Lower Body 2
				screen.render(xOffset + 2 * modifier - num
						- (modifier * flipBottom), yOffset + modifier,
						(xTile + 2) + (yTile + 1) * 32, color, flipBottom,
						scale, SpriteSheet.swords);
			}

			// Renders the Actual Sword

			yTile += 3;
			// Upper Body 1
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, swordColor, flipTop, scale,
					SpriteSheet.swords);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, swordColor, flipTop, scale,
					SpriteSheet.swords);

			// Lower Body 1
			screen.render(xOffset + (modifier * flipBottom),
					yOffset + modifier, xTile + (yTile + 1) * 32, swordColor,
					flipBottom, scale, SpriteSheet.swords);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * 32, swordColor,
					flipBottom, scale, SpriteSheet.swords);

			if (movingDir < 2) {
				// Lower Body 1
				screen.render(xOffset + (modifier * flipBottom), yOffset + 2
						* modifier, xTile + (yTile + 2) * 32, swordColor,
						flipBottom, scale, SpriteSheet.swords);

				// Lower Body 2
				screen.render(xOffset + modifier - (modifier * flipBottom),
						yOffset + 2 * modifier, (xTile + 1) + (yTile + 2) * 32,
						swordColor, flipBottom, scale, SpriteSheet.swords);
			} else {
				int num = 0;
				if (movingDir == 2) {
					num = 16;
				}
				// Upper Body 2
				screen.render(xOffset + 2 * modifier - num
						- (modifier * flipTop), yOffset, (xTile + 2) + yTile
						* 32, swordColor, flipTop, scale, SpriteSheet.swords);

				// Lower Body 2
				screen.render(xOffset + 2 * modifier - num
						- (modifier * flipBottom), yOffset + modifier,
						(xTile + 2) + (yTile + 1) * 32, swordColor, flipBottom,
						scale, SpriteSheet.swords);
			}

		}

		if (isHit) {
			JJFont.render(damageTaken, screen, (int) xOffset + isHitX,
					(int) yOffset - 10 + isHitY, isHitColor, 1);
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

	public void checkTile(double x, double y) {
		Tile currentTile = level.getTile((int) x / 8, (int) y / 8);

		if (isMoving) {

			if (currentTile == Tile.GRASS) {
				if (!sound.footstepsGrass.isRunning())
					sound.play(sound.footstepsGrass);
			} else if (currentTile == Tile.MUD) {
				if (!sound.footstepsWood.isRunning())
					sound.play(sound.footstepsWood);
			} else if (currentTile == Tile.ROAD1 || currentTile == Tile.ROAD2
					|| currentTile == Tile.ROAD3) {
				if (!sound.footstepsRoad.isRunning())
					sound.play(sound.footstepsRoad);
			} else if (currentTile == Tile.DIRTROAD) {
				if (!sound.footstepsDirt.isRunning())
					sound.play(sound.footstepsDirt);
			}
		}

	}

	public void updateColor() {
		this.color = Colors.get(-1, hairColor, shirtColor, skinColor);
	}

	public void setShirtColor(int num) {
		this.shirtColor = num;
	}

	public void setSkinColor(int num) {
		this.skinColor = num;
	}

	public void setHairColor(int num) {
		this.hairColor = num;
	}

	public void renderDisplay(Screen screen, int scale) {

		int modifier = 8 * scale;
		double xOffset = modifier / 2.0;
		double yOffset = modifier / 2.0 - 4;

		int flipTop = 0;
		int flipBottom = 0;
		int xTile = 0;

		// Normal Player movement -- Not Attacking Anything
		// Upper body 1
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile
				* 32, color, flipTop, scale, sheet);
		// Upper Body 2
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
				(xTile + 1) + yTile * 32, color, flipTop, scale, sheet);

		// Lower Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier,
				xTile + (yTile + 1) * 32, color, flipBottom, scale, sheet);
		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom,
				scale, sheet);

	}
}
