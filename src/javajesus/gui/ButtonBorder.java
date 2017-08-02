package javajesus.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.border.AbstractBorder;

import javajesus.utility.JJStrings;

public class ButtonBorder extends AbstractBorder {

	// serialization
	private static final long serialVersionUID = 1L;

	// background image to draw
	private BufferedImage background;

	// the size of the gap
	private final int gap = 13;

	/**
	 * Creates a custom border
	 */
	public ButtonBorder() {
		try {
			background = ImageIO.read(JJBorder.class.getResource(JJStrings.PLAYER_INFO_BORDER));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Paint a custom border
	 */
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.drawImage(background, x, y, width, height, null);
	}

	/**
	 * Set to custom gap size
	 */
	@Override
	public Insets getBorderInsets(Component c) {
		return (getBorderInsets(c, new Insets(0, gap, 0, gap)));
	}

	/**
	 * Set to custom gap size
	 */
	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.left = insets.right = gap;
		insets.top = insets.bottom = 0;
		return insets;
	}

	/**
	 * Make an opaque border
	 */
	@Override
	public boolean isBorderOpaque() {
		return true;
	}

}
