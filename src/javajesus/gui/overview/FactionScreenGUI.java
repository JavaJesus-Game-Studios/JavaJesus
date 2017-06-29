package javajesus.gui.overview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FactionScreenGUI extends JPanel {

	// serialization
	private static final long serialVersionUID = 1L;
	
	// background of the page
	private BufferedImage background;

	/**
	 * FactionGUI ctor()
	 */
	public FactionScreenGUI() {
		
		try {
			// initialize the image into memory
			background = ImageIO.read(FactionScreenGUI.class
					.getResource("/GUI/GUI_Inventory/GUI_FACTIONS_PAGE.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// set the size
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, WIDTH, HEIGHT, this);
	}

}
