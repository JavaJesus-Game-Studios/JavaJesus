package designer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;

import javajesus.entities.Entity;
import javajesus.graphics.Screen;

public class EntityGUI extends JPanel {
	
	// serialization
	private static final long serialVersionUID = 1L;

	// size of the item frame
	private static final int SIZE = 8;
	
	// the buffered image to render on the screen
	private final BufferedImage image;
	
	// pixel data of the buffered image
	int[] pixels;
	
	// the entity to display
	private final Entity entity;
	
	// holds the pixel data, large number to ensure building is contained
	private final Screen screen = new Screen(260, 260);
	
	// the number of tiles in each direction
	private int xTiles, yTiles;
	
	/**
	 * EntityGUI ctor()
	 * 
	 * @param panelWidth - width of the JPanel
	 * @param panelHeight - height of the Jpanel
	 * @param xTiles - tile width of the entity
	 * @param yTiles - tile height of the entity
	 */
	public EntityGUI(Entity entity, int panelWidth, int panelHeight, int xTiles, int yTiles) {
		
		// instance data
		this.entity = entity;
		this.xTiles = xTiles;
		this.yTiles = yTiles;
		
		// create the image
		image = new BufferedImage(SIZE * xTiles, SIZE * yTiles, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		// set the size
		setPreferredSize(new Dimension(panelWidth, panelHeight));
		
	}
	
	/**
	 * render()
	 * Renders these pixels onto screen pixels
	 * 
	 * @param screen - screen to render on
	 * @param xTile - the x coordinate of the entity
	 * @param yTile - the y coordinate of the entity
	 */
	public void render(Screen screen, int xTile, int yTile) {
		screen.renderChunk(this.screen, xTile, yTile);
	}
	
	/**
	 * Display the image of the item
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		// render the entity
		entity.render(screen);
		
		// add screen pixels to image pixels
		for (int y = 0; y < yTiles * SIZE; y++) {
			for (int x = 0; x < xTiles * SIZE; x++) {
				pixels[x + y * (xTiles * SIZE)] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}
		
		// draw the image
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	/**
	 * @return the width in tiles
	 */
	public int getXTiles() {
		return xTiles;
	}
	
	/**
	 * @return the height in tiles
	 */
	public int getYTiles() {
		return yTiles;
	}

}
