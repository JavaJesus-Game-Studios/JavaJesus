package game.gui.overview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Game;

public class InventorySmallPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	private String file = "/GUI/GUI_Inventory/GUI_small_panel.png";
	
	private final static int FONT_SIZE = 20;
	
	public InventorySmallPanel(int width, int height, String text) {

		try {
			this.image = ImageIO.read(InventorySmallPanel.class.getResource(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLabel label = new JLabel(text);
		label.setOpaque(false);
		label.setFont(new Font(Game.FONT_NAME, Font.BOLD, FONT_SIZE));
		
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.CENTER);
		
		this.setPreferredSize(new Dimension(width, height));
		
		this.validate();

	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
