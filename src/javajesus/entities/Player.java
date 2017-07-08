package javajesus.entities;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import engine.Window;
import javajesus.JavaJesus;
import javajesus.MessageHandler;
import javajesus.SoundHandler;
import javajesus.entities.monsters.Demon;
import javajesus.entities.structures.furniture.Chest;
import javajesus.entities.structures.transporters.MapTransporter;
import javajesus.entities.structures.transporters.Transporter;
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
public class Player extends Mob implements Skills {

	private static final long serialVersionUID = -4170571410784465465L;

	// player color set: hair, shirt, skin
	private final int[] color = { 0xFF343434, 0xFFFF0000, 0xFFFFCC99 };

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
	
	// currently equipped Gun
	private Gun equippedGun;
	
	// currently equipped Sword
	private Sword equippedSword;
	
	// currently equipped Armor
	private Armor equippedArmor;

	// player stats
	private int strength, defense;
	
	private boolean isSprinting;
	
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
		
		System.err.println("Creating Player");

	}
	
	/**
	 * Equips Armor/Sword/Gun to the player
	 * 
	 * @param obj - item to equip
	 */
	public void equip(Object obj) {

		// equip the object
		if (obj instanceof Gun) {
			equippedGun = (Gun) obj;
			equippedSword = null;
		} else if (obj instanceof Sword) {
			equippedSword = (Sword) obj;
			equippedGun = null;
		} else if (obj instanceof Armor) {
			equippedArmor = (Armor) obj;
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
		if (equippedGun != null) {
			equippedGun.tick();
		}

		// update the sword
		if (equippedSword != null) {
			equippedSword.tick(getLevel(), getX(), getY());
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
			this.maxShield = equippedArmor.getShield();
			this.gunSheet = equippedArmor.getGunSpritesheet();
		} else {
			this.yTile = 0;
			this.maxShield = 1;
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
		// regenerate shield when not moving
		if (!isMoving && shield < maxShield && !isShooting && !isSwinging) {
			shield += (0.0005 * maxShield);
		}
		// regenerate stats very slowing if moving (but not sprinting)
		if (isMoving && !isSprinting) {
			if (stamina < maxStamina)
				stamina += 0.1;
			if (shield < maxShield)
				shield += (0.00001 * maxShield);
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

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE * getScale();

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// Normal Player movement -- Not Attacking Anything
		if (!isShooting && !isSwinging) {

			int swimOffset = modifier * (isSwimming ? 1 : 0);

			// Upper body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset
					+ swimOffset, xTile + yTile * getSpriteSheet().getTilesPerRow(),
					color, flip, getScale(), getSpriteSheet());
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)),
					yOffset + swimOffset, (xTile + 1) + yTile
							* getSpriteSheet().getTilesPerRow(), color, flip, getScale(),
					getSpriteSheet());

			if (!isSwimming) {
				// Lower Body 1
				screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset
						+ modifier, xTile + (yTile + 1)
						* getSpriteSheet().getTilesPerRow(), color, flip, getScale(),
						getSpriteSheet());
				// Lower Body 2
				screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)),
						yOffset + modifier, (xTile + 1) + (yTile + 1)
								* getSpriteSheet().getTilesPerRow(), color, flip,
						getScale(), getSpriteSheet());

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
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile
					+ yTile * sheet.getTilesPerRow(), color, flip, getScale(), sheet);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)),
					yOffset, (xTile + 1) + yTile * sheet.getTilesPerRow(), color, flip,
					getScale(), sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset
					+ modifier, xTile + (yTile + 1) * sheet.getTilesPerRow(), color, flip,
					getScale(), sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)),
					yOffset + modifier,
					(xTile + 1) + (yTile + 1) * sheet.getTilesPerRow(), color, flip,
					getScale(), sheet);

		}
		// Handles Swinging Animation
		if (isSwinging) {
			equippedSword.render(screen, xOffset, yOffset, getColor());
			setDirection(equippedSword.getDirection());
		}

	}

	/**
	 * Makes a player exit vehicle
	 */
	public void exitVehicle() {
		moveTo(vehicle.getX() - 16, vehicle.getY());
		vehicle = null;
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
		} else if (currentTile == Tile.ROAD1 || currentTile == Tile.ROAD2
				|| currentTile == Tile.ROAD3) {
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
		return shield;
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
		
		JavaJesus.playerDied();
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
							getDirection(), true);
				} else {
					equippedSword.swing(getLevel(), getX(), getY(),
							getDirection(), false);
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
			if (equippedGun != null) {
				equippedGun.reload(100); // TODO
			}
			window.toggle(KeyEvent.VK_R);
		}

		// open inventory
		if (window.isKeyPressed(KeyEvent.VK_I)) {
			window.toggle(KeyEvent.VK_I);
			if (JavaJesus.inGameScreen) {
				JavaJesus.displayOverview();
				window.disable(KeyEvent.VK_W);
				window.disable(KeyEvent.VK_A);
				window.disable(KeyEvent.VK_S);
				window.disable(KeyEvent.VK_D);
				isSprinting = false;
			}

		}

		// open pause menu
		if (window.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			window.toggle(KeyEvent.VK_ESCAPE);
			if (JavaJesus.inGameScreen) {
				JavaJesus.displayPause();
			}
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

						// TODO change from mob.speak to this.speak(other
						// mob)
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

}
