package designer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Timer;
import java.util.TimerTask;

import javajesus.entities.Entity;
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
	
	// image data
	int[] pixels;
	
	// item it contains
	private Tile tile;
	
	// size of the item frame
	private static final int SIZE = 8;
	
	// used to identify groups of tile guis
	private int id;
	
	// transfer item pixels through a screen class
	private final Screen screen = new Screen(SIZE, SIZE);
	
	// Entity that is on this tile
	private Entity entity;
	
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
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		// get default screen pixels
		if (tile != null) {
			tile.render(screen);
		}
		
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
	public void changeSize(int width, int height) {
		
		// set up the panel
		setPreferredSize(new Dimension(width, height));
		repaint();
		
	}
	
	/**
	 * @return whether or not an entity is on this tile
	 */
	public boolean entityExists() {
		return entity != null;
	}
	
	/**
	 * Adds an entity to this tile
	 * @param e - the entity to add
	 */
	public void addEntity(Entity e) {
		this.entity = e;
	}
	
	/**
	 * @return the entity ID
	 */
	public byte getEntityId() {
		return entity.getId();
	}
	
	/**
	 * @return the Entity Data
	 */
	public long getEntityData(int xOffset, int yOffset) {
		
		// set the entity to the right x and y coords
		entity.moveTo((getX() - xOffset) * 8 / Designer.zoomScale, (getY() - yOffset) * 8 / Designer.zoomScale);
		
		// get the data
		long data = entity.getData();
		
		// move the entity back to zero
		entity.moveTo(0, 0);
		
		// return the data
		return data;
	}
	
	/**
	 * setTile()
	 * 
	 * @param tile - new tile to render
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
		this.entity = null;
		
		// render only if there is an item
		if (tile != null) {
			tile.render(screen);
		}
				
		repaint();
	}
	
	/**
	 * Renders an EntityGUI onto the tile
	 * @param e - the entityGUI to render
	 * @param xTile - the xtile of the entity
	 * @param yTile - the ytile of the entity
	 */
	public void render(EntityGUI e, int xTile, int yTile) {
		e.render(screen, xTile, yTile);
		repaint();
	}
	
	/**
	 * Display the image of the item
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		// add screen pixels to image pixels
		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * screen.getWidth()] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}
		
		// draw the image
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	/**
	 * Flickers the screen of the tile after 1 second
	 */
	public void flicker() {
		setVisible(false);
		
		// turn the visibility back in in 1 second
		Timer timer = new Timer(true);
		timer.schedule(new Task(this), 1000);
	}
	
	/*
	 * TimerTask to turn on visibility
	 */
	private class Task extends TimerTask {
		
		// tile to flicker
		private TileGUI tile;
		
		/**
		 * @param tile - tile to turn on
		 */
		private Task(TileGUI tile) {
			this.tile = tile;
		}

		/**
		 * Renders the tile again
		 */
		public void run() {
			tile.setVisible(true);
		}
		
	}
	
}
