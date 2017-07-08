package javajesus.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;

import javax.swing.JPanel;

/**
 * Creates a model of a player 
 */
public class PlayerGUI extends JPanel {

	// for serialization
	private static final long serialVersionUID = 1L;

	// modifies pixels
	private final Screen screen;
	
	// virtual canvas of the panel
	private final BufferedImage image;
	
	// pixels of image
	private final int[] pixels;
	
	// dimensions of the player
	private static final int PLAYER_WIDTH = 16, PLAYER_HEIGHT = 16;
	
	// spritesheet of the player
	private static final SpriteSheet sheet = SpriteSheet.player;
	
	// default color set of the player
	private final int[] color = { 0xFF343434, 0xFFFF0000, 0xFFFFCC99 };
	
	/**
	 * PlayerGUI ctor()
	 * Creates a panel that displays a picture of the player
	 * 
	 * @param width - width of the panel
	 * @param height - height of the panel
	 */
	public PlayerGUI(int width, int height) {

		// set the size
		setPreferredSize(new Dimension(width, height));
		
		// create the image and initialize the data
		image = new BufferedImage(PLAYER_WIDTH, PLAYER_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
				.getData();
		screen = new Screen(PLAYER_WIDTH, PLAYER_HEIGHT);
	}

	/**
	 * Render the image of the player on the background
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		// send pixel data to screen
		renderPlayer(screen, 1);

		// Get the screen pixels
		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * screen.getWidth()] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}

		// display the image
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	/**
	 * @return the color of the shirt
	 */
	public int getShirtColor() {
		return color[1];
	}
	
	/**
	 * @return the color of the skin
	 */
	public int getSkinColor() {
		return color[2];
	}
	
	/**
	 * Sets the hair color
	 * 
	 * @param num - the hair color in hexadecimal
	 */
	public void setHairColor(int num) {
		color[0] = num;
	}

	/**
	 * Sets the shirt color
	 * 
	 * @param num - the shirt color in hexadecimal
	 */
	public void setShirtColor(int num) {
		color[1] = num;
	}

	/**
	 * Sets the skin color
	 * 
	 * @param num - the skin color in hexadecimal
	 */
	public void setSkinColor(int num) {
		color[2] = num;
	}
	
	/**
	 * @return the color set of the player in the container
	 */
	public int[] getColors() {
		return color;
	}

	/**
	 * Renders the image of a player onto a screen
	 * @param screen - the screen to render
	 * @param scale - the scale of the player
	 */
	public void renderPlayer(Screen screen, int scale) {

		// offsets for player rendering
		int modifier = 8 * scale;

		// Upper left box
		screen.render(0, 0, 0, color, false, scale, sheet);
		
		// Upper right box
		screen.render(modifier, 0, 1, color, false, scale, sheet);

		// Lower left box
		screen.render(0, modifier, 1 * sheet.getTilesPerRow(), color, false, scale, sheet);
		
		// Lower right box
		screen.render(modifier, modifier, 1 + sheet.getTilesPerRow(), color, false, scale, sheet);

	}
	
}
