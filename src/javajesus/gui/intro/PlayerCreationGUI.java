package javajesus.gui.intro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javajesus.JavaJesus;
import javajesus.gui.PlayerSlotGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayerCreationGUI extends JPanel {

	// serialization
	private static final long serialVersionUID = 1L;

	// Selectors on the left side
	private JButton skin, shirt, finish;
	
	// default active tab
	private int active;
	
	// identifiers for different active tabs
	private static final int SKIN_SCREEN = 0, SHIRT_SCREEN = 1;
	
	// Slot Panel where the played is JavaJesused
	private PlayerSlotGUI pScreen;
	
	// text input below player panel
	private JTextField name;
	
	// sliders on right side
	private RGBSlider red, green, blue;
	
	// temp workaround
	JavaJesus main;
	
	/**
	 * Initializes instance variables and puts the panels together
	 */
	public PlayerCreationGUI(JavaJesus main) {
		
		// temp workaround
		this.main = main;
		
		// set up the player creation window
		setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH,
				JavaJesus.WINDOW_HEIGHT));
		setLayout(new BorderLayout(0, 0));
		
		// create the middle side
		JPanel middleSide = new JPanel();
		middleSide.setLayout(new BoxLayout(middleSide, BoxLayout.Y_AXIS));
		middleSide.setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH / 2, JavaJesus.WINDOW_HEIGHT));
		
		// create the components in the middle slot
		middleSide.add(pScreen = new PlayerSlotGUI(JavaJesus.WINDOW_WIDTH / 2, JavaJesus.WINDOW_HEIGHT - 80));
		middleSide.add(new JJLabel("Enter Name:"));
		middleSide.add(name = new JTextField());
		name.setMinimumSize(new Dimension(Integer.MAX_VALUE, name.getPreferredSize().height));
		
		// create the right side
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH / 2, JavaJesus.WINDOW_HEIGHT));
		
		// create the container for the skin and shirt color options
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
		
		// holds the skin and shirt buttons
		JPanel holder = new JPanel();
		holder.add(skin = new JJButton("Skin"));
		holder.add(shirt = new JJButton("Shirt"));
		holder.setMaximumSize(holder.getPreferredSize());
		
		// form the color panel
		colorPanel.add(holder);
		colorPanel.add(new JJLabel("Red"));
		colorPanel.add(red = new RGBSlider(255));
		colorPanel.add(new JJLabel("Green"));
		colorPanel.add(green = new RGBSlider(0));
		colorPanel.add(new JJLabel("Blue"));
		colorPanel.add(blue = new RGBSlider(0));
		colorPanel.add(new JPanel());
		
		// create the container for the weapon options
		JPanel weaponPanel = new JPanel();
		weaponPanel.add(new JJButton("Pistol"));
		weaponPanel.add(new JJButton("Sword"));
		
		// add the components to the right side
		rightSide.add(colorPanel);
		rightSide.add(new JJLabel("Weapon"));
		rightSide.add(weaponPanel);
		rightSide.add(finish = new JJButton("Finish"));

		// add all the components together
		add(middleSide, BorderLayout.CENTER);
		add(rightSide, BorderLayout.EAST);
	}

	/*
	 * Custom JSlider that updates the player image
	 */
	private class RGBSlider extends JSlider implements ChangeListener {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * RGBSlider ctor()
		 * Custom JSlider for RGB values
		 * 
		 * @param init - initial value
		 */
		private RGBSlider(int init) {
			super(JSlider.HORIZONTAL, 0, 255, init);
			
			// add change listener
			addChangeListener(this);
		}

		/**
		 * Updates when the slider is moved
		 */
		@Override
		public void stateChanged(ChangeEvent e) {
			
			// get the r g b values
			int r = red.getValue();
			int g = green.getValue();
			int b = blue.getValue();
			
			// update the player image
			if (active == SKIN_SCREEN) {
				pScreen.setSkinColor(new Color(r, g, b).getRGB());
			} else if (active == SHIRT_SCREEN) {
				pScreen.setShirtColor(new Color(r, g, b).getRGB());
			}
			
			// repaint the player
			pScreen.repaint();
			
		}
		
	}
	
	/*
	 * Automatically aligns the JButton
	 * and sets the Font
	 */
	private class JJButton extends JButton implements ActionListener {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * JJButton ctor()
		 * @param s - Button text
		 */
		private JJButton(String s) {
			super(s);
			
			// align and set font
			setAlignmentX(Component.CENTER_ALIGNMENT);
			setFont(new Font(JavaJesus.FONT_NAME, Font.PLAIN, 25));
			
			// add input listener
			addActionListener(this);
		}
		
		/**
		 * Handles input on the creation menu
		 * 
		 * @param e - the action that was performed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == skin) {
				active = SKIN_SCREEN;
			}
			
			if (e.getSource() == shirt) {
				active = SHIRT_SCREEN;
			}
			
			// create the player
			if (e.getSource() == finish) {
				
				// temp workaround
				main.createPlayer(name.getText(), pScreen.getShirtColor(), pScreen.getSkinColor());
				
			}
		}
	}
	
	/*
	 * Automatically aligns the JLabel
	 * and sets the Font
	 */
	private class JJLabel extends JLabel {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * JJLabel ctor()
		 * @param s - Label text
		 */
		private JJLabel(String s) {
			super(s);
			
			// align and set font
			setAlignmentX(Component.CENTER_ALIGNMENT);
			setFont(new Font(JavaJesus.FONT_NAME, Font.PLAIN, 25));
		}
	}

}
