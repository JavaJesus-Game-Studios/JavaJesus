package ca.javajesus.game.gui;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ca.javajesus.game.InputHandler;

public abstract class ScreenGUI extends JPanel {

	protected static final long serialVersionUID = 1L;
	protected BufferedImage image;
	protected InputHandler input;

	public abstract void tick();

}
