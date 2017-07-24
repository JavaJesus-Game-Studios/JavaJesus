package javajesus.entities;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import engine.Window;
import javajesus.MessageHandler;
import javajesus.SoundHandler;
import javajesus.entities.monsters.Demon;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.MapTransporter;
import javajesus.entities.transporters.Transporter;
import javajesus.entities.vehicles.Ridable;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.items.Armor;
import javajesus.items.Bazooka;
import javajesus.items.Gun;
import javajesus.items.Inventory;
import javajesus.items.Item;
import javajesus.items.Sword;
import javajesus.level.Level;
import javajesus.level.tile.Tile;
import javajesus.quests.Quest;
import javajesus.utility.Direction;

/*
 * The Player is a mob that is directly controlled by a user
 * It interacts with the world through keyboard input
 */
public class Player extends Mob {

	// player color set: hair, shirt, skin
	private final int[] color = { 0xFF010101, 0xFFFF0000, 0xFFFFCC99 };

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

	// the spritesheet to use when the player is shooting
	private SpriteSheet gunSheet = SpriteSheet.playerGuns;

	// the size of the sprite
	private static final int SIZE = 16;

	// the starting health value
	private static final int START_HEALTH = 100;

	// the starting stamina value
	private static final int START_STAMINA = 200;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;
	
	// damage range for swords
	public static final int DAMAGE_RANGE = 10;

	// determines if a player is moving in any direction
	private boolean isMoving;
	
	// currently equipped Gun
	private Gun equippedGun;
	
	// currently equipped Sword
	private Sword equippedSword;
	
	// currently equipped Armor
	private Armor equippedArmor;

	// player stats
	private int strength, defense;
	
	// whether or not the player is sprinting
	private boolean isSprinting;
	
	// directions the player is moving
	private boolean movingUp, movingDown, movingLeft, movingRight;
	
	/**
	 * Creates a new player for the game
	 * 
	 * @param level the initial level
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Player(String name, Level level, int x, int y) {
		super(level, name, x, y, 1, SIZE, SIZE, SpriteSheet.player, START_HEALTH);

		inventory = new Inventory();
		maxStamina = START_STAMINA;
		stamina = maxStamina;
		
		// gives certain names certain powers
		if (name.equals("Derek Jow") || name.equals("Stephen Northway")) {
			grantDevPowers();
			System.err.println("Creating Developer");
		}  else {
			System.err.println("Creating " + name);
		}
		
	}
	
	/**
	 * Equips Armor/Sword/Gun to the player
	 * 
	 * @param obj - item to equip
	 */
	public void equip(Object obj) {
		
		// when switching items, player can't be swinging or shooting
		isSwinging = isShooting = false;

		// equip the object
		if (obj instanceof Gun) {
			equippedGun = (Gun) obj;
			equippedSword = null;
			strength = 0;	// damage based on gun
		} else if (obj instanceof Sword) {
			equippedSword = (Sword) obj;
			equippedGun = null;
			strength = equippedSword.getStrength();
		} else if (obj instanceof Armor) {
			equippedArmor = (Armor) obj;
			defense = equippedArmor.getDefense();
		}
		
	}
	
	/**
	 * UnEquips Armor/Sword/Gun to the player
	 * 
	 * @param obj - item to unequip
	 */
	public void unEquip(Object obj) {

		if (obj instanceof Gun) {
			equippedGun = null;
		} else if (obj instanceof Sword) {
			equippedSword = null;
		} else if (obj instanceof Armor) {
			equippedArmor = null;
		}
		
	}

