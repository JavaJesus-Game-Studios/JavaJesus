package javajesus.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javajesus.JavaJesus;
import javajesus.Launcher;
import javajesus.dataIO.PlayerData;
import javajesus.items.Item;

public class PlayerCreationGUI extends JPanel {

	// serialization
	private static final long serialVersionUID = 1L;

	// Selectors on the left side
	private JButton skin, shirt, finish, pistol, sword;
	
	// default active tab
	private int active;
	
	// identifiers for different active tabs
	private static final int SKIN_SCREEN = 0, SHIRT_SCREEN = 1;
	
	// Slot Panel where the played is JavaJesused
	private PlayerGUI pScreen;
	
	// text input below player panel
	private JTextField name;
	
	// sliders on right side
	private RGBSlider red, green, blue;
	
	// display of starting weapon
	private ItemGUI weaponDisplay;
	
	// starting weapon for player
	private byte startWeapon = PlayerData.REVOLVER;
	
	// slot of player save data
	private int numSlot;
	
	// instance of the parent
	private Launcher launcher;
	
	/**
	 * Initializes instance variables and puts the panels together
	 */
	public PlayerCreationGUI(Launcher launcher) {
		
		// parent
		this.launcher = launcher;
		
		// instance variable
		this.numSlot = 1;
		
		// set up the player creation window
		setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH,
				JavaJesus.WINDOW_HEIGHT));
		setLayout(new BorderLayout(0, 0));
		
		// create the left side
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		leftSide.setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH / 2, JavaJesus.WINDOW_HEIGHT));
		
		// create the components in the middle slot
		leftSide.add(pScreen = new PlayerGUI(JavaJesus.WINDOW_WIDTH / 2, JavaJesus.WINDOW_HEIGHT - 80));
		leftSide.add(new JJLabel("Enter Name:"));
		leftSide.add(name = new JTextField());
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
		colorPanel.add(red = new RGBSlider((pScreen.getSkinColor() & 0x00FF0000) >> 16, Color.RED));
		colorPanel.add(new JJLabel("Green"));
		colorPanel.add(green = new RGBSlider((pScreen.getSkinColor() & 0x0000FF00) >> 8, Color.GREEN));
		colorPanel.add(new JJLabel("Blue"));
		colorPanel.add(blue = new RGBSlider(pScreen.getSkinColor() & 0x000000FF, Color.BLUE));
		colorPanel.add(new JPanel());
		
		// create the container for the weapon options
		JPanel weaponPanel = new JPanel(new BorderLayout(0, 0));
		JPanel weaponTop = new JPanel();
		weaponTop.add(pistol = new JJButton("Pistol"));
		weaponTop.add(sword = new JJButton("Sword"));
		
		// add a picture of the starting weapon
		weaponDisplay = new ItemGUI(Item.revolver, 0);
		
		// construct the weapon panel
		weaponPanel.add(weaponTop, BorderLayout.NORTH);
		weaponPanel.add(weaponDisplay, BorderLayout.CENTER);
		
		// add the components to the right side
		rightSide.add(colorPanel);
		rightSide.add(new JJLabel("Weapon"));
		rightSide.add(weaponPanel);
		rightSide.add(finish = new JJButton("Finish"));

		// add all the components together
		add(leftSide, BorderLayout.CENTER);
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
		private RGBSlider(int init, Color color) {
			super(JSlider.HORIZONTAL, 0, 255, init);
			
			// add change listener
			addChangeListener(this);
			
			// set the UI
			setUI(new RGBSliderUI(this, Color.BLACK, color));
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
				
				// wrap the color
				Color c = new Color(pScreen.getSkinColor());
				
				// move the sliders over to current value
				red.setValue(c.getRed());
				green.setValue(c.getGreen());
				blue.setValue(c.getBlue());
				
			}
			
			if (e.getSource() == shirt) {
				active = SHIRT_SCREEN;
				
				// wrap the color
				Color c = new Color(pScreen.getShirtColor());
				
				// move the sliders over to current value
				red.setValue(c.getRed());
				green.setValue(c.getGreen());
				blue.setValue(c.getBlue());
			}
			
			// change display to pistol
			if (e.getSource() == pistol) {
				weaponDisplay.setItem(Item.revolver);
				startWeapon = PlayerData.REVOLVER;
				weaponDisplay.repaint();
			}
			
			// change display to sword
			if (e.getSource() == sword) {
				weaponDisplay.setItem(Item.shortSword);
				startWeapon = PlayerData.SHORT_SWORD;
				weaponDisplay.repaint();
			}
			
			// create the player
			if (e.getSource() == finish) {
				
				// create a save file
				new PlayerData(numSlot, name.getText(),  pScreen.getSkinColor(), pScreen.getShirtColor(), startWeapon);
				
				// return to main display
				((CardLayout) launcher.getParent().getLayout()).show(launcher.getParent(), Launcher.MAIN);
				launcher.updateButtons();
				
			}
		}
	}
	
	/**
	 * Sets the slot to save to
	 * 
	 * @param slot - 1, 2, or 3
	 */
	public void setSlot(int slot) {
		numSlot = slot;
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
