package javajesus.gui.overview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javajesus.Game;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TopPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	private String file = "/GUI/GUI_Inventory/inventory_top_panel.png";
	
	private final static int FONT_SIZE = 60;
	
	private JLabel label;
	
	public TopPanel(int width, int height, String text) {

		try {
			this.image = ImageIO.read(TopPanel.class.getResource(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		label = new JLabel(text, SwingConstants.CENTER);
		label.setOpaque(false);
		label.setFont(new Font(Game.FONT_NAME, Font.BOLD, FONT_SIZE));
		label.setForeground(Color.white);
		
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.CENTER);
		
		this.setPreferredSize(new Dimension(width, height));
		
		this.validate();
		
	}
	
	public void updateText(String text) {
		label.setText(text);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
