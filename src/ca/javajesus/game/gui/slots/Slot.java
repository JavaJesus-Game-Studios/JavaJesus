package ca.javajesus.game.gui.slots;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.javajesus.game.gui.MapGUI;
import ca.javajesus.game.gui.ScreenGUI;

public class Slot extends ScreenGUI {

	private static final long serialVersionUID = 1L;
	protected double scale = 0.34;
	protected double xScale = 1.28;
	protected double yScale = 1;

	public Slot(String file) {

		try {
			this.image = ImageIO.read(MapGUI.class
					.getResource(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension((int) (image.getWidth() * scale * xScale),
				(int) (image.getHeight() * scale * yScale)));
	}
	
	public void setScale(double num) {
		this.scale = num;
		this.setPreferredSize(new Dimension((int) (image.getWidth() * scale * xScale),
				(int) (image.getHeight() * scale * yScale)));
	}
	
	public void setYScale(double num) {
		this.yScale = num;
		this.setPreferredSize(new Dimension((int) (image.getWidth() * scale * xScale),
				(int) (image.getHeight() * scale * yScale)));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, (int) (image.getWidth() * scale * xScale),
				(int) (image.getHeight() * scale * yScale), this);
	}

	public void tick() {
		repaint();
	}

}
