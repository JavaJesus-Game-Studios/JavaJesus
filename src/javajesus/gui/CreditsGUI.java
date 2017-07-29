package javajesus.gui;

import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

import javajesus.JavaJesus;
import javajesus.Launcher;

/*
 * The Credits Display on the Launcher
 */
public class CreditsGUI extends JPanel implements ActionListener {
	
	// serialization
	private static final long serialVersionUID = 1L;
	
	// the background display
	private BufferedImage background;
	
	// Font to display
	private static final Font bigFont = new Font(JavaJesus.FONT_NAME, 0, 25);
	private static final Font smallFont = new Font(JavaJesus.FONT_NAME, 0, 15);
	
	// instance of the launcher
	private Launcher launcher;

	/**
	 * Creates an instance of this class
	 */
	public CreditsGUI(Launcher launcher) {
		
		// instance data
		this.launcher = launcher;
		
		// try to load the background
		try {
			background = ImageIO.read(Launcher.class
					.getResource("/VISUAL_DATA/GUI/MENUS/Pause_Menu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// set the size
		setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH, JavaJesus.WINDOW_HEIGHT));
		
		// absolute placement
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// create filler at the top
		add(Box.createVerticalGlue());
		
		// now add labels
		add(new JJLabel("Technical Lead: Derek Jow", bigFont));
		add(new JJLabel("Creative Lead: Stephen Northway", bigFont));
		add(Box.createVerticalStrut(10));
		add(new JJLabel("Programmers:", bigFont));
		add(new JJLabel("Random Generation/Pathfinding: Stephen Pacwa", smallFont));
		add(new JJLabel("Everything else: Derek Jow", smallFont));
		add(new JJLabel("Miscellanious: Andrew Leamy", smallFont));
		add(Box.createVerticalStrut(10));
		add(new JJLabel("Artists:", bigFont));
		add(new JJLabel("Stephen Northway", smallFont));
		add(Box.createVerticalStrut(10));
		add(new JJLabel("Level/Story Design:", bigFont));
		add(new JJLabel("Stephen Northway", smallFont));
		add(new JJLabel("Derek Jow", smallFont));
		add(Box.createVerticalStrut(10));
		add(new JJLabel("Shoutout to Jeffrey Dalli & Sidd V", smallFont));
		add(Box.createVerticalStrut(10));
		
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
	 * @param e - the action event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// return to main menu
		((CardLayout) launcher.getParent().getLayout()).show(launcher.getParent(), Launcher.MAIN);
		launcher.updateButtons();
		
	}
	
	private class JJLabel extends JLabel {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * @param text - text to display
		 */
		public JJLabel(String text, Font font) {
			super(text);
			
			// set the text and alignment
			setFont(font);
			setForeground(Color.WHITE);
			setAlignmentX(Component.CENTER_ALIGNMENT);
			
		}
		
	}

}
