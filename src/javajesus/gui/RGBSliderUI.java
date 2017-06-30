package javajesus.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

/*
 * Custom UI for sliders
 */
public class RGBSliderUI extends BasicSliderUI {

	// start and ending colors
	private Color start, end;

	// the slider to modify
	private JSlider slider;

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
		this.slider = slider;
	}

	/**
	 * Paints the track of the slider
	 */
	@Override
	public void paintTrack(Graphics g) {

		// Rectangle is drawn in 2D graphics
		Graphics2D g2d = (Graphics2D) g;

		// size of track is based on slider
		Rectangle track = new Rectangle(0, 0, (int) slider.getSize().getWidth(),
		        (int) slider.getSize().getHeight());

		// set the paint for the gradient
		g2d.setPaint(new LinearGradientPaint(new Point2D.Float(track.x, track.y),
		        new Point2D.Float(track.width, track.height), new float[] { 0.0f, 1.0f },
		        new Color[] { start, end }));

		// fill the rectangle
		g2d.fillRect(track.x, track.y, track.width, track.height);

	}

	@Override
	public void paintThumb(Graphics g) {
		super.paintThumb(g);
	}

}
