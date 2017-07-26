package javajesus.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javajesus.utility.JJStrings;

import javax.imageio.ImageIO;
import javax.swing.border.AbstractBorder;

/*
 * A custom border and background for our
 * JSwing panels
 */
public class JJBorder extends AbstractBorder {

	// serialization
	private static final long serialVersionUID = 1L;

	// background image to draw
	private BufferedImage background;

	// the size of the gap
	private final int gap = 15;

	/**
	 * Creates a custom border
	 */
	public JJBorder() {
		try {
			background = ImageIO.read(JJBorder.class
					.getResource(JJStrings.OVERVIEW_BACKGROUND));
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
		return (getBorderInsets(c, new Insets(gap, gap, gap, gap)));
	}

	/**
	 * Set to custom gap size
	 */
	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.left = insets.top = insets.right = insets.bottom = gap;
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
