package javajesus.entities;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import engine.Window;
import javajesus.MessageHandler;
import javajesus.SoundHandler;
import javajesus.dataIO.PlayerData;
import javajesus.entities.monsters.Skeleton;
import javajesus.entities.transporters.Transporter;
import javajesus.entities.vehicles.Ridable;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.items.Armor;
import javajesus.items.DifferentOffsetItem;
import javajesus.items.Gun;
import javajesus.items.Inventory;
import javajesus.items.Item;
import javajesus.items.Sword;
import javajesus.level.Level;
import javajesus.level.tile.Tile;
import javajesus.quest.Quest;
import javajesus.utility.Direction;

/*
 * The Player is a mob that is directly controlled by a user
 * It interacts with the world through keyboard input
 */
public class Player extends Mob implements Type {

	// player color set: outline, shirt, skin, hair, pants
	private final int[] color = { 0xFF000001, 0xFFFF0000, 0xFFFFCC99, 0xFF343434, 0xFF343434};

	// the vehicle the player is in, null if not driving
	private Ridable vehicle;

	// direction the player is shooting
	private Direction shootingDir;

	// the y tile on the spritesheet to render
	private int yTile;

	// the inventory of the player
	private Inventory inventory;

	// the current stamina, influences special swings and running
	private float stamina;

	// the max stamina
	private float maxStamina;

	// List of the active quests
	private ArrayList<Quest> activeQuests = new ArrayList<Quest>();

	// List of completed quests
	private ArrayList<Quest> completedQuests = new ArrayList<Quest>();

	// the spritesheet to use when the player is shooting
	private SpriteSheet gunSheet = SpriteSheet.playerGuns_male_noarmor;
	
	// the spritesheet to use when the player has a sword equipped
	private SpriteSheet swordSheet = SpriteSheet.playerSwords_male_noarmor;

	// the size of the sprite
	private static final int SIZE = 16;

	// the starting health value
	private static final int START_HEALTH = 150;

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
	
	private Object lastEquip;

	// player stats
	private int strength, defense;
	
	//determines if a player is blocking
	private boolean isBlocking;
	
	//determines if player is facing a longitudinal direction
	private boolean isLongitudinal;
	
	// whether or not the player is sprinting
	private boolean isSprinting;
	
	// directions the player is moving
	private boolean movingUp, movingDown, movingLeft, movingRight;
	
	// the gender of the player
	private byte gender;
	
