package javajesus.entities.animals;

import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.graphics.render_strategies.Render2x3;
import javajesus.graphics.render_strategies.Render3x3;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 *	A Cow Mob 
 */
public class Cow extends Animal {

	// color of the fox
	private static final int[] color = { 0xFF111111, 0xFFdbbeac, 0xFF6c452c, 0xFFece2b3, 0xFF583824 };

	// how fast the npcs toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;
	
	// Render Strategies
	Render2x3 renderNS = new Render2x3();
	Render3x3 renderEW = new Render3x3();

	/**
	 * @param level - level it is on
	 * @param x - x coord
	 * @param y - y coord
	 */
	public Cow(Level level, int x, int y) {
		super(level, "Cow", x, y, 24, 24, SpriteSheet.quadrapeds, 3, color, true);
	}

	@Override
	public byte getId() {
		return Entity.COW;
	}

	/**
	 * Display the cow on the screen
	 */
	public void render(Screen screen) {
		super.render(screen);

		// default color
		int[] color = Cow.color;

		// change color if knockback
		if (isHit) {
			color = mobHitColor;
		}

		// no x or y offset, use the upper left corner as absolute
		int xCoordinate = getX(), yCoordinate = getY();

		// the horizontal position on the spritesheet
		int xTile = 0;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile = 15;
			if (isMoving)
				xTile = flip? 17: 19;
		} else if (getDirection() == Direction.SOUTH) {
			xTile = 0;
			if (isMoving)
				xTile = flip ? 2:4;
		} else if (isLatitudinal()) {
			xTile = 6;
			if (isMoving) {
				xTile = flip ? 9 : 12;
			}
			flip = getDirection() == Direction.WEST;
		}

		// the dead sprite has a certain location
		if (isDead()) {
			xTile = 21;
		}
		if( isLatitudinal() || isDead()) {
			renderEW.renderNormal(screen, xCoordinate, yCoordinate, xTile, yTile, flip, getSpriteSheet(), color);
		} else {
			renderNS.renderNormal(screen, xCoordinate, yCoordinate, xTile, yTile, false, getSpriteSheet(), color);
		}

	}

}
