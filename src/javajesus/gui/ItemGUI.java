package javajesus.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;

import javajesus.graphics.Screen;
import javajesus.items.Item;

/*
 * Displays an item in a JPanel
 */
public class ItemGUI extends JPanel {
	
	// serialization
	private static final long serialVersionUID = 1L;
	
	// image of the item to display
	private BufferedImage image;
	
	/**
	 * ItemGUI ctor()
	 * Displays a simple panel with a single item
	 * 
	 * @param width - preferred width
	 * @param height - preferred height
	 * @param item - item to display
	 */
	public ItemGUI(int width, int height, Item item) {
		
		// set up the panel
		setPreferredSize(new Dimension(width, height));
		
		// set up the image
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		// transfer item pixels through a screen class
		Screen screen = new Screen(width, height);
		item.render(screen, 0, 0);
		
		// add screen pixels to image pixels
		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * screen.getWidth()] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}
		
	}
	
	/**
	 * Display the image of the item
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

}