	/**
	 * Creates a new player for the game
	 * 
	 * @param level the initial level
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param gender - PlayerData.MALE/FEMALE
	 */
	public Player(String name, Level level, int x, int y, byte gender) {
		super(level, name, x, y, 1, SIZE, SIZE, SpriteSheet.player_male, START_HEALTH);
		
		// load basic data
		inventory = new Inventory();
		maxStamina = START_STAMINA;
		stamina = maxStamina;
		this.collisionImmune = true;
		
		// use the female spritesheet if female
		if (gender == PlayerData.FEMALE) {
			setSpriteSheet(SpriteSheet.player_female);
		}
		
		// gives certain names certain powers
		if (name.equals("Dev_Derek") || name.equals("Dev_Stevie")|| name.equals("Dev_Wesley")
				|| name.equals("Dev_Joel") || name.equals("Dev_Andrew") || name.equals("Dev_Karl")
				|| name.equals("Easy Mode")) {
			grantDevPowers();
			System.err.println("Creating Developer");
		}  
		else {
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
			
			// now assign the right spritesheet
			if (gender == PlayerData.FEMALE) {
				equippedSword.setSpriteSheet(PlayerData.FEMALE);

			} else {
				equippedSword.setSpriteSheet(PlayerData.MALE);
			}
			
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
	public void goTo(Level level) {

		// play the click sound
		SoundHandler.play(SoundHandler.click);

		// play the background music
		SoundHandler.playLoop(level.getBackgroundMusic());

		// remove the player from this level
		getLevel().remove(this);

		// clears all the dead mobs on the last level
		getLevel().clear();
		
		// load the level if needed
		if (!level.isLoaded()) {
			try {
				level.generateLevel();
			} catch (IOException e) {
				System.err.println("Level cannot be loaded!");
				e.printStackTrace();
				return;
			}
		}

		// change the global level variable
		setLevel(level);

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
		
		// update the active quest
		for (Quest q: activeQuests) {
			q.update();
		}

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
			if(gender== PlayerData.FEMALE) {
				this.gunSheet = SpriteSheet.playerGuns_female_noarmor;
			}else {
				this.gunSheet = SpriteSheet.playerGuns_male_noarmor;
			}
		}


		// sets the status of the sword
		if (equippedSword != null) {
			isSwinging = equippedSword.isSwinging();
		} else {
			this.yTile = 0;
			if(gender== PlayerData.FEMALE) {
				this.swordSheet = SpriteSheet.playerSwords_female_noarmor;
			}else {
				this.swordSheet = SpriteSheet.playerSwords_male_noarmor;
			}
		}

		// determines if the player is going to move
		isMoving = (dx != 0 || dy != 0);

		// play swimming sound
		if (isSwimming) {
			SoundHandler.playAmbience(SoundHandler.swimming);
			
			// remove equip in water
			if (lastEquip == null) {
				if (equippedGun != null) {
					lastEquip = equippedGun;
				} else if (equippedSword != null) {
					lastEquip = equippedSword;
					equippedSword.reset();
				}
			}
			equippedGun = null;
			equippedSword = null;
			
			isShooting = isSwinging = false;
			
			// shift sprints, can't sprint in water
		} else {
			if (lastEquip != null) {
				if (lastEquip instanceof Gun) {
					equippedGun = (Gun) lastEquip;
				} else {
					equippedSword = (Sword) lastEquip;
				}
				lastEquip = null;
			}
			
			if (isSprinting && stamina > 0 && isMoving && !isShooting && !isSwinging) {

				// shift doubles the speed
				dx *= 2;
				dy *= 2;

				// make a faster step animation
				numSteps++;

				// reduce stamina
				stamina--;
			}

		}

		// regenerate stamina when not moving
		if (!isMoving && stamina < maxStamina && !isShooting && !isSwinging) {
			stamina += 0.6;
		}

		// regenerate stats very slowing if moving (but not sprinting)
		if (isMoving && !isSprinting) {
			if (stamina < maxStamina)
				stamina += 0.2;
		}

		// move the player
		if (isMoving) {
			move(dx, dy);
			playTileSound();
		}

		tickCount++;

	}
	
	/**
	 * Play the appropriate sound for the tile
	 */
	private void playTileSound() {

		// get the current tile
		Tile currentTile = getLevel().getTileFromEntityCoords(getX(), getY());

		// Grass sound
		if (Tile.isGrass(currentTile.getId())) {
			SoundHandler.playAmbience(SoundHandler.footstepsGrass);

			// wood sound
		} else if (Tile.isWood(currentTile.getId())) {
			SoundHandler.playAmbience(SoundHandler.footstepsWood);

			// Road sounds
		} else if (Tile.isRoad(currentTile.getId())) {
			SoundHandler.playAmbience(SoundHandler.footstepsRoad);

		} else if (Tile.isFarmalnd(currentTile.getId())) {
			SoundHandler.playAmbience(SoundHandler.footstepsFarmland);

			// dirt sound effect
		} else if (Tile.isDirt(currentTile.getId())) {
			SoundHandler.playAmbience(SoundHandler.footstepsDirt);
		}

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
				addItem(e);
			}
		}
	}
	
	/**
	 * @param item - item to add to the player's inventory
	 */
	public void addItem(Item item) {
		inventory.add(item);
		MessageHandler.displayText("You have obtained " + item.getName(), Color.YELLOW);
	}

	/**
	 * Processes the image of the player on the screen
	 */
	public void render(Screen screen) {

		// tileSize used for rendering in different scales/directions
		int tileSize = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xLocation = getX(), yLocation = getY();

		// don't render if driving
		if (vehicle != null) {
			return;
		}

		// do basic rendering
		super.render(screen);
		
		// x and y tile on spritesheet
		int xTile = 0, yTile = this.yTile;

		// whether or not to flip horizontally (walking animation)
		// 2 ^ walking speed = 2 ^ 4 = 16 ~= tickcount % 16
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;
		
		//Rendering for Player when no weapons are Equipped
		if(getEquippedGun() == null && getEquippedSword() == null) {
			// north direction
			if (getDirection() == Direction.NORTH) {
				xTile = 10;
				if (!isMoving) {
					xTile = 8;
					xTile += ((tickCount % 120 <= 60) ? 10 : 0);
				}
				// south direction
			} else if (getDirection() == Direction.SOUTH) {
				xTile = 2;
				if (!isMoving) {
					xTile = 0;
					xTile += ((tickCount % 120 <= 60) ? 14 : 0);
				}
			// left or right
			} else {
				xTile = 4;
				if (isMoving)
					xTile += (flip ? 2 : 0);
				if (!isMoving)
					xTile += ((tickCount % 120 <= 60) ? 12 : 0);
				flip = getDirection() == Direction.WEST;
			}
			
			// death texture
			if (isDead()) {
				xTile = 12;
			}
	
			
			// Normal Player movement -- Not Attacking Anything
			if (!isShooting && !isSwinging) {
	
				int swimOffset = tileSize * (isSwimming ? 1 : 0);
	
				// Upper body 1
				screen.render(xLocation + (tileSize * (flip ? 1 : 0)), yLocation
						+ swimOffset, xTile, yTile, getSpriteSheet(), flip,
						color);
				// Upper Body 2
				screen.render(xLocation + tileSize - (tileSize * (flip ? 1 : 0)),
						yLocation + swimOffset, xTile + 1, yTile, getSpriteSheet(), flip, color);
	
				if (!isSwimming) {
					// Lower Body 1
					screen.render(xLocation + (tileSize * (flip ? 1 : 0)), yLocation + tileSize, xTile, yTile + 1,
					        getSpriteSheet(), flip, color);
					// Lower Body 2
					screen.render(xLocation + tileSize - (tileSize * (flip ? 1 : 0)), yLocation + tileSize, xTile + 1,
					        yTile + 1, getSpriteSheet(), flip, color);
	
				}
			}
		}

		//Rendering for Player when they have equipped a firearm
		if(getEquippedGun() != null) {
			
			// bazooka and flamethrower are special :)
			if (equippedGun instanceof DifferentOffsetItem) {
				((DifferentOffsetItem) equippedGun).renderPlayer(screen, this,
						gunSheet, shootingDir);
				return;
			}
			
			// north direction
			xTile = 0;
			yTile = equippedGun.getPlayerOffset();
			if (getDirection() == Direction.NORTH) {
				xTile = 10;
				if (equippedGun == Item.crossBow) {
					xTile = 12;
				}
				if (!isMoving) {
					xTile = 8;
					xTile += ((tickCount % 100 <= 50) ? 8 : 0);
					if(equippedGun == Item.crossBow) {
						xTile = 10;
						xTile += ((tickCount % 100 <= 50) ? 20 : 0);
					}
				}
			// south direction
			} else if (getDirection() == Direction.SOUTH) {
				xTile = 2;
				if(equippedGun == Item.crossBow) {
					xTile = flip ? 2 : 4;
					flip = false;
				} if (!isMoving) {
					xTile = 0;
					xTile += ((tickCount % 120 <= 60) ? 12 : 0);
					if (equippedGun == Item.crossBow) {
						xTile = 0;
						xTile += ((tickCount % 120 <= 60) ? 26 : 0);
					}
				}
			// left or right
			} else {
				xTile = 4;
				if(equippedGun == Item.crossBow) {
					xTile = 6;
				}
				if (isMoving)
					xTile += (flip ? 2 : 0);
				if (!isMoving) {
					xTile += ((tickCount % 120 <= 60) ? 10 : 0);
					if (equippedGun == Item.crossBow) {
						xTile = 6;
						xTile += ((tickCount % 120 <= 60) ? 22 : 0);
					}
				}
					flip = getDirection() == Direction.WEST;
			}
			// Handles Shooting Animation
			if (isShooting) {

				// tile positions for player
				xTile = 0;
				yTile = equippedGun.getPlayerOffset();

				if (shootingDir == Direction.NORTH) {
					xTile = 10;
					if (equippedGun == Item.crossBow) {
						xTile = 12;
					}
					if (!isMoving) {
						xTile = 8;
						if (equippedGun == Item.crossBow) {
							xTile = 10;
						}
					}
				} else if (shootingDir == Direction.SOUTH) {
					if (equippedGun == Item.crossBow) {
						xTile = flip ? 2 : 4;
						flip = false;
					}else {
						xTile = 2;
					}
					if (!isMoving) {
						xTile = 0;
					}
				} else {
					// reset the flip for horizontal movement
					flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;
					
					if(equippedGun == Item.crossBow) {
						xTile = flip ? 6 : 8;
					}else {
						xTile = flip ? 4 : 6;
					}
					if (!isMoving) {
						xTile = 4;
						if(equippedGun == Item.crossBow) {
							xTile = 6;
						}
					}
					if (shootingDir == Direction.EAST) {
						xLocation += 3;
					} else {
						xLocation -=3;
					}
					flip = shootingDir == Direction.WEST;
				}

			}
			SpriteSheet sheet = this.gunSheet;
			
			// Upper Body 1
			screen.render(xLocation + (tileSize * (flip ? 1 : 0)), yLocation, xTile, yTile, sheet, flip, color);
			// Upper Body 2
			screen.render(xLocation + tileSize - (tileSize * (flip ? 1 : 0)), yLocation, xTile + 1, yTile, sheet, flip,
			        color);
			// Lower Body 1
			screen.render(xLocation + (tileSize * (flip ? 1 : 0)), yLocation + tileSize, xTile, yTile + 1, sheet, flip,
			        color);
			// Lower Body 2
			screen.render(xLocation + tileSize - (tileSize * (flip ? 1 : 0)), yLocation + tileSize, xTile + 1, yTile + 1,
			        sheet, flip, color);

		}
		
		//Handles the Player when they have a sword equipped
		if (equippedSword != null) {
			yTile = equippedSword.getYSwingOffset();
			//Rendering for walking with a sword
			if(!isSwinging) {
				if(getDirection() == Direction.NORTH) {
					if(isMoving) {
						//varies between the two walking sprites, every sword has the same offsets
						xTile = 58;
						xTile += (flip ? 2 : 0);
						flip = false;
					}
					if(!isMoving) {
						//varies between standing and head bob sprite to give illusion of breathing
						//every sword has the same offsets
						xTile = 56;
						//head bob sprite at tile 70
						xTile += ((tickCount % 120 <= 60) ? 14 : 0);
					}
				}else if(getDirection() == Direction.SOUTH) {
					//varies between the two walking sprites
					if(isMoving) {
						xTile = 40;
						xTile += (flip ? 2 : 0);
						flip = false;
					}
					if(!isMoving) {
						//varies between standing and head bob sprite to give illusion of breathing
						//every sword has the same offsets
						xTile = 38;
						//head bob sprite at 62
						xTile += (tickCount % 120 <= 60) ? 24 : 0;
					}
				}else if(getDirection() == Direction.EAST) {
					//varies between the two walking sprites
					if(isMoving) {
						xTile = 44;
						xTile += (flip ? 3 : 0);
						flip = false;
					}
					if(!isMoving) {
						//varies between standing and head bob sprite to give illusion of breathing
						//every sword has the same offsets
						xTile = 44;
						//head bob sprite at 64
						xTile += (tickCount % 120 <= 60) ? 20 : 0;
					}
				}else if(getDirection() == Direction.WEST) {
					//varies between the two walking sprites
					if(isMoving) {
						xTile = 50;
						xTile += (flip ? 3 : 0);
						flip = true;
					}
					if(!isMoving) {
						//varies between standing and head bob sprite to give illusion of breathing
						//every sword has the same offsets
						xTile = 50;
						//head bob sprite at 67
						xTile += (tickCount % 120 <= 60) ? 17 : 0;
					}
				}
				isLongitudinal = (getDirection() == Direction.WEST || getDirection() == Direction.EAST);
				SpriteSheet sheet = this.swordSheet;
				// Rendering for when the player faces a north or south direction
				if( !isLongitudinal ) {
					flip = false;
					// Tip of the Sword Right
					screen.render(xLocation, yLocation - tileSize, xTile, yTile - 1, sheet, flip, color);
					// Tip of the Sword Left
					screen.render(xLocation + tileSize, yLocation - tileSize, xTile + 1, yTile - 1, sheet, flip, color);
					// Upper Body 1
					screen.render(xLocation, yLocation, xTile, yTile, sheet, flip, color);
					// Upper Body 2
					screen.render(xLocation + tileSize, yLocation, xTile + 1, yTile, sheet, flip, color);
					// Lower Body 1
					screen.render(xLocation, yLocation + tileSize, xTile, yTile + 1, sheet, flip, color);
					// Lower Body 2
					screen.render(xLocation + tileSize, yLocation + tileSize, xTile + 1, yTile + 1,
						        sheet, flip, color);
				}
				/** EAST/WEST Directions **/
				else {
					flip = false;
					
					//Player coords tracked from head position, move by 1 tile to accommodate extra tile on left
					if( getDirection() == Direction.WEST )
						xLocation -= 8;
					
					// Top of Sprite Right
					screen.render(xLocation + (tileSize * (flip ? 2 : 0)), yLocation - tileSize, xTile, yTile - 1, sheet,
							flip, color);
					// Top of Sprite Middle
					screen.render(xLocation + tileSize, yLocation - tileSize, xTile + 1, yTile - 1, sheet, flip, color);
					// Top of Sprite Left
					screen.render(xLocation + 2*tileSize - (tileSize * (flip ? 2 : 0)), yLocation - tileSize, xTile + 2, yTile - 1, 
							sheet, flip, color);
					// Middle of Sprite Right
					screen.render(xLocation + (tileSize * (flip ? 2 : 0)), yLocation, xTile, yTile, sheet, flip, color);
					// Middle of Sprite Middle
					screen.render(xLocation + tileSize, yLocation, xTile + 1, yTile, sheet, flip, color);
					// Middle of Sprite Left
					screen.render(xLocation + 2*tileSize - (tileSize * (flip ? 2 : 0)), yLocation, xTile + 2, yTile, 
							sheet, flip, color);	
					// Bottom of Sprite Right
					screen.render(xLocation + (tileSize * (flip ? 2 : 0)), yLocation + tileSize, xTile, yTile + 1, sheet, flip, color);
					// Bottom of Sprite Middle
					screen.render(xLocation + tileSize, yLocation + tileSize, xTile + 1, yTile + 1, sheet, flip, color);
					// Bottom of Sprite Left
					screen.render(xLocation + 2*tileSize - (tileSize * (flip ? 2 : 0)), yLocation + tileSize, xTile + 2, yTile + 1, 
							sheet, flip, color);
					}
				isLongitudinal = false;
			}
			
			// Handles Swinging Animation
			if (isSwinging) {
				equippedSword.render(screen, xLocation, yLocation, getColor());
				setDirection(equippedSword.getDirection());
			}
			//Handles Blocking Animation
			if(isBlocking){
				equippedSword.render(screen, xLocation, yLocation,getColor());
				setDirection(equippedSword.getDirection());
			}
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
	 * Sets the shirt color
	 * 
	 * @param num - the shirt color in hexadecimal
	 */
	public void setShirtColor(int num) {
		color[1] = num;
	}

	/**
	 * Sets the skin color
	 * 
	 * @param num - the skin color in hexadecimal
	 */
	public void setSkinColor(int num) {
		color[2] = num;
	}
	
	/**
	 * Sets the hair color
	 * 
	 * @param num - the pants color in hexadecimal
	 */
	public void setHairColor(int num) {
		color[3] = num;
	}
	
	/**
	 * Sets the pants color
	 * 
	 * @param num - the pants color in hexadecimal
	 */
	public void setPantsColor(int num) {
		color[4] = num;
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
		Item.rocketAmmo.setQuanity(500);
		inventory.add(Item.flameThrower);
		inventory.add(Item.assaultRifle);
		Item.assaultRifleAmmo.setQuanity(500);
		inventory.add(Item.assaultRifleAmmo);
		inventory.add(Item.revolver);
		Item.revolverAmmo.setQuanity(500);
		inventory.add(Item.laserRevolver);
		Item.laserAmmo.setQuanity(500);
		inventory.add(Item.shotgun);
		Item.shotgunAmmo.setQuanity(500);
		inventory.add(Item.crossBow);
		Item.arrowAmmo.setQuanity(500);
		inventory.add(Item.shortSword);
		inventory.add(Item.longSword);
		inventory.add(Item.claymore);
		inventory.add(Item.sabre);
		inventory.add(Item.heavenlySword);
		inventory.add(Item.kingSword);


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
	public int getCurrentStamina() {
		return (int) stamina;
	}

	/**
	 * @return the max stamina
	 */
	public int getMaxStamina() {
		return (int) maxStamina;
	}

	/**
	 * Adds a quest
	 */
	public void addQuest(Quest quest) {
		activeQuests.add(quest);
	}
	
	/**
	 * Finishes a quest
	 */
	public void finishQuest(Quest quest) {
		activeQuests.remove(quest);
		completedQuests.add(quest);
	}

	/**
	 * @return the summary of the first quest
	 */
	public String getQuestSummary() {
		if (activeQuests.size() > 0) {
			return activeQuests.get(0).getSummary();
		} else {
			return "No Active Quests";
		}
	}
	
	/**
	 * Makes sure that all active quests can be completed
	 */
	public void checkQuests() {
		
		// iterat through all quests
		for (int i = 0; i < activeQuests.size(); i++) {
			
			// get the current quest
			Quest q = activeQuests.get(i);
			
			// if the giver is dead, complete it
			if (!q.isCompletable()) {
				finishQuest(q);
				i--;
			}
		}
		
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
	 * Replenishes the mob health
	 * 
	 * @param num - the amount to heal -1 to fully heal
	 */
	public void restore(int num) {

		// heal to full
		if (num == -1) {
			stamina = maxStamina;

			// heal by a certain amount
		} else {
			stamina += num;

			// cant go over max and check for overflow
			if (stamina > maxStamina || stamina < -1000) {
				stamina = maxStamina;
			}
		}

	}
	
	/**
	 * If the player dies, then stop the game
	 */
	@Override
	public void remove() {
		super.remove();
		
		// reset data fields
		isSwinging = isShooting = isMoving = false;
		
		// play death sound
		SoundHandler.play(SoundHandler.deathDirge);
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
			getLevel().add(new Skeleton(getLevel(), getX(), getY()));
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
		//block key
		if(window.isKeyPressed(KeyEvent.VK_B)){
			if(!isShooting&&!isSwimming&&!isSwinging
				&&equippedSword!=null){
				equippedSword.block(getLevel(),getX(),getY(),getDirection(), isBlocking, this);
				defense += equippedSword.getStrength();
			}
		}

		// displays names of all mobs
		if (window.isKeyPressed(KeyEvent.VK_N)) {
			for (Mob m : getLevel().getMobs()) {
				if (!m.isDead())
					m.isTalking = true;
			}
			window.toggle(KeyEvent.VK_N);
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

					goTo(((Transporter) entity).getNextLevel());
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
	
	@Override
	protected boolean willCollide(int dx, int dy) {
		boolean willCollide = super.willCollide(dx, dy);
		if (willCollide) {
			SoundHandler.playAmbience(SoundHandler.bump);
		}
		return willCollide;
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
	 * @return the color of the hair
	 */
	public int getHairColor() {
		return color[3];
	}
	
	/**
	 * @return the color of the pants
	 */
	public int getPantsColor() {
		return color[4];
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

	/**
	 * Player will not be saved with the rest
	 */
	@Override
	public byte getId() {
		return -1;
	}

	@Override
	public byte getType() {
		return gender;
	}

	@Override
	public void setType(byte type) {
		gender = type;
		
		// use the female spritesheet if female
		if (gender == PlayerData.FEMALE) {
			setSpriteSheet(SpriteSheet.player_female);
		} else {
			setSpriteSheet(SpriteSheet.player_male);
		}
	}

}
