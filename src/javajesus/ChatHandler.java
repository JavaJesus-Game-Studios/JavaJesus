package javajesus;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/*
 * ChatHandler creates a graphical overlay at the bottom of 
 * the screen that processes text
 */
public class ChatHandler {

	// Max length of text on one line
	private static final int LENGTH = 75;

	// Time in seconds that the message appears
	private static final int TIME_DISPLAYED = 4;
	
	// Number of lines that can appear in chat
	private static final int NUM_LINES = 5;
	
	// x offset from the width of the box on the left side
	private static final int XOFFSET = 160, TEXT_OFFSET = 190;

	// Entire list of text recorded since the start of the game
	public static ArrayList<String> chatlog = new ArrayList<String>();

	// List of text displayed only on the window itself
	protected static ArrayList<String> chatwindow = new ArrayList<String>();

	// List of colors used in the text that corresponds to different
	// circmstances
	private static ArrayList<Color> colors = new ArrayList<Color>();

	// used in counting the ticks the window has been displayed
	private static long lastTime;
	
	// whether or not the chat window is currently visible
	private static boolean isVisible;
	
	// whether the window is currently being timed
	private static boolean tickTimer;

	// background image of the chat handler
	public static BufferedImage image;

	/**
	 * Initializes the image of the window
	 */
	public static void initialize() {
		try {
			image = ImageIO.read(ChatHandler.class
					.getResource("/GUI/GUI_Hud/GUI_Dialogue.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Displays text on the WINDOW
	 * 
	 * @param string
	 *            The words to display
	 * @param color
	 *            The color of the text
	 */
	public static void displayText(String string, Color color) {
		tickTimer = true;
		lastTime = System.currentTimeMillis();
		isVisible = true;
		chatlog.add(string);
		// If a string is longer than the max length, split it to the next line
		String check = string;
		while (!(check.length() <= LENGTH)) {
			updateWindow(check.substring(0, stringSplitter(check)), color);
			check = check.substring(stringSplitter(check));
		}
		updateWindow(check, color);
	}

	/**
	 * Draws the window on the canvas
	 * 
	 * @param g
	 *            The Graphics class that draws it on the screen
	 */
	public static void drawWindow(Graphics g) {
		
		// draw only when a new message appears or player opens it
		if (isVisible) {
			g.drawImage(image, XOFFSET,
					Display.FRAME_HEIGHT - image.getHeight() + 10,
					Display.FRAME_WIDTH - XOFFSET + 10, image.getHeight(), null);
			
			// get font data
			FontMetrics font = g.getFontMetrics();
			int yOffset = Display.FRAME_HEIGHT - 5;
			
			// process each message to ensure it fits in the window
			for (int i = 0; i < chatwindow.size(); i++) {
				if (chatwindow.get(i).contains(":")) {
					int split = chatwindow.get(i).indexOf(":") + 1;
					String name = chatwindow.get(i).substring(0, split);
					String message = chatwindow.get(i).substring(split);
					g.setColor(Color.WHITE);
					g.drawString(name, TEXT_OFFSET, yOffset);
					g.setColor(colors.get(i));
					g.drawString(message, TEXT_OFFSET + font.stringWidth(name), yOffset);
					yOffset -= 20;
				} else if (chatwindow.get(i) != null) {
					g.setColor(colors.get(i));
					g.drawString(chatwindow.get(i), TEXT_OFFSET, yOffset);
					yOffset -= 20;
				}
			}
		}

		// Automatically hides the window after a certain amount of time
		if (tickTimer
				&& (System.currentTimeMillis() > lastTime
						+ (TIME_DISPLAYED * 1000))) {
			isVisible = false;
			tickTimer = false;
			lastTime = 0;
		}
	}

	/**
	 * Processes the text in the proper order on the window
	 * 
	 * @param string
	 *            Text to process
	 * @param color
	 *            Color of the text
	 */
	private static void updateWindow(String string, Color color) {
		if (chatwindow.size() < NUM_LINES) {
			chatwindow.add(0, string);
			colors.add(0, color);
		} else {
			chatwindow.remove(NUM_LINES - 1);
			colors.remove(NUM_LINES - 1);
			chatwindow.add(0, string);
			colors.add(0, color);
		}

	}

	/**
	 * Shows or Hides the window
	 */
	public static void toggle() {
		tickTimer = false;
		lastTime = 0;
		isVisible = !isVisible;
	}

	/**
	 * @param string
	 *            The text to split
	 * @return The index of the first available space to split the word to the
	 *         next line
	 */
	private static int stringSplitter(String string) {
		for (int i = LENGTH; i > 0; i--) {
			if (string.charAt(i) == ' ') {
				return i;
			}
		}
		return LENGTH;
	}
}
