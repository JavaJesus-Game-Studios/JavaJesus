package game.entities;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import game.ChatHandler;
import game.Display;
import game.Game;
import game.InputHandler;
import game.SoundHandler;
import game.entities.monsters.Demon;
import game.entities.structures.furniture.Chest;
import game.entities.structures.transporters.MapTransporter;
import game.entities.structures.transporters.Transporter;
import game.entities.vehicles.Ridable;
import game.graphics.Colors;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import game.gui.overview.InventoryGUI;
import items.Armor;
import items.Bazooka;
import items.Inventory;
import items.Item;
import level.Level;
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
	private Ridable vehicle;

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
	@SuppressWarnings("unused")
	private ArrayList<Quest> activeQuests = new ArrayList<Quest>();

	// List of completed quests
	@SuppressWarnings("unused")
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

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	// determines if a player is moving in any direction
	private boolean isMoving;

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

		inventory = new Inventory();
		maxStamina = START_STAMINA;
		stamina = maxStamina;

	}

	// TODO will change how this works
	public void setInput(InputHandler input) {
		this.input = input;
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

		getLevel().remove(this);

		// TODO temporary fix
		if (isOnFire()) {
			setOnFire(false);
		}

		// clears all the dead mobs on the last level
		getLevel().clear();

		// load the new level if it has not been loaded yet
		if (!level.isLoaded()) {
			level.load();
		}

		// change the global level variable
		super.updateLevel(level);

		// adds the player to the new level
		level.add(this);

		// where the player should go
		Point location = level.getSpawnPoint();

		// go to the spawn location for that level
		moveTo(location.x, location.y);
	}

	/**
	 * Internal tick clock that updates the player
	 */
	public void tick() {

		// do not update if driving
		if (vehicle != null) {
			moveTo(vehicle.getX(), vehicle.getY());
			return;
		}

		// do basic checks
		super.tick();

		// update the gun
		if (inventory.getGun() != null) {
			inventory.getGun().tick();
		}

		// update the sword
		if (inventory.getSword() != null) {
			inventory.getSword().tick(getLevel(), getX(), getY());
			setDirection(inventory.getSword().getDirection());
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
				getLevel().add(new Demon(getLevel(), getX(), getY(), 1, 100));
			}
			demonCooldown = true;
		}

		// shooting keys
		if (input.up.isPressed() || input.down.isPressed() || input.left.isPressed() || input.right.isPressed()) {

			if (isShooting = !isSwinging && !isSwimming && inventory.getGun() != null
					&& !inventory.getGun().isReloading()) {

				// horizontal bullet offset
				int bulletOffset = shootingDir == Direction.EAST ? 2 * UNIT_SIZE + 1 : -1;

				// fire the gun
				if (inventory.getGun() != null) {
					inventory.getGun().fire(getX() + bulletOffset, getY() + 3, shootingDir, this);
				}
			}

		}

		// swing key
		if (input.space.isPressed()) {
			if (!isShooting && !isSwimming && !isSwinging && inventory.getSword() != null) {

				if (input.shift.isPressed() && stamina > 20) {
					stamina -= 20;
					inventory.getSword().swing(getLevel(), getX(), getY(), getDirection(), true);
				} else {
					inventory.getSword().swing(getLevel(), getX(), getY(), getDirection(), false);
				}
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
				inventory.getGun().reload(inventory);
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
			input.e.toggle(false);
			for (Entity entity : getLevel().getEntities()) {

				// enter a vehicle
				if (entity instanceof Ridable) {

					Ridable vehicle = (Ridable) entity;

					// TODO check if vehicle is destroyed
					if (getOuterBounds().intersects(vehicle.getBounds())) {
						this.vehicle = vehicle;
						moveTo(vehicle.getX(), vehicle.getY());
						vehicle.drive(this);
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
					}
				}

				// handles transporters
				if (entity instanceof Transporter && getBounds().intersects(entity.getBounds())) {

					getLevel().setSpawnPoint(getX(), getY());

					if (entity instanceof MapTransporter) {
						((MapTransporter) entity).calcNewSpawn(this);
					}

					updateLevel(((Transporter) entity).getNextLevel());
				}
			}
		}

		// toggles chat window
		if (input.v.isPressed()) {
			ChatHandler.toggle();
			input.v.toggle(false);
		}

		// sets the status of the sword
		if (inventory.getSword() != null) {
			isSwinging = inventory.getSword().isSwinging();
		}

		// determines if the player is going to move
		isMoving = (dx != 0 || dy != 0);

		// play swimming sound
		if (isSwimming) {
			SoundHandler.playSmoothly(SoundHandler.swimming);

			// shift sprints, can't sprint in water
		} else if (input.shift.isPressed() && stamina > 0 && isMoving && !isShooting && !isSwinging) {

			// shift doubles the speed
			dx *= 2;
			dy *= 2;

			// make a faster step animation
			numSteps++;

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
			if (isShooting) {
				inventory.getGun().fire(getX() + 4, getY(), shootingDir, this);
			}
		}
		if (input.down.isPressed()) {
			shootingDir = Direction.SOUTH;
			if (isShooting) {
				inventory.getGun().fire(getX() + 4, getY() + 8, shootingDir, this);
			}
		}
		if (input.left.isPressed()) {
			shootingDir = Direction.WEST;
			if (isShooting) {
				inventory.getGun().fire(getX(), getY() + 4, shootingDir, this);
			}
		}
		if (input.right.isPressed()) {
			shootingDir = Direction.EAST;
			if (isShooting) {
				inventory.getGun().fire(getX() + 8, getY() + 4, shootingDir, this);
			}
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
			inventory.add(e);
			ChatHandler.displayText("You have obtained " + e, Color.GREEN);
		}
		InventoryGUI.update();
	}

	/**
	 * Processes the image of the player on the screen
	 */
	public void render(Screen screen) {

		// don't render if driving
		if (vehicle != null) {
			return;
		}

		// do basic rendering
		super.render(screen);

		// x and y tile on spritesheet
		int xTile = 0, yTile = this.yTile;

		// whether or not to flip horizontally (walking animation)
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// north direction
		if (getDirection() == Direction.NORTH) {
			xTile = 10;
			if (!isMoving) {
				xTile = 8;
			}
			// south direction
		} else if (getDirection() == Direction.SOUTH) {
			xTile = 2;
			if (!isMoving) {
				xTile = 0;
			}
			// left or right
		} else if (isLatitudinal()) {
			xTile = 4;
			if (isMoving)
				xTile += 2;
			flip = getDirection() == Direction.WEST;
		}

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE * getScale();

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// Normal Player movement -- Not Attacking Anything
		if (!isShooting && !isSwinging) {
			// Upper body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile + yTile * getSpriteSheet().boxes, color,
					flip, getScale(), getSpriteSheet());
			// Upper Body 2
			screen.render(xOffset + modifier - (flip ? 1 : 0), yOffset, (xTile + 1) + yTile * getSpriteSheet().boxes,
					color, flip, getScale(), getSpriteSheet());

			if (!isSwimming) {
				// Lower Body 1
				screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier,
						xTile + (yTile + 1) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());
				// Lower Body 2
				screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier,
						(xTile + 1) + (yTile + 1) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

			}
		}

		// Handles Shooting Animation
		if (isShooting) {

			// bazooka is special :)
			if (inventory.getGun() instanceof Bazooka) {
				((Bazooka) inventory.getGun()).renderPlayer(screen, this);
				return;
			}

			// tile positions for player
			xTile = 0;
			yTile = inventory.getGun().getPlayerOffset();

			if (getDirection() == Direction.NORTH) {
				xTile = 8 + (flip ? 2 : 0);
				if (!isMoving)
					xTile = 10;
			}
			if (getDirection() == Direction.SOUTH) {
				xTile = 4 + (flip ? 2 : 0);
				if (!isMoving)
					xTile = 6;
			} else if (isLatitudinal()) {
				xTile = flip ? 2 : 0;
				if (!isMoving)
					xTile = 2;
				flip = getDirection() == Direction.WEST;
			}

			SpriteSheet sheet = this.gunSheet;

			// Upper Body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile + yTile * sheet.boxes, color, flip,
					getScale(), sheet);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset, (xTile + 1) + yTile * sheet.boxes,
					color, flip, getScale(), sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile + (yTile + 1) * sheet.boxes,
					color, flip, getScale(), sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier,
					(xTile + 1) + (yTile + 1) * sheet.boxes, color, flip, getScale(), sheet);

		}
		// Handles Swinging Animation
		if (isSwinging) {
			inventory.getSword().render(screen, xOffset, yOffset, getColor());
		}

	}

	/**
	 * Makes a player exit vehicle
	 */
	public void exitVehicle() {
		vehicle = null;
		move(-8, 0);
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
	 * 
	 * @param damage
	 *            the value of damage
	 * 
	 *            TODO set shader to 16711680 when health < 20
	 */
	@Override
	protected void doDamageToHealth(int damage) {

		// use shield to absorb damage
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

		// sets a shader when health is low
		if ((double) getCurrentHealth() / getMaxHealth() <= 0.25) {
			Display.getScreen().setShader(Colors.fromHex("ff0000"));
		}
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
		inventory.add(Item.blackHoleGun);
		inventory.add(Item.bazooka);
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
	 * @return The vehicle the player is driving Null if the player is not
	 *         riding anything
	 */
	public Ridable getVehicle() {
		return vehicle;
	}

	/**
	 * @return The player's inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * @return the current stamina
	 */
	public double getCurrentStamina() {
		return stamina;
	}

	/**
	 * @return the max stamina
	 */
	public double getMaxStamina() {
		return maxStamina;
	}

	/**
	 * @return the current shield
	 */
	public double getCurrentShield() {
		return stamina;
	}

	/**
	 * @return the max shield
	 */
	public double getMaxShield() {
		return maxShield;
	}

	/**
	 * @return The player's active quest
	 */
	public ArrayList<Quest> getActiveQuests() {
		return this.getActiveQuests();
	}

	/**
	 * @return The player's completed quests
	 */
	public ArrayList<Quest> getCompletedQuests() {
		return this.getCompletedQuests();
	}

	/**
	 * Replenishes the health to full
	 */
	public void heal() {
		super.heal();

		// resets the shader to default
		Display.getScreen().setShader(0);
	}

	/**
	 * TODO Temporary method to workaround input
	 * 
	 * @return The inputhandler
	 */
	public InputHandler getInput() {
		return input;
	}

	/**
	 * @return the number of steps the player has taken
	 */
	public int getNumSteps() {
		return numSteps;
	}

	/**
	 * @return true if the player is moving
	 */
	public boolean isMoving() {
		return isMoving;
	}

}