	/**
	 * Transitions the player from one level to another
	 */
	public synchronized void updateLevel(Level level) {

		// play the click sound
		SoundHandler.play(SoundHandler.click);

		// TODO loop the new background music if applicable
		SoundHandler.playLoop(SoundHandler.background1);

		getLevel().remove(this);

		// clears all the dead mobs on the last level
		getLevel().clear();

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
		if (equippedGun != null) {
			equippedGun.tick();
		}

		// update the sword
		if (equippedSword != null) {
			equippedSword.tick(getLevel(), getX(), getY(), this);
			// sdsetDirection(equippedSword.getDirection());
		}
		
		// check for pickups
		for (int i = 0; i < getLevel().getEntities().size(); i++) {
			if (getLevel().getEntities().get(i) instanceof Pickup
			        && getBounds().intersects(getLevel().getEntities().get(i).getBounds())) {
				
				// instance of the pickup
				Pickup p = (Pickup) getLevel().getEntities().get(i);
				
				// should we use it on collision?
				if (p.isInstantaneous()) {
					
					// use the item
					p.getItem().use(this);
					
					// now remove it
					p.remove();
					
				} else {
					
					// add the pickup item to the inventory
					inventory.add(p);
				}
				
				// the pickup was removed from the list so adjust index
				i--;
			}
		}

		// the change in x and y (movement)
		int dx = 0, dy = 0;
		
		// upwards movement
		if (movingUp) {
			dy--;
		}

		// downwards movement
		if (movingDown) {
			dy++;
		}

		// left movement
		if (movingLeft) {
			dx--;
		}

		// right movement
		if (movingRight) {
			dx++;
		}

		// change offsets if wearing armor
		if (equippedArmor != null) {
			this.yTile = equippedArmor.getRow();
			this.gunSheet = equippedArmor.getGunSpritesheet();
		} else {
			this.yTile = 0;
			this.gunSheet = SpriteSheet.playerGuns;
		}


		// sets the status of the sword
		if (equippedSword != null) {
			isSwinging = equippedSword.isSwinging();
		}

		// determines if the player is going to move
		isMoving = (dx != 0 || dy != 0);

		// play swimming sound
		if (isSwimming) {
			SoundHandler.playSmoothly(SoundHandler.swimming);

			// shift sprints, can't sprint in water
		} else if (isSprinting && stamina > 0 && isMoving
				&& !isShooting && !isSwinging) {

			// shift doubles the speed
			dx *= 2;
			dy *= 2;

			// make a faster step animation
			numSteps++;

			// reduce stamina
			stamina--;

		}

		// regenerate stamina when not moving
		if (!isMoving && stamina < maxStamina && !isShooting && !isSwinging) {
			stamina += 0.5;
		}

		// regenerate stats very slowing if moving (but not sprinting)
		if (isMoving && !isSprinting) {
			if (stamina < maxStamina)
				stamina += 0.1;
		}

		// move the player
		if (isMoving) {
			move(dx, dy);
			playTileSound();
		}

		tickCount++;

	}

	/**
	 * Opens a chest
	 * 
	 * @param chest - the chest to open
	 */
	private void openChest(Chest chest) {
		
		// checks if chest can be opened
		if (chest.open()) {

			// get the contents
			for (Item e : chest.getContents()) {
				inventory.add(e);
				MessageHandler.displayText("You have obtained " + e, Color.GREEN);
			}
		}
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
		} else {
			xTile = 4;
			if (isMoving)
				xTile += (flip ? 2 : 0);
			flip = getDirection() == Direction.WEST;
		}
		
