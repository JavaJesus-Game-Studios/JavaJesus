package game.gui.overview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class InventoryLongPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	private String file = "/GUI/GUI_Inventory/GUI_long_panel.png";
	
	public InventoryLongPanel(int width, int height) {

		try {
			this.image = ImageIO.read(InventoryLongPanel.class.getResource(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setPreferredSize(new Dimension(width, height));
		
		this.validate();

	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public void addItem() {
		
	}

}
