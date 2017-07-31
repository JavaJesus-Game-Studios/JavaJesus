package javajesus.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

/*
 * Custom UI for sliders
 */
public class RGBSliderUI extends BasicSliderUI {

	// start and ending colors
	private Color start, end;

	// the thumb to draw
	private BufferedImage thumb;

	/**
	 * RGBSliderUI ctor()
	 * 
	 * @param slider - slider to modify
	 * @param start - start color
	 * @param end - end color
	 */
	public RGBSliderUI(JSlider slider, Color start, Color end) {
		super(slider);

		// instantiate instance variables
		this.start = start;
		this.end = end;
		
		// load the thumb
		try {
			thumb = ImageIO
			        .read(VerticalSliderUI.class.getResource("/VISUAL_DATA/GUI/BUTTONS/SLIDERS/slider_horz.png"));
			
		} catch (IOException e) {
			System.err.println("Couldn't load RGB Slider UI");
		}
	}

	/**
	 * Paints the track of the slider
	 */
	@Override
	public void paintTrack(Graphics g) {

		// Rectangle is drawn in 2D graphics
		Graphics2D g2d = (Graphics2D) g;

		// size of track is based on slider
		Rectangle track = new Rectangle(trackRect.x - 4, trackRect.y - 1, trackRect.width + 9, trackRect.height + 2);

		// set the paint for the gradient
		g2d.setPaint(new LinearGradientPaint(new Point2D.Float(track.x, track.y),
		        new Point2D.Float(track.width, track.height), new float[] { 0.0f, 1.0f },
		        new Color[] { start, end }));

		// fill the rectangle
		g2d.fillRect(track.x, track.y, track.width, track.height);

	}

	/**
	 * Paints the slider thumb
	 */
	@Override
	public void paintThumb(Graphics g) {
		g.drawImage(thumb,  thumbRect.x, 15, thumbRect.width, thumbRect.height, null);
	}

}
