package javajesus.gui.overview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapScreenGUI extends JPanel {

	// serialization
	private static final long serialVersionUID = 1L;
	
	// background image
	private BufferedImage image;
	
	/**
	 * MapGUI ctor ()
	 * @param width
	 * @param height
	 */
	public MapScreenGUI(int width, int height) {
		try {
			image = ImageIO.read(MapScreenGUI.class
					.getResource("/GUI/GUI_Inventory/GUI_MAP_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(width, height));
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
