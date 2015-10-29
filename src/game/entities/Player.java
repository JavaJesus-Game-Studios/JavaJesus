package game.entities;

import game.ChatHandler;
import game.Display;
import game.Game;
import game.InputHandler;
import game.SoundHandler;
import game.entities.monsters.Demon;
import game.entities.npcs.NPC;
import game.entities.npcs.aggressive.Companion;
import game.entities.structures.furniture.Chest;
import game.entities.vehicles.Vehicle;
import game.graphics.JJFont;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import items.Armor;
import items.Bazooka;
import items.Gun;
import items.Inventory;
import items.Item;
import items.Sword;

import java.util.ArrayList;

import javax.sound.sampled.Clip;

import level.Level;
import level.Level1;
import level.tile.Tile;
import quests.Quest;

public class Player extends Mob {

	private static final long serialVersionUID = -4170571410784465465L;
	
	public transient InputHandler input;
	protected int[] color = { 0xFF343434, 0xFFFF0000, 0xFFFFCC99 };
	protected int shirtColor = 0xFFFF0000;
	protected int skinColor = 0xFFFFCC99;
	protected int hairColor = 0xFF343434;
	private int tickCount = 0;
	public Gun gun;
	private boolean genericCooldown;
	public boolean isDriving;
	public Vehicle vehicle;
	public int shootingDir;
	public int score;
	public Sword sword;
	public int yTile = 0;
	public Inventory inventory;
	public double stamina;
	public double startStamina;
	public boolean isTired;
	public ArrayList<Quest> activeQuests = new ArrayList<Quest>();
	public ArrayList<Quest> completedQuests = new ArrayList<Quest>();
	public int maxShield = 1;
	public double shield;

	private SpriteSheet gunSheet = SpriteSheet.playerGuns;

	public static NPC companion;
	public boolean jesusMode = false;

	public Player(Level level, int x, int y) {
		super(level, "", x, y, 1, 14, 16, SpriteSheet.player, 100);
		this.score = 0;
		this.inventory = new Inventory();
		gun = inventory.getGun(this);
		sword = inventory.getSword(this);
		isTired = false;
		startStamina = 200;
		stamina = startStamina;

		companion = new Companion(level, "Companion", x + 10, y, 16, 16, 100,
				new int[] { 0xFF2A2A2A, 0xFF000046, 0xFFEDC5AB }, 0, 4, this);

	}
	
	public void setInput(InputHandler input) {
		this.input = input;
		if (isDriving) {
			vehicle.input = input;
		}
	}

	public Level getLevel() {
		if (level == null) {
			return new Level1();
		}
		return level;
	}

	public void equip() {
		gun = inventory.getGun(this);
		if (gun.getClip() == null) {
			gun.initSound();
		}
		sword = inventory.getSword(this);
	}

	public void equip(Armor armor) {
		this.yTile = armor.getRow();
		this.defense = armor.getDefense();
		this.maxShield = armor.getShield();
		shield = maxShield;
		switch (armor.getType()) {
		case VEST:
			this.gunSheet = SpriteSheet.playerVestedGuns;
			break;
		case KNIGHT:
			this.gunSheet = SpriteSheet.playerKnightedGuns;
			break;
		case HORNED:
			this.gunSheet = SpriteSheet.playerHornedGuns;
			break;
		case OWL:
			this.gunSheet = SpriteSheet.playerIstrahiimGuns;
			break;
		default:
			this.gunSheet = SpriteSheet.playerGuns;
			break;
		}
	}

	public void changeLevel(Level level) {
		
		sound.play(SoundHandler.sound.click);
		if (!this.level.getBackgroundMusic().equals(level.getBackgroundMusic())) {
			this.level.getBackgroundMusic().stop();
			this.level.getBackgroundMusic().setFramePosition(0);
			level.getBackgroundMusic().loop(Clip.LOOP_CONTINUOUSLY);
		}

		if (!level.isLoaded) {
			level.load();
		}
		input.e.toggle(false);
		this.level.remEntity(this);
		if (isOnFire()) {
			setOnFire(false);
		}
		this.level.clear();
		init(level);
		level.init();
		level.addEntity(this);
		this.x = level.spawnPoint.x;
		this.y = level.spawnPoint.y;
		this.getBounds().setLocation(this.x - this.width / 2,
				this.y - this.height / 2);
		this.getOuterBounds().setLocation(this.x - this.width / 2 - 2,
				this.y - this.height / 2 - 2);
	}

