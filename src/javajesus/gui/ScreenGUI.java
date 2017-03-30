package javajesus.gui;

import java.awt.image.BufferedImage;

import javajesus.InputHandler;

import javax.swing.JPanel;

public abstract class ScreenGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	protected BufferedImage image;
	protected InputHandler input;

	public abstract void tick();
	
	public BufferedImage getImage() {
		return image;
	}

}
