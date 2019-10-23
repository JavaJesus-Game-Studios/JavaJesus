package javajesus.entities.monsters;

import java.awt.Color;

import javajesus.JavaJesus;
import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.entities.Player;
import javajesus.graphics.Animation;
import javajesus.graphics.Screen;
import javajesus.graphics.render_strategies.Render2x2;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class Goose extends Monster {

	// dimensions of the EvilFox
	private static final int WIDTH = 16, HEIGHT = 16;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 6;
	
	// base stats
	private static final int BASE_STRENGTH = 1, BASE_DEFENSE = 1;

	// color set of a EvilFox
	private static final int[] color = { 0xFF111111, 0xFF2c2417, 0xFF4c412e, 0xFF3f3626, 0xFF1e1911 };

	// Render Strategy
	Render2x2 renderGoose = new Render2x2();
	Animation gooseAttack = new Animation();
	/**
	 * Creates a EvilFox
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param speed - how fast the EvilFox moves
	 * @param health - the base health
	 */
	public Goose(Level level, int x, int y, int speed, int health) {
		super(level, "Goose", x, y, speed, WIDTH, HEIGHT, 26, health, 50);
	}
	
	/**
	 * Creates a Goose
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param health - the base health
	 */
	public Goose(Level level, int x, int y, int health) {
		this(level, x, y, 1, health);
	}
	
	/**
	 * Creates a EvilFox
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 */
	public Goose(Level level, int x, int y) {
		this(level, x, y, 2, 100);
	}

	/**
	 * Displays the EvilFox to the screen
	 */
	public void render(Screen screen) {
		super.render(screen);
		
		// default color
		int[] color = Goose.color;

		// change color if knockback
		if (isHit) {
			color = mobHitColor;
		}

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xCoordinate = getX(), yCoordinate = getY();

		// the horizontal position on the spritesheet
		int xTile = 0;
		int[] xTiles = new int[]{0,0};

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		//This Code gets the xTile offsets on the SpriteSheet
		if (getDirection() == Direction.NORTH) {
			xTile = 10;
			if(isMoving)
				xTile += (flip ? 2: 4);
			if (isShooting) {
				xTiles = new int[]{24, 26};
			}
		} else if (getDirection() == Direction.SOUTH) {
			xTile = 0;
			if(isMoving)
				xTile += (flip ? 2: 4);
			if (isShooting) {
				xTiles = new int[]{16, 18};
			}
		} else {
			xTile = 6;
			if(isMoving)
				xTile += (flip ? 0: 2);
			if (isShooting) {
				xTiles = new int[]{20, 22};
			}
			flip = getDirection() == Direction.WEST;
		}

		// death image
		//TODO DRAW DEATH SPRITE
		if (isDead())
			xTile = 0;
		
		// Renders the Goose
		if(!isShooting)
			renderGoose.renderNormal(screen, xCoordinate, yCoordinate, xTile, yTile, flip, getSpriteSheet(), color);
		if(isShooting)
			gooseAttack.renderAnimation(screen, xCoordinate, yCoordinate, xTiles, yTile, 2, 2, 4, getSpriteSheet(),color);
	}

	/**
	 * Text to player
	 */
	public void speak(Player player) {
		isTalking = true;
		MessageHandler.displayText("Honk.", Color.white);
		return;
	}
	
	/**
	 * Goose member specific loot
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
		return Entity.GOOSE;
	}
}

