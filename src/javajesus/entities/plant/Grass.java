package javajesus.entities.plant;

import javajesus.dataIO.EntityData;
import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public abstract class Grass extends Entity {

	private Sprite sprite;
	protected int col1;
	protected int col2;

	/**
	 * Creates a plant from a specified sprite
	 * 
	 * @param level  - the level it is on
	 * @param x      - the x coord
	 * @param y      - the y coord
	 * @param sprite - the sprite to use
	 */
	public Grass(Level level, int x, int y, int width, int height, Sprite sprite) {
		super(level, x, y);

		this.sprite = sprite;
		this.col1 = 0xFF3fa235;
		this.col2 = 0xFF277c1d;

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
		return Integer.MAX_VALUE;
	}

	public void tick() {

	}
	
	public int getCol1() {
		return col1;
	}
	
	public int getCol2() {
		return col2;
	}
	
	@Override
	public long getData() {
		return EntityData.type1(getX(), getY());
	}
}
