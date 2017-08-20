package javajesus.entities.monsters;

import javajesus.JavaJesus;
import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class Skeleton extends Monster {
	
	// dimensions of the bandito
	private static final int WIDTH = 16, HEIGHT = 24;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 3;
		
	// base stats
	private static final int BASE_STRENGTH = 7, BASE_DEFENSE = 5;
	
	// color set of skeleton
	private static final int[] color =  { 0xFF111111, 0xFF700000, 0xFFDBA800, 0, 0 };

	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Skeleton(Level level, int x, int y) {
		super(level, "Skeleton", x, y, 1, WIDTH, HEIGHT, 10, 100, 50);
	}
	
	/**
	 * Displays the Bandito to the screen
	 */
	public void render(Screen screen) {
		super.render(screen);
		
		// default color
		int[] color = Skeleton.color;

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
			if (isShooting) {
				xTile = 23;
			} else {
				xTile = 12 + (isMoving ? 2 : 4);
				flip = false;
			}
			
		} else if (getDirection() == Direction.SOUTH) {
			
			if (isShooting) {
				xTile = 18;
			} else {
				xTile = isMoving ? 2 : 4;
				flip = false;
			}
		} else {
			xTile = 6 + (flip ? 3 : 0);
			
			if (isShooting) {
				xTile = 20;
			}
			
			flip = getDirection() == Direction.WEST;
		}
		
		// death image
		if (isDead())
			xTile = 25;
		
		// base 2x2 quadrant y-axis symmetry 
		if (isLongitudinal() || isDead()) {

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
		
		// render the extra sword chunks
		if (isShooting && isLongitudinal()) {
			
			// Lower sword
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier, xTile, yTile + 2, getSpriteSheet(),
			        flip, color);

			// Lower sword
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + 2 * modifier, xTile + 1, yTile + 2,
			        getSpriteSheet(), flip, color);
		} else if (isLatitudinal() && !isDead()) {
			
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
			
		}
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
		return Entity.SKELETON;
	}

}
