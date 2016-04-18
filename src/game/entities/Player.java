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
import game.gui.overview.InventoryGUI;
import items.Armor;
import items.Bazooka;
import items.Gun;
import items.Inventory;
import items.Item;
import items.Sword;

import java.awt.Color;
import java.util.ArrayList;

import javax.sound.sampled.Clip;

import level.Level;
import level.Level1;
import level.tile.Tile;
import quests.Quest;
import utility.Direction;

/*
 * The Player is a mob that is directly controlled by a user
 * It interacts with the world through keyboard input
 */
public class Player extends Mob implements Skills {

	private static final long serialVersionUID = -4170571410784465465L;

	// keyboard input
	private transient InputHandler input;

	// player color set: hair, shirt, skin
	private static final int[] color = { 0xFF343434, 0xFFFF0000, 0xFFFFCC99 };

	// the internal tick timer
	private int tickCount;

	// spawn demon cooldown
	private boolean demonCooldown;

	// the vehicle the player is in, null if not driving
	private Vehicle vehicle;

	// direction the player is shooting
	private Direction shootingDir;

	// the y tile on the spritesheet to render
	private int yTile;

	// the inventory of the player
	private Inventory inventory;

	// the current stamina, influences special swings and running
	private double stamina;

	// the max stamina
	private double maxStamina;

	// List of the active quests
	private ArrayList<Quest> activeQuests = new ArrayList<Quest>();

	// List of completed quests
	private ArrayList<Quest> completedQuests = new ArrayList<Quest>();

	// takes hits before using health
	private double shield;

	// the max shield
	private int maxShield = 1;

	// the spritesheet to use when the player is shooting
	private SpriteSheet gunSheet = SpriteSheet.playerGuns;

	// whether or not the player can clip through walls
	public boolean jesusMode;

	// the size of the sprite
	private static final int SIZE = 16;

	// the starting health value
	private static final int START_HEALTH = 100;

	// the starting stamina value
	private static final int START_STAMINA = 200;
	
	// player stats
	private int strength, defense;

	/**
	 * Creates a new player for the game
	 * 
	 * @param level
	 *            the initial level
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public Player(Level level, int x, int y) {
		super(level, "", x, y, 1, SIZE, SIZE, SpriteSheet.player, START_HEALTH);

		inventory = new Inventory(this);
		maxStamina = START_STAMINA;
		stamina = maxStamina;

	}

	// TODO will change how this works
	public void setInput(InputHandler input) {
		this.input = input;
		if (vehicle != null) {
			vehicle.input = input;
		}
	}

	public void equip(Armor armor) {
		this.yTile = armor.getRow();
		this.maxShield = armor.getShield();
		this.gunSheet = armor.getGunSpritesheet();
	}

	/**
	 * Transitions the player from one level to another
	 */
	public void updateLevel(Level level) {

		// play the click sound
		SoundHandler.play(SoundHandler.click);

		// loop the new background music if applicable
		if (!getLevel().getBackgroundMusic().equals(level.getBackgroundMusic())) {
			SoundHandler.playLoop(level.getBackgroundMusic());
		}

		// load the new level if it has not been loaded yet
		if (!level.isLoaded) {
			level.load();
		}

		input.e.toggle(false);

		getLevel().remEntity(this);

		// TODO temporary fix
		if (isOnFire()) {
			setOnFire(false);
		}

		// clears all the dead mobs on the last level
		getLevel().clear();

		// change the global level variable
		super.updateLevel(level);

		// adds the player to the new level
		level.addEntity(this);

		// go to the spawn location for that level
		moveTo(level.spawnPoint.x, level.spawnPoint.y);
	}

