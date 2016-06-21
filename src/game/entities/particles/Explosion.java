package game.entities.particles;

import game.SoundHandler;
import game.entities.Mob;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import level.Level;

/*
 * An explosion that damages all mobs in its vicinity
 */
public class Explosion extends Particle {

	private static final long serialVersionUID = 3716434059942612881L;

	// y offset on spritesheet
	private int posNumber;
	
	// internal timer
	private int tickCount = 1;

	// the number of ticks per animation segment
	private static final int ANIMATION_LENGTH = 5;
	
	// damage per tick to mobs inside
	private static final int DPT = 1;

	/**
	 * Creates an explosion
	 * @param level the level it is on
	 * @param x the x coord AT THE CENTER
	 * @param y the y coord AT THE CENTER
	 */
	public Explosion(Level level, int x, int y) {
		super(level, x - 8, y - 8, 4 * SpriteSheet.explosions.boxes, new int[] { 0xFFFF9900, 0xFFFF3C00, 0xFFFF0000 });
		setSpriteSheet(SpriteSheet.explosions);
		this.posNumber = getTileNumber();
		SoundHandler.play(SoundHandler.explosion);
		setBounds(getX(), getY(), 16, 16);
	}

	/**
	 * Updates the explosion
	 */
	public void tick() {

		// updates the position
		if (tickCount % ANIMATION_LENGTH == 0) {
			posNumber += 2;
		}

		// if no more animations, remove it
		if (posNumber > getTileNumber() + 26) {
			getLevel().remove(this);
		}

		// damage all mobs in its radius
		for (Mob mob : getLevel().getMobs()) {
			if (getBounds().intersects(mob.getBounds())) {
				mob.damage(DPT);
			}
		}

		tickCount++;
	}

	/**
	 * Displays the explosion
	 */
	public void render(Screen screen) {
		screen.render(getX(), getY(), posNumber, getColor(), getSpriteSheet());
		screen.render(getX() + 8, getY(), posNumber + 1, getColor(), getSpriteSheet());
		screen.render(getX(), getY() + 8, posNumber + getSpriteSheet().boxes, getColor(), getSpriteSheet());
		screen.render(getX() + 8, getY() + 8, posNumber + 1 + getSpriteSheet().boxes, getColor(), getSpriteSheet());
	}

}
