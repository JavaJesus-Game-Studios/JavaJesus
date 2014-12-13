package ca.javajesus.game.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class InventoryGUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	
	public InventoryGUI() {
		try {
			this.image = ImageIO.read(InventoryGUI.class
			        .getResource("/GUI/Main_Menu_Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);           
    }
	
}