	/**
	 * Internal tick clock that updates the player
	 */
	public void tick() {

		// do not update if driving
		if (vehicle != null) {
			return;
		}

		// do basic checks
		super.tick();

		// automate movement with a script
		if (script != null && !script.isCompleted()) {
			script.moveToPoint();
			return;
		}

		// the change in x and y (movement)
		int dx = 0, dy = 0;

		// TODO move input out of the tick loop
		// toggles jesus mode (no clip)
		if (input.j.isPressed()) {
			jesusMode = !jesusMode;
			isSwimming = false;
			input.j.toggle(false);
		}

		// spawns a demon
		if (input.t.isPressed()) {
			if (!demonCooldown) {
				getLevel().addEntity(new Demon(getLevel(), "Demon", getX(), getY(), 1));
			}
			demonCooldown = true;
		}

		// shooting keys
		if (input.up.isPressed() || input.down.isPressed() || input.left.isPressed() || input.right.isPressed()) {

			isShooting = !isSwinging && !isSwimming && inventory.getGun() != null && !inventory.getGun().isReloading;

		}

		// swing key
		if (input.space.isPressed()) {
			if (!isShooting && !isSwimming && !isSwinging && inventory.getSword() != null) {
				inventory.getSword().swing();
				isSwinging = true;
			}
		}

		// displays names of all mobs
		if (input.h.isPressed()) {
			for (Mob m : getLevel().getMobs()) {
				if (!m.isDead())
					m.isTalking = true;
			}
		}

		// upwards movement
		if (input.w.isPressed()) {
			dy--;
		}

		// downwards movement
		if (input.s.isPressed()) {
			dy++;
		}

		// left movement
		if (input.a.isPressed()) {
			dx--;
		}

		// right movement
		if (input.d.isPressed()) {
			dx++;
		}

		// reload
		if (input.r.isPressed()) {
			if (inventory.getGun() != null) {
				inventory.getGun().reload();
				input.r.toggle(false);
			}
		}

		// toggle developer mode
		if (input.f3.isPressed()) {
			Game.setDisplayDevScreen(!Game.getDisplayDevScreen());
			input.f3.toggle(false);
		}

		// open inventory
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

		// open pause menu
		if (input.esc.isPressed()) {
			input.esc.toggle(false);
			if (Display.inGameScreen) {
				Display.displayPause();
			}
		}

		// action button
		if (input.e.isPressed()) {
			for (Entity entity : getLevel().getEntities()) {

				// enter a vehicle
				if (entity instanceof Vehicle) {

					Vehicle vehicle = (Vehicle) entity;

					// TODO check if vehicle is destroyed
					if (getOuterBounds().intersects(vehicle.getBounds())) {
						this.vehicle = vehicle;
						this.vehicle.addPlayer(this);
						moveTo(vehicle.getX(), vehicle.getY());
						vehicle.isUsed = true;
						input.e.toggle(false);
						return;
					}
				}

				// open a chest
				if (entity instanceof Chest && getOuterBounds().intersects(entity.getBounds())) {
					openChest((Chest) entity);
				}

				// talk to a mob
				if (entity instanceof Mob && entity != this) {

					// the other mob
					Mob other = (Mob) entity;
					if (getOuterBounds().intersects(other.getOuterBounds()) && !other.isDead()) {

						// TODO change from mob.speak to this.speak(other
						// mob)
						other.speak(this);
						input.e.toggle(false);
					}
				}
			}
		}

		// toggles chat window
		if (input.v.isPressed()) {
			ChatHandler.toggle();
			input.v.toggle(false);
		}

		// TODO repetitive
		if (inventory.getSword() != null) {
			isSwinging = inventory.getSword().isSwinging;
		}

		// determines if the player is going to move
		boolean isMoving = (dx != 0 || dy != 0);

		// play swimming sound
		if (isSwimming) {
			SoundHandler.playSmoothly(SoundHandler.swimming);

			// shift sprints, can't sprint in water
		} else if (input.shift.isPressed() && stamina > 0 && isMoving && !isShooting && !isSwinging) {

			// shift doubles the speed
			dx *= 2;
			dy *= 2;

			// reduce stamina
			stamina--;

		}

		// regenerate stamina when not moving
		if (!isMoving && stamina < maxStamina && !isShooting) {
			stamina += 0.5;
		}
		// regenerate shield when not moving
		if (!isMoving && shield < maxShield && !isShooting) {
			shield += (0.0005 * maxShield);
		}
		// regenerate stats very slowing if moving (but not sprinting)
		if (isMoving && !input.shift.isPressed()) {
			if (stamina < maxStamina)
				stamina += 0.1;
			if (shield < maxShield)
				shield += (0.00001 * maxShield);
		}

		// move the player
		if (isMoving) {
			if (jesusMode) {
				move(dx, dy, true);
				isSwimming = false;
			} else {
				move(dx, dy);
			}
			playTileSound();
		}

		// update the shooting directions if applicable
		if (input.up.isPressed()) {
			shootingDir = Direction.NORTH;
		}
		if (input.down.isPressed()) {
			shootingDir = Direction.SOUTH;
		}
		if (input.left.isPressed()) {
			shootingDir = Direction.WEST;
		}
		if (input.right.isPressed()) {
			shootingDir = Direction.EAST;
		}

		// TODO Look into this
		if (inventory.getGun() != null) {
			inventory.getGun().tick();
		}

		if (inventory.getSword() != null) {
			inventory.getSword().tick();
		}

		// spawn demon cooldown loop
		if (demonCooldown) {
			if (tickCount % 100 == 0) {
				demonCooldown = false;
			}
		}

		tickCount++;

	}

	/**
	 * Opens a chest
	 * 
	 * @param chest
	 *            the chest to open
	 */
	private void openChest(Chest chest) {

		// change the chest to be open
		chest.open();

		// get the contents
		for (Item e : chest.getContents()) {
			inventory.addItem(e);
			ChatHandler.displayText("You have obtained " + e, Color.GREEN);
		}
		InventoryGUI.update();
	}

	/**
	 * Processes the image of the player on the screen
	 */
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

