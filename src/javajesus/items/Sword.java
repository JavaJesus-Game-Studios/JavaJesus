package javajesus.items;

import java.awt.Rectangle;

import javajesus.SoundHandler;
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

	private static final long serialVersionUID = -3339710504468683415L;

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

	// cooridnates for the player swinging animatino
	private int xSwingOffset, ySwingOffset;

	// spritesheet for player
	private static final SpriteSheet sheet = SpriteSheet.swords;

	// the offsets for the power swing positions
	private int[] powerSwingOffsets;

	// first powerSwingOffset position based on direction
	private int startPos;

	// current offset in the swing position
	private int currentPowerOffset;

	// tracks the time elapsed for the power swing attack
	private int powerSwingTicks;

	// whether or not the sword is power swinging
	private boolean powerSwinging;

	// power swing offset modifier that changes the current position
	// 2 = GREATSWORD (even 2 spaces extra in each)
	// 1 = the swords with the awkward 1 space in diagonals
	private int powerSwingModifier;

	// TODO should fix in spritesheet
	// there is an extra offset when diagonal and facing south
	private boolean diagonal;

	// the hit area of the sword
	private Rectangle bounds = new Rectangle();

	// length of the sword
	private int length;

	// sword bound offsets
	public static final int SHORT = 4, MEDIUM = 8, LONG = 12;

	// unit size of player
	private static final int PLAYER_SIZE = 16;

	// base size of each spritesheet square
	private static final int UNIT_SIZE = 8;

	// the direction of the sword
	private Direction direction;

	/**
	 * Creates a Sword defaults to short swords
	 * 
	 * @param name
	 *            the name of the sword
	 * @param id
	 *            the unique ID
	 * @param xTile
	 *            the xTile on the item sheet
	 * @param yTile
	 *            the yTile on the item sheet
	 * @param xSwingOffset
	 *            the x offset on the player sheet
	 * @param ySwingOffset
	 *            the y offset on the player sheet
	 * @param color
	 *            color of the sword
	 * @param description
	 *            description of the sword
	 * @param cooldown
	 *            the cooldown between swings
	 * @param damage
	 *            the damage dealt
	 * @param powerSwingOffsets
	 *            the offset points on the player spritesheet for the extended
	 *            swing
	 */
	public Sword(String name, int id, int xTile, int yTile, int xSwingOffset, int ySwingOffset, int[] color,
			String description, int cooldown, int damage, int[] powerSwingOffsets) {
		this(name, id, xTile, yTile, xSwingOffset, ySwingOffset, color, description, cooldown, damage,
				powerSwingOffsets, 0, SHORT);

	}

	/**
	 * Creates a Sword
	 * 
	 * @param name
	 *            the name of the sword
	 * @param id
	 *            the unique ID
	 * @param xTile
	 *            the xTile on the item sheet
	 * @param yTile
	 *            the yTile on the item sheet
	 * @param xSwingOffset
	 *            the x offset on the player sheet
	 * @param ySwingOffset
	 *            the y offset on the player sheet
	 * @param color
	 *            color of the sword
	 * @param description
	 *            description of the sword
	 * @param cooldown
	 *            the cooldown between swings
	 * @param damage
	 *            the damage dealt
	 * @param powerSwingOffsets
	 *            the offset points on the player spritesheet for the extended
	 *            swing
	 * @param swingoffset
	 *            the swing modifier offset inbetween swing animations
	 * @param length
	 *            either SHORT, MEDIUM, or LONG
	 */
	public Sword(String name, int id, int xTile, int yTile, int xSwingOffset, int ySwingOffset, int[] color,
			String description, int cooldown, int damage, int[] powerSwingOffsets, int swingoffset, int length) {
		super(name, id, xTile, yTile, color, description, true);
		this.cooldownTime = cooldown;
		this.damage = damage;
		this.xSwingOffset = xSwingOffset;
		this.ySwingOffset = ySwingOffset;
		this.length = length;
		this.powerSwingOffsets = powerSwingOffsets;
		this.powerSwingModifier = swingoffset;

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
			cooldown = isSwinging = false;
		}

		// update the directions and sprites if power swinging
		if (powerSwinging) {

			// update the area box
			updateBounds(x, y);

			// hit the mobs
			attackMobs(level, player);

			if (++powerSwingTicks % (cooldownTime / 5) == 0) {

				// increment the current phase by at least 2 (always) on the
				// spritesheet
				currentPowerOffset += 2;

				// long sword will always have two extra boxes
				if (powerSwingModifier == 2) {
					currentPowerOffset += 2;

					// east/west always has an extra box, while some medium
					// diagonal downwardfacing sprites also have an extra box
				} else if (direction == Direction.EAST || direction == Direction.WEST
						|| (diagonal && powerSwingModifier == 1)) {
					currentPowerOffset++;
				}

				// power swing timer
				if (powerSwingTicks >= cooldownTime) {
					currentPowerOffset = 0;
					powerSwingTicks = 0;
					powerSwinging = false;
				}
			}
		}

	}

	/**
	 * Sets the bounds for attacking enemies
	 * 
	 * @param x
	 *            the mob's x position
	 * @param y
	 *            the mob's y position
	 */
	private void updateBounds(int x, int y) {
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
		for (int i = 0; i < level.getMobs().size(); i++) {
			Mob m = level.getMobs().get(i);
			if (bounds.intersects(m.getBounds())) {
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
	 * @param screen
	 *            the screen to display it on
	 * @param player
	 *            the player to display TODO fix offsets with modifiers
	 */
	public void render(Screen screen, int xOffset, int yOffset, int[] color) {

		// tile offsets
		int xTile = xSwingOffset, yTile = ySwingOffset;

		// whether to render the player backwards
		boolean flip = direction == Direction.WEST;

		// base modifier size
		int modifier = UNIT_SIZE;

		// sprite offset for SIMPLE swing in one direction
		if (direction == Direction.NORTH) {
			xTile = 2;
		} else if (direction != Direction.SOUTH) {
			xTile = 4;
		}

		// adjsut offsets for power swinging
		if (powerSwinging) {

			// if the sword is long
			if (powerSwingModifier == 2) {

				// if the swing animation is over, roll back
				if (startPos + currentPowerOffset > powerSwingOffsets[3] + 5) {
					startPos = powerSwingOffsets[0];
					currentPowerOffset = 0;
				}

				// short or medium swords, roll back
			} else if (startPos + currentPowerOffset > powerSwingOffsets[3] + 3) {
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
			} else if (xTile == powerSwingOffsets[2]) {
				direction = Direction.EAST;
			} else if (xTile < powerSwingOffsets[2]) {
				direction = Direction.SOUTH;
			} else {
				direction = Direction.NORTH;
			}

			// set diagonal
			diagonal = direction == Direction.SOUTH && ((xTile < powerSwingOffsets[2] && xTile > powerSwingOffsets[1])
					|| (xTile > powerSwingOffsets[0] && xTile < powerSwingOffsets[1]));

		}

		// render the long sword power swinging animation
		if (powerSwingModifier == 2 && powerSwinging) {

			// long sword
			for (int i = 0; i < 5; i++) {
				screen.render(xOffset, yOffset + i * modifier, xTile + (yTile + i) * sheet.getTilesPerRow(), color, sheet);

				screen.render(xOffset + modifier, yOffset + i * modifier, (xTile + 1) + (yTile + i) * sheet.getTilesPerRow(),
						color, sheet);

				screen.render(xOffset + 2 * modifier, yOffset + i * modifier, (xTile + 2) + (yTile + i) * sheet.getTilesPerRow(),
						color, sheet);

				screen.render(xOffset + 3 * modifier, yOffset + i * modifier, (xTile + 3) + (yTile + i) * sheet.getTilesPerRow(),
						color, sheet);
			}

			// render normal east/west
		} else if (direction == Direction.EAST || direction == Direction.WEST) {

			// TODO look into this
			if (direction == Direction.WEST && !powerSwinging) {
				xOffset -= modifier;
			}

			// render east/west display
			for (int i = 0; i < 2; i++) {

				screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + (modifier * i),
						xTile + (yTile + i) * sheet.getTilesPerRow(), color, flip, sheet);

				screen.render(xOffset + modifier, yOffset + (modifier * i), (xTile + 1) + (yTile + i) * sheet.getTilesPerRow(),
						color, flip, sheet);

				screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + (modifier * i),
						(xTile + 2) + (yTile + i) * sheet.getTilesPerRow(), color, flip, sheet);
			}

			// north south display
		} else {

			// render the awkward diagonals with an extra space
			if (diagonal && powerSwingModifier > 0) {
				for (int i = 0; i < 2; i++) {

					screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + (modifier * i),
							xTile + (yTile + i) * sheet.getTilesPerRow(), color, flip, sheet);

					screen.render(xOffset + modifier, yOffset + (modifier * i), (xTile + 1) + (yTile + i) * sheet.getTilesPerRow(),
							color, flip, sheet);

					screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + (modifier * i),
							(xTile + 2) + (yTile + i) * sheet.getTilesPerRow(), color, flip, sheet);
				}

				// render the normal up down
			} else {

				// render the core body 2x2
				screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile + yTile * sheet.getTilesPerRow(), color, flip,
						sheet);

				screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset,
						(xTile + 1) + yTile * sheet.getTilesPerRow(), color, flip, sheet);

				screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier,
						xTile + (yTile + 1) * sheet.getTilesPerRow(), color, flip, sheet);

				screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier,
						(xTile + 1) + (yTile + 1) * sheet.getTilesPerRow(), color, flip, sheet);

				// render the extra tip for swords
				if (direction == Direction.SOUTH) {

					screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier,
							xTile + (yTile + 2) * sheet.getTilesPerRow(), color, flip, sheet);

					screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier,
							(xTile + 1) + (yTile + 2) * sheet.getTilesPerRow(), color, flip, sheet);

					// render the extra tip for north
				} else if (length == MEDIUM && direction == Direction.NORTH) {

					screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset - modifier,
							xTile + (yTile - 1) * sheet.getTilesPerRow(), color, flip, sheet);

					screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset - modifier,
							(xTile + 1) + (yTile - 1) * sheet.getTilesPerRow(), color, flip, sheet);

				}

			}
		}

	}

	/**
	 * @return whether or not the sword is swinging
	 */
	public boolean isSwinging() {
		return isSwinging;
	}

	/**
	 * @return the direction of the sword
	 */
	public Direction getDirection() {
		return direction;
	}

}
