package javajesus.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

/*
 * Custom UI for sliders
 */
public class VerticalSliderUI extends BasicScrollBarUI {

	// custom buffered images
	private BufferedImage track, thumb;
	
	// x offset from track image
	private static final int xOffset = 3;
	
	// dummy arrow button
	private static final JButton dummy = new JButton();

	/**
	 * Initializes the track and thumb
	 */
	public VerticalSliderUI() {
		
		// dummy should not be visible
		Dimension zero = new Dimension(0, 0);
		dummy.setPreferredSize(zero);
		dummy.setMaximumSize(zero);

		try {

			track = ImageIO.read(
			        VerticalSliderUI.class.getResource("/VISUAL_DATA/GUI/PANELS/INVENTORY/description_slider.png"));
			thumb = ImageIO
			        .read(VerticalSliderUI.class.getResource("/VISUAL_DATA/GUI/BUTTONS/SLIDERS/slider_vert.png"));

		} catch (IOException e) {
			System.err.println("Couldn't load Vertical Slider UI");
		}

	}

	/**
	 * Custom Track
	 */
	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		g.drawImage(track, trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, null);
	}

	/**
	 * Custom Thumb
	 */
	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		g.drawImage(thumb, thumbBounds.x + xOffset, thumbBounds.y, thumb.getWidth() + 1, thumb.getHeight(), null);
	}
	
	@Override
	protected JButton createDecreaseButton(int orientation) {
	    return dummy;
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
	    return dummy;
	}

}