		// death texture
		if (isDead()) {
			xTile = 12;
		}

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// Normal Player movement -- Not Attacking Anything
		if (!isShooting && !isSwinging) {

			int swimOffset = modifier * (isSwimming ? 1 : 0);

			// Upper body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset
					+ swimOffset, xTile, yTile, getSpriteSheet(), flip,
					color);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)),
					yOffset + swimOffset, xTile + 1, yTile, getSpriteSheet(), flip, color);

			if (!isSwimming) {
				// Lower Body 1
				screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile, yTile + 1,
				        getSpriteSheet(), flip, color);
				// Lower Body 2
				screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile + 1,
				        yTile + 1, getSpriteSheet(), flip, color);

			}
		}

		// Handles Shooting Animation
		if (isShooting) {

			// bazooka is special :)
			if (equippedGun instanceof Bazooka) {
				((Bazooka) equippedGun).renderPlayer(screen, this,
						gunSheet, shootingDir);
				return;
			}

			// tile positions for player
			xTile = 0;
			yTile = equippedGun.getPlayerOffset();

			if (shootingDir == Direction.NORTH) {
				xTile = 8;
				if (equippedGun == Item.assaultRifle) {
					xTile = 10;
				}
				if (!isMoving) {
					xTile = 10;
					if (equippedGun == Item.assaultRifle) {
						xTile = 12;
					}
				}
			} else if (shootingDir == Direction.SOUTH) {
				xTile = 4;
				if (equippedGun == Item.assaultRifle) {
					xTile += flip ? 2 : 0;
					flip = false;
				}
				if (!isMoving) {
					xTile = 6;
					if (equippedGun == Item.assaultRifle) {
						xTile = 8;
					}
				}
			} else {
				// reset the flip for horizontal movement
				flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;
				xTile = flip ? 2 : 0;
				if (!isMoving)
					xTile = 2;
				flip = shootingDir == Direction.WEST;
			}

			SpriteSheet sheet = this.gunSheet;

			// Upper Body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile, yTile, sheet, flip, color);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset, xTile + 1, yTile, sheet, flip,
			        color);

			// Lower Body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile, yTile + 1, sheet, flip,
			        color);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile + 1, yTile + 1,
			        sheet, flip, color);

		}
		// Handles Swinging Animation
		if (isSwinging) {
			equippedSword.render(screen, xOffset, yOffset, getColor());
			setDirection(equippedSword.getDirection());
		}

	}
	
	/**
	 * Renders sword collision bounds to screen
	 * 
	 * @param screen - screen to render to
	 */
	public void renderSwordCollision(Screen screen) {
		if (isSwinging) {
			equippedSword.renderBounds(screen);
		}
	}

	/**
	 * Makes a player exit vehicle
	 */
	public void exitVehicle() {
		getBounds().setSize(SIZE, SIZE);
		moveTo(vehicle.getX() - SIZE, vehicle.getY());
		vehicle = null;
	}

	/**
	 * Play the appropriate sound for the tile
	 */
	public void playTileSound() {

		// get the current tile
		Tile currentTile = getLevel().getTile(getX() >> 3, getY() >> 3);

		// Grass sound
		if (currentTile == Tile.GRASS0) {
			SoundHandler.playSmoothly(SoundHandler.footstepsGrass);

			// Mud sound
		} else if (currentTile == Tile.MUD) {
			SoundHandler.playSmoothly(SoundHandler.footstepsWood);

			// Road sounds
		} else if (currentTile == Tile.CAR_ROAD || currentTile == Tile.SIDEWALK_MID) {
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
	 * Give developers special perks that make them invincible
	 */
	public void grantDevPowers() {
		this.strength = 100;
		this.maxStamina = Short.MAX_VALUE;
		this.stamina = maxStamina;
		this.defense = 100;
		setMaxHealth(Short.MAX_VALUE);
		heal(-1);
		inventory.add(Item.blackHoleGun);
		inventory.add(Item.bazooka);
	}

	/**
	 * Must be called after loading a save file because sounds are not saved
	 */
	public void initSound() {
		equippedGun.initSound();
	}

	/**
	 * @return The player's colorset
	 */
	public int[] getColor() {
		return color;
	}

	/**
	 * @return strength of the player
	 */
	@Override
	public int getStrength() {
		return strength;
	}

	/**
	 * @return defense of the player
	 */
	@Override
	public int getDefense() {
		return defense;
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
	
	/**
	 * If the player dies, then stop the game
	 */
	@Override
	public void remove() {
		super.remove();
		
		// reset data fields
		isSwinging = isShooting = isMoving = false;
	}

	/**
	 * Handles input for the player
	 * 
	 * @param window - window to check for input
	 */
	public void input(Window window) {
		
		if (vehicle != null) {
			vehicle.input(window);
			return;
		}
		
		isSprinting = window.isKeyPressed(KeyEvent.VK_SHIFT);
		
		// toggles jesus mode (no clip)
		if (window.isKeyPressed(KeyEvent.VK_J)) {
			toggleClip();
			isSwimming = false;
			window.toggle(KeyEvent.VK_J);
		}

		// spawns a demon
		if (window.isKeyPressed(KeyEvent.VK_T)) {
			getLevel().add(new Demon(getLevel(), getX(), getY(), 1, 100));
			window.toggle(KeyEvent.VK_T);
		}

		// shooting requirements
		isShooting = (window.isKeyPressed(KeyEvent.VK_UP) || window.isKeyPressed(KeyEvent.VK_DOWN)
				|| window.isKeyPressed(KeyEvent.VK_LEFT) || window.isKeyPressed(KeyEvent.VK_RIGHT))
				&& !isSwinging
				&& !isSwimming
				&& equippedGun != null
				&& !equippedGun.isReloading();

		// update the shooting directions if applicable
		if (window.isKeyPressed(KeyEvent.VK_UP)) {
			shootingDir = Direction.NORTH;
			if (isShooting) {
				equippedGun.fire(getX() + 7, getY(), shootingDir, this);
			}
		}
		if (window.isKeyPressed(KeyEvent.VK_DOWN)) {
			shootingDir = Direction.SOUTH;
			if (isShooting) {
				equippedGun.fire(getX() + 7, getY() + 8, shootingDir,
						this);
			}
		}
		if (window.isKeyPressed(KeyEvent.VK_LEFT)) {
			shootingDir = Direction.WEST;
			if (isShooting) {
				equippedGun.fire(getX(), getY() + 6, shootingDir, this);
			}
		}
		if (window.isKeyPressed(KeyEvent.VK_RIGHT)) {
			shootingDir = Direction.EAST;
			if (isShooting) {
				equippedGun.fire(getX() + 8, getY() + 6, shootingDir,
						this);
			}
		}

		// swing key
		if (window.isKeyPressed(KeyEvent.VK_SPACE)) {
			if (!isShooting && !isSwimming && !isSwinging
					&& equippedSword != null) {

				if (isSprinting && stamina > 20) {
					stamina -= 20;
					equippedSword.swing(getLevel(), getX(), getY(),
							getDirection(), true, this);
				} else {
					equippedSword.swing(getLevel(), getX(), getY(),
							getDirection(), false, this);
				}
			}
		}

		// displays names of all mobs
		if (window.isKeyPressed(KeyEvent.VK_H)) {
			for (Mob m : getLevel().getMobs()) {
				if (!m.isDead())
					m.isTalking = true;
			}
			window.toggle(KeyEvent.VK_H);
		}

		// movement
		movingUp = window.isKeyPressed(KeyEvent.VK_W);
		movingDown = window.isKeyPressed(KeyEvent.VK_S);
		movingLeft = window.isKeyPressed(KeyEvent.VK_A);
		movingRight = window.isKeyPressed(KeyEvent.VK_D);
		
		// reload
		if (window.isKeyPressed(KeyEvent.VK_R)) {
			if (equippedGun != null && !equippedGun.isReloading() && equippedGun.getAmmo() != null) {

				inventory.remove(equippedGun.getAmmo(),
				        equippedGun.reload(inventory.getAmmoAmount(equippedGun.getAmmo())));

			}
			window.toggle(KeyEvent.VK_R);
		}

		// action button
		if (window.isKeyPressed(KeyEvent.VK_E)) {
			window.toggle(KeyEvent.VK_E);
			for (int i = 0; i < getLevel().getEntities().size(); i++) {
				Entity entity = getLevel().getEntities().get(i);
				// enter a vehicle
				if (entity instanceof Ridable) {

					Ridable vehicle = (Ridable) entity;

					// TODO check if vehicle is destroyed
					if (getOuterBounds().intersects(vehicle.getBounds())) {
						this.vehicle = vehicle;
						moveTo(vehicle.getX(), vehicle.getY());
						vehicle.drive(this);
						// hide player bounds
						getBounds().setSize(0, 0);
						return;
					}
				}

				// open a chest
				if (entity instanceof Chest
						&& getOuterBounds().intersects(entity.getBounds())) {
					openChest((Chest) entity);
					break;
				}

				// talk to a mob
				if (entity instanceof Mob && entity != this) {

					// the other mob
					Mob other = (Mob) entity;
					if (getOuterBounds().intersects(other.getOuterBounds())
							&& !other.isDead()) {

						// talk to that mob
						other.speak(this);
						break;
					}
				}

				// handles transporters
				if (entity instanceof Transporter
						&& getBounds().intersects(entity.getBounds())) {

					getLevel().setSpawnPoint(getX(), getY());

					if (entity instanceof MapTransporter) {
						((MapTransporter) entity).calcNewSpawn(this);
					}

					updateLevel(((Transporter) entity).getNextLevel());
					break;
				}
			}
		}

		// toggles chat window
		if (window.isKeyPressed(KeyEvent.VK_V)) {
			MessageHandler.toggle();
			window.toggle(KeyEvent.VK_V);
		}
		
	}
	
	/**
	 * @return the color of the shirt
	 */
	public int getShirtColor() {
		return color[1];
	}
	
	/**
	 * @return the color of the skin
	 */
	public int getSkinColor() {
		return color[2];
	}

	/**
	 * @return the equipped gun
	 */
	public Gun getEquippedGun() {
		return equippedGun;
	}
	
	/**
	 * @return the equipped sword
	 */
	public Sword getEquippedSword() {
		return equippedSword;
	}

	@Override
	public byte getId() {
		return Entity.PLAYER;
	}

}
