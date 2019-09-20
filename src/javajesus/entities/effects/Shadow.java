package javajesus.entities.effects;

import javajesus.graphics.Screen;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

public class Shadow {

	private Sprite sprite;
	private int x;
	private int y;
	private int width;
	

	public Shadow(Level level, int x, int y, Sprite sprite) {
		// TODO Auto-generated constructor stub
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	/**
	 * Displays the grass on the screen
	 */
	public void render(Screen screen, int x, int y) {
			screen.render(x, y, null, sprite);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}


}
