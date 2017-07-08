package javajesus.graphics;

/*
 * Utility class for rendering from the font sprite sheet
 */
public class JJFont {
	
	// list of chars on the spritesheet
	private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + "abcdefghijklmnopqrstuvwxyz      "
			+ "0123456789.,:;'\"!?$%()-=+/      ";
	
	/**
	 * Renders text to the screen
	 * 
	 * @param msg - message to display
	 * @param screen - screen to render to
	 * @param x - x position
	 * @param y - y position
	 * @param color - color scheme
	 * @param scale - size of the text
	 */
	public static void render(String msg, Screen screen, int x, int y, int[] color, int scale) {
		
		// iterate through chars in the message
		for (int i = 0; i < msg.length(); i++) {
			int charIndex = chars.indexOf(msg.charAt(i));
			if (charIndex >= 0)
				screen.render(x + (i * 8), y, charIndex, color, false, scale, SpriteSheet.letters);

		}

	}

}
