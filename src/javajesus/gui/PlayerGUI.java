package javajesus.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.utility.JJStrings;

import javax.imageio.ImageIO;
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
	
	// background of the player GUI
	private BufferedImage background;
	
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
		
		// load the background image pixels
		try {
			background = ImageIO.read(PlayerGUI.class.getResourceAsStream(JJStrings.PLAYER_PEDESTAL));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// create the image and initialize the data
		image = new BufferedImage(PLAYER_WIDTH, PLAYER_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
				.getData();
		screen = new Screen(PLAYER_WIDTH, PLAYER_HEIGHT);
	}

	/**
	 * Render the image of the player on the background
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		// draw the background image
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		
		// send pixel data to screen
		renderPlayer(screen, 1);

		// Get the screen pixels
		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				
				// pixel at the screen coordinate
				int col = screen.getPixels()[x + y * screen.getWidth()];
				
				// don't render black
				if (col != 0) {
					pixels[x + y * screen.getWidth()] = col | 0xFF000000;
				}
				
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
		screen.render(0, 0, 0, 0, sheet, color, scale);
		
		// Upper right box
		screen.render(modifier, 0, 1, 0, sheet, color, scale);

		// Lower left box
		screen.render(0, modifier, 0, 1, sheet, color, scale);
		
		// Lower right box
		screen.render(modifier, modifier, 1, 1, sheet, color, scale);

	}
	
}
