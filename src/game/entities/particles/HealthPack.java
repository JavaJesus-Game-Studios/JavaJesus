package game.entities.particles;

import java.util.Random;

import game.SoundHandler;
import game.entities.Mob;
import level.Level;

/*
 * A health pack can be picked up by the player to restore health
 */
public class HealthPack extends Particle {

	private static final long serialVersionUID = -7889833550875856663L;

	// random generator for location on map
	private static final Random random = new Random();

	// sprite size
	private static int SIZE = 8;

	/**
	 * Creates a Health Pack
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the base x coord
	 * @param y
	 *            the base y coord
	 * @param randomize
	 *            whether or not to randomize the healthpack around the base
	 *            coordinates
	 */
	public HealthPack(Level level, double x, double y, boolean randomize) {
		super(level, x, y, 9, new int[] { 0xFFF6F4EE, 0xFFFFFFFF, 0xFFFF0000 });

		if (randomize) {
			setX(random.nextInt(400) - 200);
			setX(random.nextInt(400) - 200);
		}

		this.setBounds(getX(), getY(), SIZE, SIZE);
	}

	/**
	 * Checks for collision with any mob
	 */
	public void tick() {
		for (Mob mob : getLevel().getMobs()) {

			if (getBounds().intersects(mob.getBounds())) {
				mob.heal();
				getLevel().remEntity(this);
				SoundHandler.play(SoundHandler.click);
			}

		}
	}

}
