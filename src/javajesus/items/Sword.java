package javajesus.items;

import java.awt.Rectangle;

import javajesus.SoundHandler;
import javajesus.dataIO.PlayerData;
import javajesus.entities.Damageable;
import javajesus.entities.Mob;
import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A sword is an item that also renders a swinging animation for the player
 */
public class Sword extends Item {

	// whether or not the sword is in cooldown
	private boolean cooldown;

	// the number of ticks between swings
	private int cooldownTime;

	// tracks the time elapsed for cooldown
	private int cooldownTicks;

	// damage per swing
	private int damage;

	// whether or not the player is swinging
	private boolean isSwinging;

	// coordinates for the player swinging animation
	private int xSwingOffset, ySwingOffset;

	// spritesheet for player
	private SpriteSheet sheet = SpriteSheet.playerSwords_male_noarmor;

	// the offsets for the power swing positions
	private int[] powerSwingOffsets;
	
	// the offsets of the blocking sprites
	private int blockingOffsets;

	// first powerSwingOffset position based on direction
	private int startPos;

	// current offset in the swing position
	private int currentPowerOffset;

	// tracks the time elapsed for the power swing attack
	private int powerSwingTicks;

	// whether or not the sword is power swinging
	private boolean powerSwinging;
	
	//whether or not the sword is blocking
	private boolean blocking;

	// there is an extra offset when diagonal and facing south
	private boolean diagonal;

	// the hit area of the sword
	private final Rectangle bounds = new Rectangle();

	// length of the sword
	private int length;

	// sword bound offsets
	public static final int SHORT = 5, MEDIUM = 10, LONG = 15;

	// unit size of player
	private static final int PLAYER_SIZE = 16;

	// base size of each spritesheet square
	private static final int UNIT_SIZE = 8;

	// the direction of the sword
	private Direction direction;
	
	// knockback strength
	private int knockback;

	/**
	 * Creates a Sword
	 * 
	 * @param name - the name of the sword
	 * @param id - the unique ID
	 * @param xTile - the xTile on the item sheet
	 * @param yTile - the yTile on the item sheet
	 * @param xSwingOffset - the x offset on the player sword sheet
	 * @param ySwingOffset - the y offset on the player sword sheet
	 * @param color - color of the sword
	 * @param description - description of the sword
	 * @param cooldown - the cooldown between swings
	 * @param damage - the damage dealt
	 * @param powerSwingOffsets - the offset points on the player spritesheet for the extended swing
	 * @param swingoffset - the swing modifier offset inbetween swing animations (Extra length of sword)
	 * @param length - either SHORT, MEDIUM, or LONG
	 */
	public Sword(String name, int id, int xTile, int yTile, int xSwingOffset, int ySwingOffset, int[] color,
			String description, int cooldown, int damage, int[] powerSwingOffsets, int blockingOffsets,
			int length, int knockback) {
		super(name, id, xTile, yTile, color, description, true);
		this.cooldownTime = cooldown;
		this.damage = damage;
		this.xSwingOffset = xSwingOffset;
		this.ySwingOffset = ySwingOffset;
		this.length = length;
		this.powerSwingOffsets = powerSwingOffsets;
		this.blockingOffsets = blockingOffsets;
		this.knockback = knockback;
	}
	
	/**
	 * Sets the spritesheet based on the gender
	 * @param type - PlayerData.Male/Female
	 */
	public void setSpriteSheet(byte type) {
		if (type == PlayerData.FEMALE) {
			sheet = SpriteSheet.playerSwords_female_noarmor;
		} else {
			sheet = SpriteSheet.playerSwords_male_noarmor;
		}
	}

	/**
	 * Updates the sword
	 * 
	 * @param level - the level the mob is on
	 * @param x - the mob's x coord
	 * @param y - the mob'x y coord
	 */
	public void tick(Level level, int x, int y, Player player) {

		// timer for the cooldown
		if (cooldown) {
			cooldownTicks++;
		}

		// timer for the cooldown
		if (cooldownTicks >= cooldownTime) {
			cooldownTicks = 0;
			cooldown = isSwinging = blocking = false;
		}
		
		// update the area box
		updateBounds(x, y);

		// update the directions and sprites if power swinging
		if (powerSwinging) {

			// hit the mobs
			attackMobs(level, player);

			if (++powerSwingTicks % (cooldownTime / 5) == 0) {

				// Always increment by at least 2
				currentPowerOffset += 2;
				
				// If facing EAST/WEST add an additional 2 to the offset
				if( direction == Direction.EAST || direction == Direction.WEST )
					currentPowerOffset += 2;
				// If facing diagonally add an additional 1 to the offset
				else if( diagonal )
					currentPowerOffset++;
				

				// power swing timer
				if (powerSwingTicks >= cooldownTime) {
					currentPowerOffset = 0;
					powerSwingTicks = 0;
					powerSwinging = false;
					diagonal = false;
				}
			}
		}

	}

