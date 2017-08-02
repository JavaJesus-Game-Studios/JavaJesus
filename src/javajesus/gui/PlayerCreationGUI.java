package javajesus.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javajesus.JavaJesus;
import javajesus.Launcher;
import javajesus.dataIO.PlayerData;
import javajesus.utility.JJStrings;

public class PlayerCreationGUI extends JPanel implements ActionListener {

	// serialization
	private static final long serialVersionUID = 1L;

	// Selectors on the left side
	private final JJButton skin, shirt, hair, pants, finish, pistol, sword, back, male, female;
	
	// default active tab
	private int active;
	
	// identifiers for different active tabs
	private static final int SKIN_SCREEN = 0, SHIRT_SCREEN = 1, HAIR_SCREEN = 2, PANTS_SCREEN = 3;
	
	// Slot Panel where the played is JavaJesused
	private PlayerGUI pScreen;
	
	// text input below player panel
	private JJTextField name;
	
	// sliders on right side
	private RGBSlider red, green, blue;
	
	// starting weapon for player
	private byte startWeapon = PlayerData.REVOLVER;
	
	// slot of player save data
	private int numSlot;
	
	// instance of the parent
	private Launcher launcher;
	
	// constants for panel sizes
	private static final int LEFT_SIDE_WIDTH = 400, ENTER_NAME_HEIGHT = 128;
	
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
		setBorder(new JJBorder());
		
		// create the left side
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		leftSide.setPreferredSize(new Dimension(LEFT_SIDE_WIDTH, JavaJesus.WINDOW_HEIGHT));
		
		// create the components in the middle slot
		leftSide.add(pScreen = new PlayerGUI(LEFT_SIDE_WIDTH, JavaJesus.WINDOW_HEIGHT - ENTER_NAME_HEIGHT));
		leftSide.add(name = new JJTextField());
		
