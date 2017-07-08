package javajesus.entities.projectiles;

import javax.sound.sampled.Clip;

import javajesus.entities.Mob;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A bullet projectile
 */
public class Bullet extends Projectile {

	private static final long serialVersionUID = 6386517640048489710L;

	// the colorset for all fireballs
	private static final int[] color = { 0xFF000000, 0xFF000000, 0xFFFFFF00 };

	// pixel size of a fireball
	private static final int SIZE = 2;

	/**
	 * Creates a bullet
	 * @param level the level it is on
	 * @param x the x coord
	 * @param y the y coord
	 * @param xPos the x coord to move  to
	 * @param yPos the y coord to move to
	 * @param mob the mob that is firing
	 * @param damage the damage of this bullet
	 * @param clip the Sound of this projectile
	 */
	public Bullet(Level level, int x, int y, int xPos, int yPos, Mob mob, int damage, Clip clip) {
		super(level, x, y, SIZE, SIZE, 1, 6, xPos, yPos, mob, damage, clip);
		
		adjustOffset(mob.getDirection());
	}

	/**
	 * Creates a bullet with a simple direction
	 * @param level the level it is on
	 * @param x the x coord
	 * @param y the y coord
	 * @param direction the directon the bullet should move
	 * @param mob the mob that is firing
	 * @param damage the damage of this bullet
	 * @param clip the Sound of this projectile
	 */
	public Bullet(Level level, int x, int y, Direction direction, Mob mob, int damage, Clip clip) {
		super(level, x, y, SIZE, SIZE, 1, 6, direction, mob, damage, clip);
		
		adjustOffset(direction);
	}
	
	/**
	 * Adjusts the offset of the arrow
	 * 
	 */
	private void adjustOffset(Direction direction) {
		switch (direction) {
		case NORTH:
			this.tileNumber = 1 + getSpriteSheet().getTilesPerRow();
			return;
		case SOUTH:
			this.tileNumber = 1 + getSpriteSheet().getTilesPerRow();
			return;
		case EAST:
			this.tileNumber = 1;
			return;
		default:
			this.tileNumber = 1;
			return;
		}
	}

	/**
	 * @return the colorset of the bullet
	 */
	@Override
	protected int[] getColor() {
		return color;
	}

}
