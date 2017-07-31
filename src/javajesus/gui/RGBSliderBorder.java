package javajesus.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.border.AbstractBorder;

public class RGBSliderBorder extends AbstractBorder {
	
	// serialization
	private static final long serialVersionUID = 1L;

	// background image to draw
	private BufferedImage background;
	
	// inset bounds
	private static final int TOP = 15, LEFT = 181, BOTTOM = 11, RIGHT = 23;
	
	/**
	 * Creates a custom border
	 */
	public RGBSliderBorder(String path) {
		try {
			background = ImageIO.read(JJBorder.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Paint a custom border
	 */
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		g.drawImage(background, x, y, width, height, null);
	}

	/**
	 * Set to custom gap size
	 */
	@Override
	public Insets getBorderInsets(Component c) {
		return (getBorderInsets(c, new Insets(TOP, LEFT, BOTTOM, RIGHT)));
	}

	/**
	 * Set to custom gap size
	 */
	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.top = TOP;
		insets.left = LEFT;
		insets.bottom = BOTTOM;
		insets.right = RIGHT;
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
