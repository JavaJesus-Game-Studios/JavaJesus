package javajesus.entities.effects;

import java.awt.geom.Ellipse2D;
import java.util.Random;

import javajesus.entities.Entity;
import javajesus.entities.Mob;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * A black hole sucks in mobs and deals extreme damage to all mobs in its vicinity
 */
public class BlackHole extends Entity {

	// xTile offset
	private int xOffset;

	// internal timer
	private int tickCount;

	// radius of the black hole
	private final Ellipse2D.Double radius;

	// size of the black hole
	private static final int SIZE = 1024;

	// randomly creates explosions
	private static final Random random = new Random();

	// the number of ticks per animation segment
	private static final int ANIMATION_LENGTH = 20;

	// damage per tick to mobs inside
	private static final int DPT = 1;

	// spritesheet of black hole
	private static final SpriteSheet sheet = SpriteSheet.explosionLarge;

	// color of the black hole
	private static final int[] color = { 0xFF000000, 0xFF000000, 0xFF000000 };

	// box size on spritesheet
	private static final int MODIFIER = 8;

	/**
	 * Creates a black hole
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord AT THE CENTER
	 * @param y - the y coord A THE CENTER
	 */
	public BlackHole(Level level, int x, int y) {
		super(level, x, y);

		// set collision bounds
		setBounds(getX() - (MODIFIER * 2), getY() - (MODIFIER * 2), MODIFIER * 4, MODIFIER * 4);
		radius = new Ellipse2D.Double(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);

	}

	/**
	 * Updates the black hole
	 */
	public void tick() {

		// increment animation
		if (++tickCount % ANIMATION_LENGTH == 0) {
			xOffset += 4;
		}

		// animation is over
		if (xOffset > (14 * 4)) {

			// remove the blackhole
			getLevel().remove(this);

		}

		// randomly create an explosion
		if (random.nextInt(10) == 0) {
			getLevel().add(
			        new Explosion(getLevel(), random.nextInt(300) - 150 + getX(), random.nextInt(300) - 150 + getY()));
		}

		// suck in all the mobs!
		for (Mob mob : getLevel().getMobs()) {

			// the change in x and y
			int dx = 0, dy = 0;

			if (radius.intersects(mob.getBounds())) {

				mob.damage(DPT);

				if (mob.getX() > getX()) {
					dx--;
				} else if (mob.getX() < getX()) {
					dx++;
				}
				if (mob.getY() > getY()) {
					dy--;
				} else if (mob.getY() < getY()) {
					dy++;
				}
			}

			// move the mob
			if (dx != 0 || dy != 0) {
				mob.move(dx, dy);
			}

		}
	}

	/**
	 * Display the black hole
	 */
	public void render(Screen screen) {

		// top to bottom
		for (int i = 0; i < 4; i++) {

			// left to right
			for (int j = 0; j < 4; j++) {
				screen.render(getX() - (MODIFIER * 2) + (j * MODIFIER), getY() - (MODIFIER * 2) + (i * MODIFIER),
				        xOffset + j, i, sheet, false, color);
			}
		}

	}

	/**
	 * Black Holes won't be saved
	 */
	@Override
	public byte getId() {
		return -1;
	}

}
