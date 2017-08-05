package javajesus.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import javajesus.graphics.Screen;
import javajesus.items.Item;
import javajesus.utility.JJStrings;

/*
 * Displays an item in a JPanel
 */
public class ItemGUI extends JPanel {
	
	// serialization
	private static final long serialVersionUID = 1L;
	
	// image of the item to display
	private BufferedImage image, background_off, background_on;
	
	// item it contains
	private Item item;
	
	// size of the item frame
	private static final int WIDTH = 24, HEIGHT = 24;
	
	// id of the item gui used for the inventory screen
	private int id;
	
	// whether or not it is selected
	private boolean selected;
	
	/**
	 * ItemGUI ctor()
	 * Displays a simple panel with a single item
	 * 
	 * @param width - preferred width
	 * @param height - preferred height
	 * @param item - item to display
	 */
	public ItemGUI(Item item, int id) {
		
		// initialize the instance variables
		this.item = item;
		this.id = id;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		// set up the panel
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// load the backgrounds
		try {
			background_off = ImageIO.read(ItemGUI.class.getResource(JJStrings.INVENTORY_OFF));
			background_on = ImageIO.read(ItemGUI.class.getResource(JJStrings.INVENTORY_ON));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return The Item In this Panel
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * @return the ID of this item gui
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * setItem()
	 * 
	 * @param item - new item to render
	 */
	public void setItem(Item item) {
		this.item = item;
		repaint();
	}
	
	/**
	 * Turns the button on
	 */
	public void turnOn() {
		selected = true;
		repaint();
	}
	
	/**
	 * Turns the button off
	 */
	public void turnOff() {
		selected = false;
		repaint();
	}
	
	/**
	 * Display the image of the item
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		// set up the image
		int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		// transfer item pixels through a screen class
		Screen screen = new Screen(WIDTH, HEIGHT);
		
		// render only if there is an item
		if (item != null) {
			item.render(screen);
		}
		
		// add screen pixels to image pixels
		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * screen.getWidth()] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}
		
		// draw the background
		if (selected) {
			g.drawImage(background_on, 0, 0, getWidth(), getHeight(), null);
		} else {
			g.drawImage(background_off, 0, 0, getWidth(), getHeight(), null);
		}
		
		// draw the image
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

}
