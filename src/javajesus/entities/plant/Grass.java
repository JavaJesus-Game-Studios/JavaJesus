package javajesus.entities.plant;

import javajesus.dataIO.EntityData;
import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public abstract class Grass extends Entity{
	
	
	private Sprite sprite;

	/**
	 * Creates a plant from a specified sprite
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param sprite - the sprite to use
	 */
	public Grass(Level level, int x, int y, int width, int height, Sprite sprite) {
		super(level, x, y);

		this.sprite = sprite;

		// makes smoother collision points for the player
		setBounds(getX(), getY(), width, height);

	}
	/**
	 * Displays the grass on the screen
	 */
	public void render(Screen screen) {
		screen.render(getX(), getY(), null, sprite);
	}
	@Override
	public int getLayer() {
		return -1;
	}
	public void tick() {}
	
	@Override
	public long getData() {
		return EntityData.type1(getX(), getY());
	}
}