		this.getBounds().setLocation(this.x - this.width / 2, this.y - this.height / 2);
		this.getOuterBounds().setLocation(this.x - this.width / 2 - 2, this.y - this.height / 2 - 2);

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
			screen.render(xOffset, yOffset + 3, 0 + 10 * sheet.boxes, waterColor, 0x00, 1, sheet);
			screen.render(xOffset + 8, yOffset + 3, 0 + 10 * sheet.boxes, waterColor, 0x01, 1, sheet);
		}

		// Normal Player movement -- Not Attacking Anything
		if (!isShooting && !isSwinging) {
			// Upper body 1
			screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile * sheet.boxes, color, flip, scale, sheet);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flip), yOffset, (xTile + 1) + yTile * sheet.boxes, color,
					flip, scale, sheet);

			if (!isSwimming) {
				// Lower Body 1
				screen.render(xOffset + (modifier * flip), yOffset + modifier, xTile + (yTile + 1) * sheet.boxes, color,
						flip, scale, sheet);
				// Lower Body 2
				screen.render(xOffset + modifier - (modifier * flip), yOffset + modifier,
						(xTile + 1) + (yTile + 1) * sheet.boxes, color, flip, scale, sheet);

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
			screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile * sheet.boxes, color, flip, scale, sheet);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flip), yOffset, (xTile + 1) + yTile * sheet.boxes, color,
					flip, scale, sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * flip), yOffset + modifier, xTile + (yTile + 1) * sheet.boxes, color,
					flip, scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flip), yOffset + modifier,
					(xTile + 1) + (yTile + 1) * sheet.boxes, color, flip, scale, sheet);

			int bulletOffset = -4;
			if (shootingDir == 2) {
				bulletOffset = -7;
			}

			if (stamina > 0 && gun != null) {
				gun.fire(level, this.x + bulletOffset, this.y - 2, shootingDir, this);
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
			JJFont.render(damageTaken, screen, (int) xOffset + isHitX, (int) yOffset - 10 + isHitY, isHitColor, 1);
		}

	}

	/**
	 * Play the appropriate sound for the tile
	 */
	public void playTileSound() {

		// get the current tile
		Tile currentTile = getLevel().getTile(getX() >> 3, getY() >> 3);

		// Grass sound
		if (currentTile == Tile.GRASS) {
			SoundHandler.playSmoothly(SoundHandler.footstepsGrass);

			// Mud sound
		} else if (currentTile == Tile.MUD) {
			SoundHandler.playSmoothly(SoundHandler.footstepsWood);

			// Road sounds
		} else if (currentTile == Tile.ROAD1 || currentTile == Tile.ROAD2 || currentTile == Tile.ROAD3) {
			SoundHandler.playSmoothly(SoundHandler.footstepsRoad);

			// Dirt road sound
		} else if (currentTile == Tile.DIRTROAD) {
			SoundHandler.playSmoothly(SoundHandler.footstepsDirt);
		}

	}

	/**
	 * Sets the hair color
	 * 
	 * @param num
	 *            the hair color in hexadecimal
	 */
	public void setHairColor(int num) {
		color[0] = num;
	}

	/**
	 * Sets the shirt color
	 * 
	 * @param num
	 *            the shirt color in hexadecimal
	 */
	public void setShirtColor(int num) {
		color[1] = num;
	}

	/**
	 * Sets the skin color
	 * 
	 * @param num
	 *            the skin color in hexadecimal
	 */
	public void setSkinColor(int num) {
		color[2] = num;
	}
	
	/**
	 * Player uses shield and has defense unlike other mobs
	 * @param damage the value of damage
	 */
	@Override
	protected void doDamageToHealth(int damage) {
		
		// use shield to deflect damage
		while (shield > 0 && damage > 0) {
			shield--;
			damage--;
		}
		if (shield < 0) {
			shield = 0;
		}
		damage -= getDefense();
		if (damage <= 0) {
			damage = 0;
		}
		
		super.doDamageToHealth(damage);
	}

	/**
	 * Give developers special perks that make them invincible
	 */
	public void grantDevPowers() {
		this.strength = 100;
		this.maxStamina = Integer.MAX_VALUE;
		this.stamina = maxStamina;
		this.defense = 10;
		setMaxHealth(Integer.MAX_VALUE);
		heal();
		this.maxShield = 1000;
		this.shield = maxShield;
		inventory.addItem(Item.blackHoleGun);
		inventory.addItem(Item.bazooka);
	}

	/**
	 * @return The player's colorset
	 */
	public int[] getColor() {
		return color;
	}

	@Override
	public int getStrength() {
		return strength;
	}

	@Override
	public int getDefense() {
		// TODO armor defense (will be static)
		return defense;
	}

	@Override
	public int getAccuracy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEvasion() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return whether or not the player is driving
	 */
	public boolean isDriving() {
		return vehicle != null;
	}
	
	/**
	 * @return The vehicle the player is driving
	 * Null if the player is not riding anything
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	/**
	 * @return The player's inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	public double getCurrentStamina() {
		return stamina;
	}
	
	//public double get

}