		// create the right side
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH - LEFT_SIDE_WIDTH, JavaJesus.WINDOW_HEIGHT));
		
		// add the player information panel at the top
		JJPanel appearance = new JJPanel(JJStrings.PLAYER_INFO_TOP);
		appearance.setBorder(new EmptyBorder(70, 230, 70, 30));
		appearance.add(male = new JJButton("male"));
		male.isOn = true;
		appearance.add(female = new JJButton("male"));
		rightSide.add(appearance);
		
		// now add the row of buttons
		JPanel buttonContainer = new JPanel();
		buttonContainer.setBorder(new ButtonBorder());
		JPanel buttons = new JPanel(new FlowLayout(0, 0, FlowLayout.LEFT));
		buttons.add(skin = new JJButton("skin"));
		skin.isOn = true;
		buttons.add(hair = new JJButton("hair"));
		buttons.add(shirt = new JJButton("shirt"));
		buttons.add(pants = new JJButton("pants"));
		buttonContainer.add(buttons);
		rightSide.add(buttonContainer);
		
		// form the color panel
		rightSide.add(red = new RGBSlider((pScreen.getSkinColor() & 0x00FF0000) >> 16, Color.RED));
		rightSide.add(green = new RGBSlider((pScreen.getSkinColor() & 0x0000FF00) >> 8, Color.GREEN));
		rightSide.add(blue = new RGBSlider(pScreen.getSkinColor() & 0x000000FF, Color.BLUE));
		
		// create the container for the weapon options
		JJPanel weaponPanel = new JJPanel(JJStrings.PLAYER_STARTING_WEAP);
		weaponPanel.setLayout(new FlowLayout(0, 0, FlowLayout.LEFT));
		
		// create a border for the following buttons
		JPanel options = new JPanel();
		options.setLayout(new BorderLayout(0, 15));
		options.setBorder(new EmptyBorder(67, 54, 25, 300));
		options.add(sword = new JJButton("sword"), BorderLayout.NORTH);
		options.add(pistol = new JJButton("gun"), BorderLayout.CENTER);
		pistol.isOn = true;
		options.setBackground(new Color(0, 0, 0, 0));
		weaponPanel.add(options);
		rightSide.add(weaponPanel);
		
		// add the buttons
		JPanel last = new JPanel(new FlowLayout(0, 0, FlowLayout.LEFT));
		last.add(back = new JJButton("back"));
		last.add(finish = new JJButton("finish"));
		rightSide.add(last);

		// add all the components together
		add(leftSide, BorderLayout.CENTER);
		add(rightSide, BorderLayout.EAST);
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
			skin.isOn = true;
			
			// turn off all other buttons
			shirt.isOn = false;
			hair.isOn = false;
			pants.isOn = false;
			
			// wrap the color
			Color c = new Color(pScreen.getSkinColor());
			
			// move the sliders over to current value
			red.setValue(c.getRed());
			green.setValue(c.getGreen());
			blue.setValue(c.getBlue());
			
		}
		
		if (e.getSource() == shirt) {
			active = SHIRT_SCREEN;
			shirt.isOn = true;
			
			// turn off all other buttons
			skin.isOn = false;
			hair.isOn = false;
			pants.isOn = false;
			
			// wrap the color
			Color c = new Color(pScreen.getShirtColor());
			
			// move the sliders over to current value
			red.setValue(c.getRed());
			green.setValue(c.getGreen());
			blue.setValue(c.getBlue());
		}
		
		if (e.getSource() == hair) {
			active = HAIR_SCREEN;
			hair.isOn = true;
			
			// turn off all other buttons
			skin.isOn = false;
			shirt.isOn = false;
			pants.isOn = false;
			
			// wrap the color
			Color c = new Color(pScreen.getHairColor());
			
			// move the sliders over to current value
			red.setValue(c.getRed());
			green.setValue(c.getGreen());
			blue.setValue(c.getBlue());
		}
		
		if (e.getSource() == pants) {
			active = PANTS_SCREEN;
			pants.isOn = true;
			
			// turn off all other buttons
			skin.isOn = false;
			shirt.isOn = false;
			hair.isOn = false;
			
			// wrap the color
			Color c = new Color(pScreen.getPantsColor());
			
			// move the sliders over to current value
			red.setValue(c.getRed());
			green.setValue(c.getGreen());
			blue.setValue(c.getBlue());
		}
		
		// male selected
		if (e.getSource() == male) {
			male.isOn = true;
			female.isOn = false;
			pScreen.setGender(PlayerData.MALE);
		}
		
		// female selected
		if (e.getSource() == female) {
			male.isOn = false;
			female.isOn = true;
			pScreen.setGender(PlayerData.FEMALE);
		}
		
		// change display to pistol
		if (e.getSource() == pistol) {
			pistol.isOn = true;
			sword.isOn = false;
			startWeapon = PlayerData.REVOLVER;
		}
		
		// change display to sword
		if (e.getSource() == sword) {
			sword.isOn = true;
			pistol.isOn = false;
			startWeapon = PlayerData.SHORT_SWORD;
		}
		
		// player clicks back
		if (e.getSource() == back) {
			
			// return to main display
			((CardLayout) launcher.getParent().getLayout()).show(launcher.getParent(), Launcher.MAIN);
			launcher.updateButtons();
			
		}
		
		// create the player
		if (e.getSource() == finish) {
			
			// create a save file
			PlayerData.save(numSlot, name.getText(),  pScreen.getSkinColor(), pScreen.getHairColor(), 
					pScreen.getShirtColor(), pScreen.getPantsColor(), pScreen.getGender(), startWeapon);
			
			// return to main display
			((CardLayout) launcher.getParent().getLayout()).show(launcher.getParent(), Launcher.MAIN);
			launcher.updateButtons();
			
		}
		
		// now repaint
		repaint();
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
			
			// set the right border border
			String path;
			if (color == Color.RED) {
				path = JJStrings.RED_SLIDER;
			} else if (color == Color.GREEN) {
				path = JJStrings.GREEN_SLIDER;
			} else {
				path = JJStrings.BLUE_SLIDER;
			}
			setBorder(new RGBSliderBorder(path));
			
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
			} else if (active == HAIR_SCREEN) {
				pScreen.setHairColor(new Color(r, g, b).getRGB());
			} else if (active == PANTS_SCREEN) {
				pScreen.setPantsColor(new Color(r, g, b).getRGB());
			}
			
			// repaint the player
			pScreen.repaint();
			
		}
		
	}
	
	/*
	 * Automatically aligns the JButton
	 * and sets the Font
	 */
	private class JJButton extends JButton {
		
		// serialization
		private static final long serialVersionUID = 1L;
		
		// the backgrounds for the buttons
		private BufferedImage button_on, button_off;
		
		// base directory
		private static final String DIR = "/VISUAL_DATA/GUI/BUTTONS/HUD_BUTTONS/";
		
		// extensions for each file type
		private static final String ON = "_on.png", OFF = "_off.png";
		
		// whether or not the button is on
		private boolean isOn;
		
		/**
		 * JJButton ctor()
		 * @param s - Button text
		 */
		private JJButton(String pathName) {
			super();
			
			// load the backgrounds
			try {
				button_on = ImageIO.read(OverviewGUI.class.getResource(DIR + pathName + ON));
				button_off = ImageIO.read(OverviewGUI.class.getResource(DIR + pathName + OFF));

				// set the dimensions
				setPreferredSize(new Dimension(button_on.getWidth(), button_on.getHeight()));

				// file doesnt exist
			} catch (Exception e) {
				System.err.println("Couldn't load " + pathName);
			}
			
			// add input listener
			addActionListener(PlayerCreationGUI.this);
		}
		
		/**
		 * Paints the background of the button
		 */
		@Override
		public void paintComponent(Graphics g) {
			
			// draw right state
			if (isOn) {
				g.drawImage(button_on, 0, 0, getWidth(), getHeight(), null);
			} else {
				g.drawImage(button_off, 0, 0, getWidth(), getHeight(), null);
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
	 * JTextField with an image background
	 */
	private class JJTextField extends JTextField {

		// serialization
		private static final long serialVersionUID = 1L;

		// image to render
		private BufferedImage background;
		
		// the font to use
		private final Font font = new Font(JavaJesus.FONT_NAME, 0, 25);

		/**
		 * Creates a customizable JPanel with text
		 * 
		 * @param path - the path to the image to render
		 */
		private JJTextField() {
			super();

			// load the background
			try {
				background = ImageIO.read(PlayerCreationGUI.class.getResource(JJStrings.ENTER_NAME));
			} catch (IOException e) {
				System.err.println("Couldn't load enter name panel");
			}
			
			// make the background transparent
			setBackground(new Color(0, 0, 0, 0));
			
			// set the inset bounds
			setBorder(new EmptyBorder(50, 18, 11, 12));	
			
			// set the font and color
			setFont(font);
			setForeground(Color.WHITE);
		}

		/**
		 * @param g - graphics used to draw the image
		 */
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
			
			// now draw the text
			super.paintComponent(g);
		}

	}
	
	/*
	 * JPanel with an image background
	 */
	private class JJPanel extends JPanel {

		// serialization
		private static final long serialVersionUID = 1L;

		// image to render
		private BufferedImage background;

		/**
		 * Creates a customizable JPanel with text
		 * 
		 * @param path - the path to the image to render
		 */
		private JJPanel(String path) {
			super();

			// load the background
			try {
				background = ImageIO.read(PlayerCreationGUI.class.getResource(path));
				setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
			} catch (IOException e) {
				System.err.println("Couldn't load " + path);
			}
		}

		/**
		 * @param g - graphics used to draw the image
		 */
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		}

	}
	
}