	/**
	 * Sets the bounds for attacking enemies
	 * 
	 * @param x - the mob's x position
	 * @param y - the mob's y position
	 */
	private void updateBounds(int x, int y) {
		
		if (direction != null) {
			switch (direction) {
			case NORTH:
				bounds.setBounds(x, y - length, PLAYER_SIZE, length);
				break;
			case SOUTH:
				bounds.setBounds(x, y + PLAYER_SIZE, PLAYER_SIZE, length);
				break;
			case WEST:
				bounds.setBounds(x - length, y, length, PLAYER_SIZE);
				break;
			case EAST:
				bounds.setBounds(x + PLAYER_SIZE, y, length, PLAYER_SIZE);
				break;
			default:
				bounds.setBounds(x, y, PLAYER_SIZE, PLAYER_SIZE);
				break;
			}
		}
	}

	/**
	 * Swings the sword!
	 * 
	 * @param x - the mob's x position
	 * @param y - the mob's y position
	 * @param dir - the direction the mob is facing
	 * @param power - whether or not it is a power swing
	 */
	public void swing(Level level, int x, int y, Direction dir, boolean power, Player player) {

		if (!cooldown) {

			direction = dir;

			// enables power swinging
			// sets initial offset for power swinging
			if (powerSwinging = power) {
				switch (dir) {
				case NORTH:
					startPos = powerSwingOffsets[3];
					break;
				case SOUTH:
					startPos = powerSwingOffsets[1];
					break;
				case EAST:
					startPos = powerSwingOffsets[2];
					break;
				default:
					startPos = powerSwingOffsets[0];
					break;
				}
			}

			// update area box
			updateBounds(x, y);

			// hits mobs
			attackMobs(level, player);

			cooldown = isSwinging = true;

			// TODO implement different sounds
			SoundHandler.playShortSword();

		}

	}
	
	/**
	 * Blocks incoming attacks with the equipped sword
	 * 
	 * @param x - mobs x coordinate
	 * @param y - mobs y coordinate
	 * @param dir - direction the mob is facing
	 */
	public void block(Level level, int x, int y, Direction dir, boolean isBlocking, Player player){
		direction = dir;
		if(blocking = isBlocking){
			switch(dir){
			case NORTH:
				blockingOffsets = blockingOffsets+0;
				break;
			case SOUTH:
				blockingOffsets = blockingOffsets+2;
				break;
			case EAST:
				blockingOffsets = blockingOffsets+4;
				break;
			default:
				blockingOffsets = blockingOffsets+4;
				break;
			}
		}
	}
	
	/**
	 * Renders collision box to the screen
	 * 
	 * @param screen - screen to render to
	 */
	public void renderBounds(Screen screen) {
		screen.renderCollisionBox(bounds);
	}

	/**
	 * Damages mobs in the bounds
	 * 
	 * @param level - the level to check
	 * @param player - player attacking the mob
	 */
	private void attackMobs(Level level, Player player) {
		for (int i = 0; i < level.getDamageables().size(); i++) {
			Damageable m = level.getDamageables().get(i);
			if (bounds.intersects(m.getBounds())) {
				
				if (m instanceof Mob) {
					// inflict knockback
					((Mob) m).knockback(knockback, direction);
				}
				
				// damage the mob
				player.attack(Player.DAMAGE_RANGE, m);
				
			}
		}
	}
	
	/**
	 * @return sword strength
	 */
	public int getStrength() {
		return damage;
	}

