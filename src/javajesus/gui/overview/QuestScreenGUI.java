package javajesus.gui.overview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class QuestScreenGUI extends JPanel {

	// serialization
	private static final long serialVersionUID = 1L;
	
	// background image
	private BufferedImage image;
	
	/**
	 * QuestGUI ctor()
	 */
	public QuestScreenGUI() {
		try {
			image = ImageIO.read(QuestScreenGUI.class
					.getResource("/GUI/GUI_Inventory/GUI_QUEST_PAGE.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, this);
	}

}
