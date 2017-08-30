package javajesus.entities.monsters;

import javajesus.entities.Entity;

import java.awt.Color;

import javajesus.JavaJesus;
import javajesus.MessageHandler;
import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A EvilFox is a powerful monster that strikes fear into any foe
 */
public class EvilFox extends Monster {

	// dimensions of the EvilFox
	private static final int WIDTH = 16, HEIGHT = 16;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;
	
	// base stats
	private static final int BASE_STRENGTH = 15, BASE_DEFENSE = 5;

	// color set of a EvilFox
	private static final int[] color = { 0xFF111111, 0xFF2c2417, 0xFF4c412e, 0xFF3f3626, 0xFF1e1911 };

	/**
	 * Creates a EvilFox
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param speed - how fast the EvilFox moves
	 * @param health - the base health
	 */
	public EvilFox(Level level, int x, int y, int speed, int health) {
		super(level, "EvilFox", x, y, speed, WIDTH, HEIGHT, 21, health, 50);
	}
	
	/**
	 * Creates a EvilFox
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param health - the base health
	 */
	public EvilFox(Level level, int x, int y, int health) {
		this(level, x, y, 1, health);
	}
	
	/**
	 * Creates a EvilFox
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 */
	public EvilFox(Level level, int x, int y) {
		this(level, x, y, 1, 100);
	}

	/**
	 * Displays the EvilFox to the screen
	 */
	public void render(Screen screen) {
		super.render(screen);
		
		// default color
		int[] color = EvilFox.color;

		// change color if knockback
		if (isHit) {
			color = mobHitColor;
		}

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal position on the spritesheet
		int xTile = 0;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile = 8 + (isMoving ? 2 : 0);
			if (isShooting) {
				xTile = 22 + (flipAttack ? 2 : 0);
			}
		} else if (getDirection() == Direction.SOUTH) {
			xTile = 0 + (isMoving ? 2 : 0);
			if (isShooting) {
				xTile = 12 + (flipAttack ? 2 : 0);
			}
		} else {
			xTile = 4 + (flip ? 2 : 0);
			if (isShooting) {
				xTile = 16 + (flipAttack ? 3 : 0);
			}
			flip = getDirection() == Direction.WEST;
		}

		// death image
		if (isDead())
			xTile = 26;
		
		// requires width of 3
		if (isShooting && isLatitudinal()) {
			
			// move x offset over if west
			if (getDirection() == Direction.WEST) {
				xOffset -= 8;
			}
			
			// iterate top to bottom
			for (int i = 0; i < 2; i++) {
				
				// Left body
				screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + i * modifier, xTile, yTile + i, getSpriteSheet(), flip, color);
				
				// Middle body
				screen.render(xOffset + modifier, yOffset + i * modifier, xTile + 1, yTile + i, getSpriteSheet(), flip, color);
				
				// Right body
				screen.render(xOffset + (modifier * 2) - (modifier * (flip ? 2 : 0)), yOffset + i * modifier, xTile + 2, yTile + i, getSpriteSheet(), flip, color);
				
			}
			
		} else {
			
			// Upper body
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile, yTile, getSpriteSheet(), flip, color);

			// Upper body
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset, xTile + 1, yTile, getSpriteSheet(),
			        flip, color);

			// Lower Body
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile, yTile + 1, getSpriteSheet(),
			        flip, color);

			// Lower Body
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile + 1, yTile + 1,
			        getSpriteSheet(), flip, color);
			
		}

	}

	/**
	 * Text to player
	 */
	public void speak(Player player) {
		isTalking = true;
		MessageHandler.displayText("Come child, and I will tell you the truth of the LORD.", Color.white);
		return;
	}
	
	/**
	 * Gang member specific loot
	 */
	protected void dropLoot() {
		
		// drop 2x basic loot first
		super.dropLoot();
		super.dropLoot();
		
	}

	@Override
	public int getStrength() {
		return Math.round(BASE_STRENGTH * JavaJesus.difficulty);
	}

	@Override
	public int getDefense() {
		return Math.round(BASE_DEFENSE * JavaJesus.difficulty);
	}

	@Override
	public byte getId() {
		return Entity.EVILFOX;
	}
}
