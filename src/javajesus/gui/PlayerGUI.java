package javajesus.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import javajesus.dataIO.PlayerData;
import javajesus.utility.JJStrings;

/**
 * Creates a model of a player 
 */
public class PlayerGUI extends JPanel {

	// for serialization
	private static final long serialVersionUID = 1L;

	// background of the player GUI
	private BufferedImage background, player;
	
	// pixels of image
	private int[] pixelsMale, pixelsFemale, pixelsPlayer;

	// default color set of the player
	private final int[] color = { 0xFF000001, 0xFFFF0000, 0xFFFFCC99, 0xFF343434, 0xFF343434 };
	
	// determines which pixel set to use
	private byte gender = PlayerData.MALE;
	
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
		
		// load the image pixels
		try {
			
			// load the background
			background = ImageIO.read(PlayerGUI.class.getResource(JJStrings.PLAYER_PEDESTAL));
			
			// load the male
			BufferedImage male = ImageIO.read(PlayerGUI.class.getResourceAsStream(JJStrings.PLAYER_MALE));
			
			// load the female
			BufferedImage female = ImageIO.read(PlayerGUI.class.getResourceAsStream(JJStrings.PLAYER_FEMALE));
			
			// total size of pixel data
			int size = male.getWidth() * male.getHeight();
			
			// set up the size of each of the containers
			pixelsMale = new int[size];
			pixelsFemale = new int[size];
			
			// contains the pixel data to be shown
			player = new BufferedImage(male.getWidth(), male.getHeight(), BufferedImage.TYPE_INT_ARGB);
			pixelsPlayer = ((DataBufferInt) player.getRaster().getDataBuffer()).getData();
			
			// now load the pixels
			for (int i = 0; i < size; i++) {
				pixelsMale[i] = male.getRGB(i % male.getWidth(), i / male.getWidth());
				pixelsFemale[i] = female.getRGB(i % female.getWidth(), i / female.getWidth());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Render the image of the player on the background
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		// draw the background image
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		
		// Get the screen pixels
		for (int y = 0; y < player.getHeight(); y++) {
			for (int x = 0; x < player.getWidth(); x++) {
				
				// pixel at the screen coordinate
				int col = 0;
				
				// get the pixel from the right array
				if (gender == PlayerData.FEMALE) {
					col = pixelsFemale[x + y * player.getWidth()];
				} else {
					col = pixelsMale[x + y * player.getWidth()];
				}
				
				// assign the color based on color set
				switch (col) {
				case 0xFF555555: {
					col = color[0];
					break;
				}
				case 0xFFAAAAAA: {
					col = color[1];
					break;
				}
				case 0xFFFFFFFF: {
					col = color[2];
					break;
				}
				case 0xFFE0E0E0: {
					col = color[3];
					break;
				}
				case 0xFF7A7A7A: {
					col = color[4];
					break;
				}
				}
				
				// don't render black
				pixelsPlayer[x + y * player.getWidth()] = col;
				
			}

		}

		// display the image
		g.drawImage(player, 0, 0, getWidth(), getHeight(), null);
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
	 * @return the color of the hair
	 */
	public int getHairColor() {
		return color[3];
	}
	
	/**
	 * @return the color of the pants
	 */
	public int getPantsColor() {
		return color[4];
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
	 * Sets the hair color
	 * 
	 * @param num - the hair color in hexadecimal
	 */
	public void setHairColor(int num) {
		color[3] = num;
	}
	
	/**
	 * Sets the pants color
	 * 
	 * @param num - the pants color in hexadecimal
	 */
	public void setPantsColor(int num) {
		color[4] = num;
	}
	
	/**
	 * @return the color set of the player in the container
	 */
	public int[] getColors() {
		return color;
	}
	
	/**
	 * @param gender - gender type to render
	 */
	public void setGender(byte gender) {
		this.gender = gender;
	}
	
	/**
	 * Gets the gender of the player
	 */
	public byte getGender() {
		return gender;
	}
	
}