	/**
	 * Some items require the player to have additional animations that aren't
	 * available normally for the player
	 * 
	 * Delegate the rendering calls of the player here
	 * 
	 * @param screen - the screen to display it on
	 * @param player - the player to display TODO fix offsets with modifiers
	 */
	public void render(Screen screen, int xOffset, int yOffset, int[] color) {

		// tile offsets
		int xTile = xSwingOffset, yTile = ySwingOffset;

		// whether to render the player backwards
		boolean flip = direction == Direction.WEST;

		// base modifier size
		int modifier = UNIT_SIZE;
		
		diagonal = false;

		// sprite offset for SIMPLE swing in one direction
		if (direction == Direction.NORTH) {
			xTile = 2;
		} else if (direction != Direction.SOUTH) {
			xTile = 4;
		}
		//adjusts offsets for blocking
		if(blocking){
			xTile = blockingOffsets;
		}
		// adjust offsets for power swinging
		if (powerSwinging) {
			
			// if the swing animation is over, roll back
			if (startPos + currentPowerOffset > powerSwingOffsets[3] + 4) {
				startPos = powerSwingOffsets[0];
				currentPowerOffset = 0;
			}

			// get the right xTile for power swinging
			xTile = startPos + currentPowerOffset;

			// do not render backwards
			flip = false;
			
			// update the direction
			if (xTile == powerSwingOffsets[0]) {
				direction = Direction.WEST;
			} else if (xTile == powerSwingOffsets[2])
				direction = Direction.EAST;
			else if (xTile < powerSwingOffsets[2])
				direction = Direction.SOUTH;
			else
				direction = Direction.NORTH;

			// Set diagonal, TRUE only if SOUTH or NORTH at specified locations
			diagonal =  ( (xTile > powerSwingOffsets[0] && xTile < powerSwingOffsets[1])
					|| (xTile > powerSwingOffsets[1] && xTile < powerSwingOffsets[2]) )
					|| ( (xTile > powerSwingOffsets[2] && xTile < powerSwingOffsets[3])
					|| (xTile > powerSwingOffsets[3]) );
			
			// center the first south diagonal
			if ( diagonal && direction == Direction.SOUTH && xTile < powerSwingOffsets[1]
					|| diagonal && direction == Direction.NORTH && xTile > powerSwingOffsets[3]) {
				xOffset -= modifier;
			}

		}
		// Render normal NORTH: 2*3 (x*y)
		if( direction == Direction.NORTH ) {
			if( !diagonal ) {
			for (int i = 0; i < 3; i++) {
					// Left Half
					screen.render(xOffset, yOffset + (i - 1) * modifier, xTile, yTile - 1 + i, sheet, false, color);
					// Right Half
					screen.render(xOffset + modifier, yOffset + (i - 1) * modifier, xTile + 1, yTile - 1 + i, sheet, false, color);
				}
			} else {
				// Diagonal Rendering NORTH: 3*3 (x*y)
				for (int i = 0; i < 3; i++) {
					// Left
					screen.render(xOffset, yOffset + (i - 1) * modifier, xTile, yTile - 1 + i, sheet, false, color);
					// Middle
					screen.render(xOffset + modifier, yOffset + (i - 1) * modifier, xTile + 1, yTile - 1 + i, sheet, false, color);
					// Right
					screen.render(xOffset + modifier*2, yOffset + (i - 1) * modifier, xTile + 2, yTile - 1 + i, sheet, false, color);
				}
			}
		}
		// Render SOUTH: 2*3 (x*y)
		if( direction == Direction.SOUTH) {
			if(!diagonal) {
				for (int i = 0; i < 3; i++) {
					// Left Half
					screen.render(xOffset, yOffset + i * modifier, xTile, yTile + i, sheet, false, color);
					// Right Half
					screen.render(xOffset + modifier, yOffset + i * modifier, xTile + 1, yTile + i, sheet, false, color);
				}
			} else {
				// Diagonal Rendering SOUTH: 3*3 (x*y)
				for (int i = 0; i < 3; i++) {
					// Left
					screen.render(xOffset, yOffset + i * modifier, xTile, yTile + i, sheet, false, color);
					// Middle
					screen.render(xOffset + modifier, yOffset + i * modifier, xTile + 1, yTile + i, sheet, false, color);
					// Right
					screen.render(xOffset + modifier*2, yOffset + i * modifier, xTile + 2, yTile + i, sheet, false, color);
				}
			}
		}
		// Render EAST/WEST: 4*2 (x*y)
		if (direction == Direction.EAST || direction == Direction.WEST) {
			if(direction == Direction.WEST)
				xOffset -= modifier*2;
			for (int i = 0; i < 2; i++) {
				// Left
				screen.render(xOffset + (modifier * (flip ? 3 : 0)), yOffset + (modifier * i),
				        xTile, yTile + i, sheet, flip, color);
				// Left Middle
				screen.render(xOffset + modifier + (modifier * (flip ? 1 : 0)), yOffset + (modifier * i),
				        xTile + 1, yTile + i, sheet, flip, color);
				// Right Middle
				screen.render(xOffset + 2 * modifier - (modifier * (flip ? 1 : 0)), yOffset + (modifier * i),
				        xTile + 2, yTile + i, sheet, flip, color);
				// Right
				screen.render(xOffset + 3 * modifier - (modifier * (flip ? 3 : 0)), yOffset + (modifier * i),
				        xTile + 3, yTile + i, sheet, flip, color);
			}
		}
			
	}

	public int getYSwingOffset() {
		return ySwingOffset;
	}
	/**
	 * @return whether or not the sword is swinging
	 */
	public boolean isSwinging() {
		return isSwinging;
	}
	
	public void reset() {
		isSwinging = false;
		cooldown = false;
	}
	
	/**
	 * 
	 * @return whether or not the sword is blocking
	 */
	public boolean isBlocking(){
		return blocking;
	}

	/**
	 * @return the direction of the sword
	 */
	public Direction getDirection() {
		return direction;
	}

}
