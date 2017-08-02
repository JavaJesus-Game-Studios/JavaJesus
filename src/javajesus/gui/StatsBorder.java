package javajesus.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.border.AbstractBorder;

import javajesus.utility.JJStrings;

public class StatsBorder extends AbstractBorder {

	// serialization
	private static final long serialVersionUID = 1L;

	// background image to draw
	private BufferedImage background;

	// the different borders
	private static final int HEALTH = 0, ENERGY = 1;
	
	// gaps insets
	private int top, left, bottom, right;

	/**
	 * Creates a custom border
	 */
	public StatsBorder(int type) {
		
		// the path to load the background
		String path = "";
		
		// select the path
		if  (type == HEALTH) {
			path = JJStrings.STATUS_TOP;
			top = 18;
			left = 260;
			bottom = 0;
			right = 18;
		} else if (type == ENERGY) {
			path = JJStrings.STATUS_MIDDLE;
			top = 10;
			left = 260;
			bottom = 10;
			right = 18;
		} else { // ARMOR
			path = JJStrings.STATUS_BOTTOM;
			top = 5;
			left = 260;
			bottom = 18;
			right = 18;
		}
		
		// load the image
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
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.drawImage(background, x, y, width, height, null);
	}

	/**
	 * Set to custom gap size
	 */
	@Override
	public Insets getBorderInsets(Component c) {
		return (getBorderInsets(c, new Insets(top, left, bottom, right)));
	}

	/**
	 * Set to custom gap size
	 */
	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.left = left;
		insets.right = right;
		insets.top = top;
		insets.bottom = bottom;
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
