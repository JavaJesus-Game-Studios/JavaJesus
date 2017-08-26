package javajesus.gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import javajesus.JavaJesus;
import javajesus.Launcher;

public class ControlsGUI extends JPanel implements ActionListener {

	// serialization
	private static final long serialVersionUID = 1L;

	// the background display
	private BufferedImage background;

	// Font to display
	private static final Font bigFont = new Font(JavaJesus.FONT_NAME, 0, 25);

	// instance of the launcher
	private Launcher launcher;

	/**
	 * Creates an instance of this class
	 */
	public ControlsGUI(Launcher launcher) {

		// instance data
		this.launcher = launcher;

		// try to load the background
		try {
			background = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/MENUS/Controls_ALPHA_TEMP.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// set the size
		setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH, JavaJesus.WINDOW_HEIGHT));

		// absolute placement
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// create filler at the top
		add(Box.createVerticalGlue());

		// now add a single button
		JButton back = new JButton("Back");
		add(back);
		back.setFont(bigFont);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.addActionListener(this);

	}

	/**
	 * Paints the background and text
	 */
	@Override
	public void paintComponent(Graphics g) {

		// draw the background
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

	}

	/**
	 * Action listener for the button
	 * 
	 * @param e - the action event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// return to main menu
		((CardLayout) launcher.getParent().getLayout()).show(launcher.getParent(), Launcher.MAIN);
		launcher.updateButtons();

	}

}
