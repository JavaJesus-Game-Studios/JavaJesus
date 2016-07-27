package game.entities.projectiles;

import game.SoundHandler;
import game.entities.Mob;
import game.entities.particles.BlackHole;
import game.graphics.SpriteSheet;
import level.Level;
import utility.Direction;

/*
 * BlackHoleDetonators create black holes!
 */
public class BlackHoleDetonator extends Projectile {

	private static final long serialVersionUID = 2546446873996072596L;

	// the colorset for all detonators
	private static final int[] color = { 0xFF000000, 0xFF000000, 0xFFFFFF00 };

	/**
	 * Creates a detonator
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param xPos
	 *            the x coord to move to
	 * @param yPos
	 *            the y coord to move to
	 * @param mob
	 *            the mob that is firing
	 * @param damage
	 *            the damage of this detonator
	 */
	public BlackHoleDetonator(Level level, int x, int y, int xPos, int yPos, Mob mob, int damage) {
		super(level, x, y, 2, 1, SpriteSheet.particles.boxes, 6, xPos, yPos, mob, damage, SoundHandler.explosion);
	}

	/**
	 * Creates a detonator with a simple direction
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param direction
	 *            the direction the missile should move
	 * @param mob
	 *            the mob that is firing
	 * @param damage
	 *            the damage of this detonator
	 */
	public BlackHoleDetonator(Level level, int x, int y, Direction direction, Mob mob, int damage) {
		super(level, x, y, 2, 1, SpriteSheet.particles.boxes, 6, direction, mob, damage, SoundHandler.explosion);
	}

	@Override
	protected void onDestroyed() {
		
		getLevel().add(new BlackHole(getLevel(), getX(), getY()));
		super.onDestroyed();

	}

	@Override
	protected int[] getColor() {
		return color;
	}

}
