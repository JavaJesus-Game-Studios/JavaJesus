package javajesus.gui.slots;

import java.awt.Graphics;
import java.io.IOException;

import javajesus.gui.ScreenGUI;

import javax.imageio.ImageIO;

/*
 * Simple JPanel with a custom background
 */
public class Slot extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public Slot(String file) {

		try {
			this.image = ImageIO.read(Slot.class.getResource(file));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public void tick() {
		repaint();
	}

}
