package ca.javajesus.game.entities;

import ca.javajesus.game.JavaRectangle;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class FireEntity extends Entity  {

	private long lastIterationTime;
	private int delay;
	private int xTile;
	private int yTile = 15;
	private int[] color = { 0xFFF7790A, 0xFFF72808, 0xFF000000 };

	public FireEntity(Level level, int x, int y) {
		super(level);
		this.x = x;
		this.y = y;
		this.lastIterationTime = System.currentTimeMillis();
		this.delay = 100;
		this.bounds = new JavaRectangle(8, 8, this);
		this.bounds.setLocation(x, y);
	}

	public void tick() {
		if ((System.currentTimeMillis() - lastIterationTime) >= (delay)) {
			lastIterationTime = System.currentTimeMillis();
			if (xTile < 4) {
				xTile++;
			} else {
				xTile = 0;
			}
		}

	}

	public int getXTile() {
		return xTile;
	}

	public void render(Screen screen) {

		screen.render(x, y, xTile + yTile * SpriteSheet.tiles.boxes, color, 0, 1, SpriteSheet.tiles);

	}

}
