package ca.javajesus.game.gui.slots;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import ca.javajesus.game.gui.ScreenGUI;

public class Slot extends ScreenGUI {

	private static final long serialVersionUID = 1L;

	public Slot(String file) {

		try {
			this.image = ImageIO.read(Slot.class.getResource(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension((int) (image.getWidth()),
				(int) (image.getHeight())));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, (int) (image.getWidth()),
				(int) (image.getHeight()), this);
	}

	public void tick() {
		repaint();
	}

}
