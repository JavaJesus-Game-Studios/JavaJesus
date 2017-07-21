/**
 * @author shtevay
 * ONLY COVERS SMALL EXPLOSION CURRENTLY WILL ADD LARGE EXPLOSION LATER
 */
package javajesus.entities.effects;

import javajesus.SoundHandler;
import javajesus.entities.Entity;
import javajesus.entities.Mob;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * An explosion that damages all mobs in its vicinity
 */
public class Explosion extends Entity {

	// x offset that increases over time
	private int xOffset;

	// internal timer
	private int tickCount;

	// the number of ticks per animation segment
	private static final int ANIMATION_LENGTH = 5;

	// the number of animation segments
	private static final int EXPLOSION_LENGTH = 14;

	// damage per tick to mobs inside
	private static final int DPT = 1;

	// spritesheet for explosions
	private static final SpriteSheet sheet = SpriteSheet.explosionSmall;

	// color set of the explosion
	private static final int[] color = { 0xFFFF9900, 0xFFFF3C00, 0xFFFF0000 };

	// size of a box in the spritesheet
	private static final int MODIFIER = 8;

	/**
	 * Creates an explosion effect
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord AT THE CENTER
	 * @param y - the y coord AT THE CENTER
	 */
	public Explosion(Level level, int x, int y) {
		super(level, x, y);

		// play an explosion sound
		SoundHandler.play(SoundHandler.explosion);

		// set collision bounds
		setBounds(getX() - MODIFIER, getY() - MODIFIER, MODIFIER * 2, MODIFIER * 2);
	}

	/**
	 * Updates the explosion
	 */
	public void tick() {

		// updates the position
		if (++tickCount % ANIMATION_LENGTH == 0) {
			xOffset += 2;
		}

		// if no more animations, remove it
		if (xOffset >= (EXPLOSION_LENGTH * 2)) {
			getLevel().remove(this);
		}

		// damage all mobs in its radius
		for (Mob mob : getLevel().getMobs()) {
			if (getBounds().intersects(mob.getBounds())) {
				mob.damage(DPT);
			}
		}

	}

	/**
	 * Displays the explosion on the screen
	 */
	public void render(Screen screen) {

		// render top to bottom
		for (int i = 0; i < 2; i++) {

			// render left to right
			for (int j = 0; j < 2; j++) {

				screen.render(getX() - MODIFIER + (MODIFIER * j), getY() - MODIFIER + (MODIFIER * i), xOffset + j, i,
				        sheet, false, color);

			}
		}

	}

	/**
	 * Explosions won't be saved
	 */
	@Override
	public byte getId() {
		return -1;
	}

	@Override
	public long getData() {
		return 0;
	}

}
