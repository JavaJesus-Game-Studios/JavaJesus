package ca.javajesus.game.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Slot extends JPanel {

	protected static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public Slot() {
		try {
			this.image = ImageIO.read(InventoryGUI.class
					.getResource("/GUI/GUI_INVENTORY_SCREEN.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth() * 4, image.getHeight() * 4, this);
	}

}
