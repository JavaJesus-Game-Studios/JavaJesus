package javajesus.entities.effects;

import javajesus.SoundHandler;
import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * An explosion that damages all mobs in its vicinity
 */
public class SlashEffect extends Entity {

	// internal timer
	private int tickCount;

	private static final int LENGTH = 15;

	// spritesheet for slash
	private static final SpriteSheet sheet = SpriteSheet.dynamic;

	// color set of the explosion
	private static final int[] color = { 0xFF737373, 0xFF404040, 0xFF0000FF };

	private static final int xTile = 0, yTile = 2;

	private boolean mirror;

	/**
	 * Creates a slash effect
	 * 
	 * @param level - the level it is on
	 * @param x     - the x coord
	 * @param y     - the y coord
	 */
	public SlashEffect(Level level, int x, int y, boolean mirror) {
		super(level, x, y);

		// change to slash sound
		SoundHandler.playGash();
		
		setBounds(x, y, 8, 8);

		this.mirror = mirror;
		
	}

	/**
	 * Updates the effect
	 */
	public void tick() {

		if (++tickCount % LENGTH == 0) {
			getLevel().remove(this);
		}

	}

	/**
	 * Displays the effect on the screen
	 */
	public void render(Screen screen) {
		screen.render(getX() + (mirror ? 8 : 0), getY(), xTile, yTile, sheet, mirror, color);
	}

	/**
	 * Slash effects won't be saved
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