	public void tick() {

		if (isDriving) {
			return;
		}

		checkTile(x, y);

		if (isHit) {
			isHitTicks++;
			if (isHitTicks > 20) {
				isHitTicks = 0;
				isHit = false;
			}
		}

		if (script != null && !script.isCompleted()) {
			script.moveToPoint();
		}

		int xa = 0;
		int ya = 0;
		if (input.j.isPressed()) {
			jesusMode = !jesusMode;
			isSwimming = false;
			input.j.toggle(false);
		}
		if (input.t.isPressed()) {
			if (!genericCooldown) {
				level.addEntity(new Demon(level, "Demon", (int) this.x,
						(int) this.y, 1));
			}
			genericCooldown = true;
		}
		if (input.up.isPressed() || input.down.isPressed()
				|| input.left.isPressed() || input.right.isPressed()) {
			if (!isSwinging && !isSwimming && !isDriving && gun != null
					&& !this.gun.isReloading)
				isShooting = true;
		} else {
			isShooting = false;
		}
		if (input.space.isPressed()) {
			if (!isShooting && !isSwimming && !isDriving && !isSwinging
					&& sword != null) {
				if (sword != null) {
					sword.swing();
					isSwinging = true;
				}
			}
		}

		if (input.h.isPressed()) {
			for (Mob m : level.getMobs()) {
				if (!m.isDead())
					m.isTalking = true;
			}
		}
		if (input.w.isPressed()) {
			ya--;
			if (!jesusMode && isSolidEntityCollision(0, ya)) {
				ya++;
			}
		}
		if (input.s.isPressed()) {
			ya++;
			if (!jesusMode && isSolidEntityCollision(0, ya)) {
				ya--;
			}
		}
		if (input.a.isPressed()) {
			xa--;
			if (!jesusMode && isSolidEntityCollision(xa, 0)) {
				xa++;
			}
		}
		if (input.d.isPressed()) {
			xa++;
			if (!jesusMode && isSolidEntityCollision(xa, 0)) {
				xa--;
			}
		}
		if (input.r.isPressed()) {
			if (gun != null) {
				gun.reload();
				input.r.toggle(false);
			}
		}
		if (input.f3.isPressed()) {
			Game.setDisplayDevScreen(!Game.getDisplayDevScreen());
			input.f3.toggle(false);
		}
		if (input.i.isPressed()) {
			input.i.toggle(false);
			if (Display.inGameScreen) {
				Display.displayInventory();
				input.w.toggle(false);
				input.a.toggle(false);
				input.s.toggle(false);
				input.d.toggle(false);
				input.shift.toggle(false);
			}
		}
		if (input.esc.isPressed()) {
			input.esc.toggle(false);
			if (Display.inGameScreen) {
				Display.displayPause();
			}
		}
		if (input.e.isPressed()) {
			if (!isDriving) {
				for (Entity entity : level.getEntities()) {
					if (entity instanceof Vehicle) {
						if (this.getBounds().intersects(
								((Vehicle) entity).getBounds())
								&& !((Vehicle) entity).isDead) {
							this.vehicle = (Vehicle) entity;
							this.vehicle.addPlayer(this);
							this.x = vehicle.x;
							this.y = vehicle.y;
							isDriving = true;
							vehicle.isUsed = true;
							input.e.toggle(false);
							return;
						}
					}
					if (entity instanceof Chest
							&& this.getOuterBounds().intersects(
									((Chest) entity).bounds)) {
						((Chest) entity).open(this);
					}
					if (entity instanceof Mob && !(entity instanceof Player)) {
						if (this.getOuterBounds().intersects(
								((Mob) entity).getOuterBounds())
								&& !((Mob) entity).isDead) {
							((Mob) entity).speak(this);
							input.e.toggle(false);
						}
					}
				}
			}
		}
		if (input.v.isPressed()) {
			ChatHandler.toggle();
			input.v.toggle(false);
		}
		if (sword != null) {
			isSwinging = sword.isSwinging;
		}
		if (isSwimming) {
			speed = 1;
			if (!sound.swimming.isRunning())
				sound.play(SoundHandler.sound.swimming);
		} else if (input.shift.isPressed() && !isDriving && !isTired
				&& isMoving() && !isShooting && !isSwinging) {
			speed = 2;
			stamina--;
			if (stamina <= 0)
				isTired = true;
		} else {
			speed = 1;
		}
		if (!isMoving() && stamina < startStamina && !isShooting) {
			stamina += 0.5;
			isTired = false;
		}
		if (!isMoving() && shield < maxShield && !isShooting) {
			shield += (0.0005 * maxShield);
		}
		if (isMoving() && !input.shift.isPressed()) {
			if (stamina < startStamina)
				stamina += 0.1;
			if (shield < maxShield)
				shield += (0.00001 * maxShield);
			isTired = false;
		}

		if (jesusMode) {
			move(xa, ya, true);
			setMoving(true);
		} else if ((xa != 0 || ya != 0)
				&& !isSolidEntityCollision((int) (xa * speed),
						(int) (ya * speed)) && !isDriving && speed > 1
				&& !isSwinging) {
			if (gun != null && gun instanceof Bazooka && !isShooting) {
				move(xa, ya);
				setMoving(true);
			} else if (gun != null && !(gun instanceof Bazooka)) {
				move(xa, ya);
				setMoving(true);
			} else if (gun == null) {
				move(xa, ya);
				setMoving(true);
			}
		} else if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isDriving && !isSwinging) {
			if (gun != null && gun instanceof Bazooka && !isShooting) {
				move(xa, ya);
				setMoving(true);
			} else if (gun != null && !(gun instanceof Bazooka)) {
				move(xa, ya);
				setMoving(true);
			} else if (gun == null) {
				move(xa, ya);
				setMoving(true);
			}
		} else {
			setMoving(false);
		}

