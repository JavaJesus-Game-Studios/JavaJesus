package game;

import java.awt.Rectangle;

import game.graphics.Screen;
/*
 * Something where order of rendering matters
 * Background = Render First
 * Foreground = Render Last
 */
public interface Hideable {

	/**
	 * Determines if this object is behind a building
	 * @return true if behind a building
	 */
	public boolean isBehindBuilding();
	
	/**
	 * Forces a hideable to be an entity with bounds
	 * @return the bounds of the entity
	 */
	public Rectangle getBounds();
	
	/**
	 * Forces a hideaable to be an entity that will be rendered
	 * @param screen the screen to display it on
	 */
	public void render(Screen screen);

}
