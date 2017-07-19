package designer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javajesus.graphics.Screen;
import javajesus.level.tile.Tile;

import javax.swing.JPanel;

/*
 * Displays a Tile in a JPanel
 */
public class TileGUI extends JPanel {
	
	// serialization
	private static final long serialVersionUID = 1L;
	
	// image of the item to display
	private BufferedImage image;
	
	// item it contains
	private Tile tile;
	
	// size of the item frame
	private static final int SIZE = 8;
	
	// used to identify groups of tile guis
	private int id;
	
	/**
	 * TileGUI ctor()
	 * Displays a simple panel with a single Tile
	 * 
	 * @param tile - tile to display
	 * @param id - id of this tile
	 */
	public TileGUI(Tile tile, int id) {
		this(tile, id, SIZE, SIZE);
	}
	
	/**
	 * TileGUI ctor()
	 * Displays a simple panel with a single Tile
	 * 
	 * @param item - tile to display
	 * @param id - id of this tile
	 * @param width - width of the tile gui
	 * @param height - height of the tile gui
	 */
	public TileGUI(Tile tile, int id, int width, int height) {
		
		// initialize the instance variables
		this.tile = tile;
		this.id = id;
		image = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
		
		// set up the panel
		setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * @return The Tile In this Panel
	 */
	public Tile getTile() {
		return tile;
	}
	
	/**
	 * @return ID of this tile
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Changes the size of the component
	 */
	public void setSize(int width, int height) {
		
		// set up the panel
		setPreferredSize(new Dimension(width, height));
		repaint();
		
	}
	
	/**
	 * setTile()
	 * 
	 * @param tile - new tile to render
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
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
		Screen screen = new Screen(SIZE, SIZE);
		
		// render only if there is an item
		if (tile != null) {
			tile.render(screen);
		}
		
		// add screen pixels to image pixels
		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * screen.getWidth()] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}
		
		// draw the image
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

}
