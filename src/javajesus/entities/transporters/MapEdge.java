package javajesus.entities.transporters;

import javajesus.graphics.Screen;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * A map transporter is placed on the edges of a level to transition  between two outisde levels
 */
public class MapEdge extends Transporter {

	// which level it leads to
	private String next;

	/**
	 * Creates a EdgeTransporter
	 * 
	 * @param currentLevel - level it is on
	 * @param x - upper left x coord
	 * @param y - upper left y coord
	 * @param edge - which edge it is on
	 * @param nextLevel - the next level it leads to
	 */
	public MapEdge(Level currentLevel, int x, int y, Direction edge, String nextLevel) {
		super(currentLevel, x, y, 0, 0, null);
		
		// instance data
		this.next = nextLevel;
		calcDirection(edge);

	}
	
	/**
	 * Sets the  bounds based on the direction
	 * @param side - side it is on
	 */
	private void calcDirection(Direction side) {
		getBounds().setSize(0, 0); // TODO
	}
	
	/**
	 * @return the next level
	 * TODO
	 */
	@Override
	public Level getNextLevel() {
		switch (next) {
		default:
			return null;
		}
	}

	/**
	 * Blank because it is invisible
	 */
	public void render(Screen screen) {}

}
