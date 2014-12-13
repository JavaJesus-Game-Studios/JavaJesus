package ca.javajesus.game.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Slot extends JPanel {

	protected static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public Slot() {
		try {
			this.image = ImageIO.read(Slot.class.getResource("/GUI/slot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
