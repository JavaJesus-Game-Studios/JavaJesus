package game.gui;

import game.InputHandler;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class ScreenGUI extends JPanel {

	protected static final long serialVersionUID = 1L;
	protected BufferedImage image;
	protected InputHandler input;

	public abstract void tick();

}