		if (input.up.isPressed()) {
			shootingDir = 0;
			setDirection(Direction.NORTH);
		}
		if (input.down.isPressed()) {
			shootingDir = 1;
			setDirection(Direction.SOUTH);
		}
		if (input.left.isPressed()) {
			shootingDir = 2;
			setDirection(Direction.WEST);
		}
		if (input.right.isPressed()) {
			shootingDir = 3;
			setDirection(Direction.EAST);
		}
		if (level.getTile(x >> 3, (y + 3) >> 3).getId() == 3 && !jesusMode) {
			isSwimming = true;
		} else {
			isSwimming = false;
		}

		if (gun != null) {
			gun.tick();
		}

		if (sword != null) {
			sword.tick();
		}

		if (genericCooldown) {
			if (tickCount % 100 == 0) {
				genericCooldown = false;
			}
		}
		tickCount++;

	}

	public void render(Screen screen) {
		super.render(screen);
		if (isDriving) {
			this.x = vehicle.x;
			this.y = vehicle.y;
			if (this.vehicle.isDead) {
				isDriving = false;
				vehicle.isUsed = false;
				vehicle.remPlayer();
			}
			
		}

		this.getBounds().setLocation(this.x - this.width / 2,
				this.y - this.height / 2);
		this.getOuterBounds().setLocation(this.x - this.width / 2 - 2,
				this.y - this.height / 2 - 2);
		
		if (isDriving) {
			return;
		}

		if (health < 20) {
			// screen.setShader(16711680);
		}

		int xTile = 0;
		int yTile = this.yTile;
		int walkingAnimationSpeed = 4;
		if (speed == 3) {
			numSteps++;
		}

		int flip = (numSteps >> walkingAnimationSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 10;
			if (!isMoving()) {
				xTile = 8;
			}
		}
		if (getDirection() == Direction.SOUTH) {
			xTile += 2;
			if (!isMoving()) {
				xTile = 0;
			}
		} else if (isLatitudinal(getDirection())) {
			xTile += 4 + ((numSteps >> walkingAnimationSpeed) & 1) * 2;
			if (getDirection() == Direction.WEST) {
				flip = 1;
			} else {
				flip = 0;
			}
			if (!isMoving()) {
				xTile = 4;
			}
		}

		int modifier = 8 * scale;
		int xOffset = x - modifier;
		int yOffset = y - modifier;

		// Handles swimming animation
		if (isSwimming) {
			isShooting = false;
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

		// Normal Player movement -- Not Attacking Anything
		if (!isShooting && !isSwinging) {
			// Upper body 1
			screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile
					* sheet.boxes, color, flip, scale, sheet);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flip), yOffset,
					(xTile + 1) + yTile * sheet.boxes, color, flip, scale,
					sheet);

			if (!isSwimming) {
				// Lower Body 1
				screen.render(xOffset + (modifier * flip), yOffset + modifier,
						xTile + (yTile + 1) * sheet.boxes, color, flip, scale,
						sheet);
				// Lower Body 2
				screen.render(xOffset + modifier - (modifier * flip), yOffset
						+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes,
						color, flip, scale, sheet);

			}
		}

		// Handles Shooting Animation
		if (isShooting) {
			xTile = 0;
			yTile = gun.playerOffset;

			if (getDirection() == Direction.NORTH) {
				xTile += 8;
			}
			if (getDirection() == Direction.SOUTH) {
				xTile += 4;
			} else if (isLatitudinal(getDirection())) {
				xTile += ((numSteps >> walkingAnimationSpeed) & 1) * 2;
				if (getDirection() == Direction.WEST) {
					flip = 1;
				} else {
					flip = 0;
				}
			}

			SpriteSheet sheet = this.gunSheet;

			// Upper Body 1
			screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile
					* sheet.boxes, color, flip, scale, sheet);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flip), yOffset,
					(xTile + 1) + yTile * sheet.boxes, color, flip, scale,
					sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * flip), yOffset + modifier,
					xTile + (yTile + 1) * sheet.boxes, color, flip, scale,
					sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flip), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes, color,
					flip, scale, sheet);

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

		}

		// Handles Swinging Animation
		if (isSwinging) {
			sword.render(screen);
		}

		if (isHit && !isDriving) {
			JJFont.render(damageTaken, screen, (int) xOffset + isHitX,
					(int) yOffset - 10 + isHitY, isHitColor, 1);
		}

	}

	public void checkTile(double x, double y) {
		Tile currentTile = level.getTile((int) x / 8, (int) y / 8);

		if (isMoving()) {

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
		color[0] = hairColor;
		color[1] = shirtColor;
		color[2] = skinColor;
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

	public void damage(int a, int b) {
		int damage = random.nextInt(b - a + 1) + a;
		while (shield > 0 && damage > 0) {
			shield--;
			damage--;
		}
		if (shield < 0) {
			shield = 0;
		}
		damage -= defense;
		if (damage <= 0) {
			damage = 0;
		}
		if (isDriving)
			this.vehicle.health -= damage;
		else
			this.health -= damage;
		damageTaken = String.valueOf(damage);
		isHit = true;
		isHitX = random.nextInt(10) - 5;
		isHitY = random.nextInt(6) - 3;
		isHitColor = new int[] { 0xFF000000, 0xFF000000, 0xFFFF0000 };
		if (health <= 0) {
			kill();
		}
	}

	public void grantDevPowers() {
		this.strength = 100;
		this.startStamina = Integer.MAX_VALUE;
		this.stamina = startStamina;
		this.defense = 10;
		this.startHealth = Integer.MAX_VALUE;
		this.health = startHealth;
		this.maxShield = 1000;
		this.shield = maxShield;
		inventory.addItem(Item.blackHoleGun);
		inventory.addItem(Item.bazooka);
	}

	public int[] getColor() {
		return color;
	}
	
	public SpriteSheet getSpriteSheet() {
		return this.gunSheet;
	}

}
