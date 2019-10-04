package javajesus.entities.monsters;

import javajesus.JavaJesus;
import javajesus.entities.Entity;
import javajesus.graphics.IRenderStrategy;
import javajesus.graphics.Screen;
import javajesus.graphics.render_strategies.Render2x2;
import javajesus.graphics.render_strategies.Render2x3;
import javajesus.graphics.render_strategies.Render3x2;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class Skeleton extends Monster {
	
	// dimensions of the skeleton
	private static final int WIDTH = 16, HEIGHT = 16;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 3;
		
	// base stats
	private static final int BASE_STRENGTH = 1, BASE_DEFENSE = 1;
	
	// color set of skeleton
	private static final int[] color =  { 0xFF111111, 0xFF700000, 0xFFDBA800, 0, 0 };
	
	// Render Strategies needed
	private IRenderStrategy renderSkeleton;
	private Render2x2 renderNormal;
	private Render2x3 renderSSwing;
	private Render3x2 renderEWSwing;

	
	/**
	 * @param level - level it is on
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Skeleton(Level level, int x, int y) {
		super(level, "Skeleton", x, y, 1, WIDTH, HEIGHT, 10, 100, 50);
		
		// Initialize Render Strategies
		renderNormal = new Render2x2();
		renderSSwing = new Render2x3();
		renderEWSwing = new Render3x2();
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

		// no x or y offset, use the upper left corner as absolute
		int xCoordinate = getX(), yCoordinate = getY();

		// the horizontal position on the spritesheet
		int xTile = 0;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;
		
		xTile = this.renderHelper(xTile, flip);
		
		renderSkeleton.renderNormal(screen, xCoordinate, yCoordinate - 1, xTile, yTile, flip, getSpriteSheet(), color);
	}
	/**
	 * Helper method that sets the correct render strategy and finds the correct xTile for the Sprite
	 * @param xTile the xTile to find
	 * @param flip determines which xTile to use whilst walking
	 */
	private int renderHelper(int xTile, boolean flip) {
		// adjust sprite sheet offsets
		if (getDirection() == Direction.NORTH) {
			if(!isShooting) {
				xTile = 14;
				if (isMoving) {
					xTile = (flip ? 16 :18);
					flip = false;
				}
			} else
				xTile = 28;
			renderSkeleton = renderNormal;
		} else if (getDirection() == Direction.SOUTH) {
			if( !isShooting ) {
			xTile = 0;
				if (isMoving) {
					xTile = (flip ? 2 : 4);
					flip = false;
				}
				renderSkeleton = renderNormal;
			} else {
				xTile = 20;
				renderSkeleton = renderSSwing;
			}
		} if( getDirection() == Direction.EAST) {
			if( !isSwinging ) {
				xTile = 6;
				if(isMoving) {
					xTile = (flip ? 6:8);
					flip = false;
				}
			}else {
				xTile = 22;
				renderSkeleton = renderEWSwing;
			}
		} if( getDirection() == Direction.WEST) {
			if( !isSwinging ) {
				xTile = 10;
				if(isMoving) {
					xTile = (flip ? 10:12);
					flip = false;
				}
			}else {
				xTile = 25;
				renderSkeleton = renderEWSwing;
			}
		}
		
		// death image
		if (isDead()) {
			xTile = 30;
			renderSkeleton = renderNormal;
		}
		return xTile;
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
