package javajesus.utility;

/*
 * Utility class  for rendering text
 */
public class LevelText {
	
	// instance data of a text object
	private final String message;
	private int x, y;
	private int[] color;
	private int scale;
	
	/**
	 * Creates a text object to be rendered
	 * 
	 * @param message - message to display
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param color - color scale
	 * @param scale - size
	 */
	public LevelText(String message, int x, int y, int[] color, int scale) {
		
		// instance data
		this.message = message;
		this.x = x;
		this.y = y;
		this.color = color;
		this.scale = scale;
	}

	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * @return the x
	 */
	public final int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public final int getY() {
		return y;
	}

	/**
	 * @return the color
	 */
	public final int[] getColor() {
		return color;
	}

	/**
	 * @return the scale
	 */
	public final int getScale() {
		return scale;
	}


}
