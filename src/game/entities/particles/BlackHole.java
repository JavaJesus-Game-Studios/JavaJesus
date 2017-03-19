package game.entities.particles;

import game.Display;
import game.SoundHandler;
import game.entities.Mob;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import level.Level;

/*
 * A black hole sucks in mobs and deals extreme damage to all mobs in its vicinity
 * Dev weapon only
 */
public class BlackHole extends Particle {

	private static final long serialVersionUID = 2827325538515820858L;

	// yTile offset
	private int posNumber;

	// internal timer
	private int tickCount = 1;

	// radius of the black hole
	private Ellipse2D.Double radius;

	// size of the black hole
	private static final int SIZE = 1024;

	// randomly creates explosions
	private static final Random random = new Random();

	// the number of ticks per animation segment
	private static final int ANIMATION_LENGTH = 20;

	// damage per tick to mobs inside
	private static final int DPT = 1;

	/**
	 * Creates a black hole
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord AT THE CENTER
	 * @param y
	 *            the y coord A THE CENTER
	 */
	public BlackHole(Level level, int x, int y) {
		super(level, x, y, 0, new int[] { 0xFF000000, 0xFF000000, 0xFF000000 });
		
		this.setBounds(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
		
		setSpriteSheet(SpriteSheet.explosions);
		posNumber = getTileNumber();
		radius = new Ellipse2D.Double(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
		SoundHandler.play(SoundHandler.explosion);

		// darken the screen
		Display.getScreen().setShader(983082);
	}

	/**
	 * Updates the black hole
	 */
	public void tick() {

		// increment animation
		if (tickCount % ANIMATION_LENGTH == 0) {
			posNumber += 4;
		}

		// animation is over
		if (posNumber > getTileNumber() + (14 * 4)) {

			// remove the shader and the blackhole
			Display.getScreen().setShader(0);
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

		tickCount++;
	}

	/**
	 * Display the black hole
	 */
	public void render(Screen screen) {
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				screen.render(getX() + (j * 24) - 24, getY() + (i * 24) - 48,
						posNumber + j + (i * getSpriteSheet().getNumBoxes()), getColor(), false, 3, getSpriteSheet());
			}
		}

	}

}
