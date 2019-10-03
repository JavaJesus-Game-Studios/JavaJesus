package javajesus.entities.animals;

import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.graphics.render_strategies.Render2x2;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 *	A Cat Mob 
 */
public class Cat extends Animal {
	
	// color of the cat
	private static final int[] color = { 0xFF111111, 0xFFf3cdf6, 0xFFeaeaea, 0xFFa6a6a6, 0xFF111111 };
	private Render2x2 renderCat = new Render2x2();
	/**
	 * @param level - level it is on
	 * @param x - x coord
	 * @param y - y coord
	 */
	public Cat(Level level, int x, int y) {
		super(level, "Cat", x, y, 16, 16, SpriteSheet.quadrapeds, 10, color, true);
	}
	public void render(Screen screen) {
		super.render(screen);
		// default color
		int[] color = Cat.color;

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
			xTile = 10;
			if (isMoving)
				xTile = flip? 12: 14;
			} else if (getDirection() == Direction.SOUTH) {
			xTile = 0;
			if (isMoving)
				xTile = flip ? 2:4;
		} else if (isLatitudinal()) {
			xTile = 6;
			if (isMoving) {
				xTile = flip ? 6 : 8;
			}
			flip = getDirection() == Direction.WEST;
		}

		// the dead sprite has a certain location
		if (isDead()) {
			xTile = 18;
		}
		if( isLatitudinal())
			renderCat.renderNormal(screen, xCoordinate, yCoordinate, xTile, yTile, flip, getSpriteSheet(), color);
		if( isLongitudinal())
			renderCat.renderNormal(screen, xCoordinate, yCoordinate, xTile, yTile, false, getSpriteSheet(), color);
	}

	@Override
	public byte getId() {
		return Entity.CAT;
	}

}
