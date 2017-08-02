package javajesus.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import javajesus.JavaJesus;
import javajesus.SoundHandler;
import javajesus.entities.Player;
import javajesus.items.Item;
import javajesus.utility.JJStrings;

/*
 * The Overview Menu of the Inventory Screen
 */
public class OverviewGUI extends JPanel implements FocusListener {

	// serialization
	private static final long serialVersionUID = 1L;

	// layout used to switch containers
	private CardLayout cl;

	// IDs of each inventory screen
	private static final String MAIN = "overview", INVENTORY = "inventory", QUESTS = "missions",
			FACTIONS = "factions", MAP = "worldmap";
	
	// viewing panel that changes
	private JPanel viewing;

	// buttons at the top
	private JJButton overview, inventory, factions, quests, map;
	
	// Inventory modifiers
	private static final int NUM_ROWS = 6, NUM_COLS = 6, INVENTORY_SPACE = NUM_ROWS * NUM_COLS;
	
	// dimensions of the buttons
	private static final int BUTTON_WIDTH = JavaJesus.WINDOW_WIDTH / 5, BUTTON_HEIGHT = 80;

	// constants for the main view
	private static final int PLAYER_PANEL_WIDTH = 374, NAME_BOX_HEIGHT = 79, STATUS_BAR_HEIGHT = 65,
			OBJ_HEIGHT = 145;
	
	// constants for the inventory screen
	private static final int LEFT_SIDE_WIDTH = 290, ITEM_DISPLAY_HEIGHT = 290, TOP_HEIGHT = 33, MID_HEIGHT = 31, BOTTOM_HEIGHT = 150, CURRENCY_HEIGHT = 58;
	
	// The player in the Overview GUI
	private Player player;
	
	// extension of image files
	private static final String PNG = ".png";
	
	// inventory panel
	private InventoryGUI invenPanel;

	/**
	 * Overview screen ctor()
	 */
	public OverviewGUI(Player player) {
		
		// set the player
		this.player = player;

		// set up the panel
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH,
				JavaJesus.WINDOW_HEIGHT));
		addFocusListener(this);
		
		// add the button panel to the top
		JPanel top = new JPanel(new GridLayout(1, 5));
		top.add(overview = new JJButton(MAIN, true, false));
		overview.active = true;
		top.add(inventory = new JJButton(INVENTORY, true, false));
		top.add(factions = new JJButton(FACTIONS, true, false));
		top.add(quests = new JJButton(QUESTS, true, false));
		top.add(map = new JJButton(MAP, true, false));
		
		// set up the viewing panel
		viewing = new JPanel();
		viewing.setLayout(cl = new CardLayout(0, 0));
		
		// add the components to the viewing panel
		viewing.add(new MainGUI(), MAIN);
		viewing.add(invenPanel = new InventoryGUI(), INVENTORY);
		viewing.add(new FactionGUI(), FACTIONS);
		viewing.add(new QuestGUI(), QUESTS);
		viewing.add(new MapGUI(), MAP);

		// add all components to the screen
		add(top, BorderLayout.NORTH);
		add(viewing, BorderLayout.CENTER);
		
		// add the border
		setBorder(new JJBorder());

	}
	
	/*
	 * Inventory display
	 */
	private class InventoryGUI extends JPanel implements ActionListener, MouseListener {
		
		// serialization
		private static final long serialVersionUID = 1L;
		
		// bottom panel buttons
		private JJButton use, equip, drop;
		
		// actual inventory panel with grid layout
		private JPanel main;
		
		// selected item
		private ItemGUI selected;
		
		// selected descriptions
		private JJPanel name, info, money;
		private JJTextArea description;

		/**
		 * InventoryGUI ctor()
		 */
		private InventoryGUI() {
			
			// set up the panel
			setPreferredSize(viewing.getPreferredSize());
			setLayout(new BorderLayout(0, 0));
			
			// left side that contains item and description
			JPanel leftSide = new JPanel();
			leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
			leftSide.setPreferredSize(new Dimension(LEFT_SIDE_WIDTH, leftSide.getPreferredSize().height));
			
			// add left side components
			leftSide.add(selected = new ItemGUI(Item.blank, 0));
			selected.setPreferredSize(new Dimension(LEFT_SIDE_WIDTH, ITEM_DISPLAY_HEIGHT)); 
			leftSide.add(name = new JJPanel(JJStrings.INFO_TOP, LEFT_SIDE_WIDTH, TOP_HEIGHT, "Empty", 0, 5, 15));
			leftSide.add(info = new JJPanel(JJStrings.INFO_MIDDLE, LEFT_SIDE_WIDTH, MID_HEIGHT, "Amount: 0", 0, 0, 15));
			JScrollPane pane = new JScrollPane(description = new JJTextArea("None"));
			pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			pane.getVerticalScrollBar().setUI(new VerticalSliderUI());
			pane.setBorder(null);
			leftSide.add(pane);
			leftSide.add(money = new JJPanel(JJStrings.INFO_CURRENCY, LEFT_SIDE_WIDTH, CURRENCY_HEIGHT, "$0"));
			
			// construct the buttons at the bottom
			JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
			row.add(use = new JJButton("use", false, true));
			row.add(equip = new JJButton("equip", false, true));
			equip.setVisible(false);
			row.add(drop = new JJButton("drop", false, true));
			use.addActionListener(this);
			equip.addActionListener(this);
			drop.addActionListener(this);
			leftSide.add(row);
			
			// the main panel contains the grid layout
			main = new JPanel(new GridLayout(NUM_ROWS, NUM_COLS));
			for (int i = 0; i < INVENTORY_SPACE; i++) {
				
				// add the mouse listener to this inventory panel
				ItemGUI slot = new ItemGUI(Item.blank, i);
				slot.addMouseListener(this);
				
				// add the slot to the grid layout
				main.add(slot);
			}
			
			// add the components to this panel
			add(leftSide, BorderLayout.WEST);
			add(main, BorderLayout.CENTER);
			
		}

		/**
		 * Actions when the bottom buttons are pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// button clicked
			SoundHandler.play(SoundHandler.click);
			
			if (e.getSource() == use || e.getSource() == equip) {
				
				if (selected.getItem() != null && selected.getItem().isUsable()) {
					
					// use and remove
					selected.getItem().use(player);
					player.getInventory().remove(selected.getItem());
					
					// remove the selected item
					// set the descriptors on the left side
					selected.setItem(Item.blank);
					name.setText(Item.blank.getName());
					description.setText(Item.blank.getDescription());
				}

				// discard
			} else if (e.getSource() == drop) {
				
				if (selected.getItem() != null) {
					player.getInventory().discard(selected.getItem());
				}

			}
			
			// repaint the inventory screen
			update();
		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * Handles mouse clicking event
		 * @param e - the event fired
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			
			// get the item gui that was clicked
			Item clicked = ((ItemGUI) main.getComponent(((ItemGUI) e.getSource()).getId())).getItem();
			
			// set the descriptors on the left side
			selected.setItem(clicked);
			name.setText(clicked.getName());
			
			// display different information depending on the object
			if (clicked.contains("Ammo")) {
				info.setText("Ammo: " + clicked.getQuantity());
			} else if (clicked.contains("Sword") || clicked.contains("Gun")) {
				info.setText("Durability: N/A");
				use.setVisible(false);
				equip.setVisible(true);
			} else {
				info.setText("Amount: " + clicked.getQuantity());
				use.setVisible(true);
				equip.setVisible(false);
			}
			
			description.setText(clicked.getDescription());

			// repaint the inventory screen
			repaint();
			
			// bring viewing pane back into focus
			viewing.requestFocusInWindow();
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * Update the inventory when the screen is entered
		 */
		private void update() {
			
			// iterate through all the slots
			for (int i = 0; i < INVENTORY_SPACE; i++) {
				
				// set the item to the appropriate item
				if (player.getInventory().get(i) == null) {
					((ItemGUI) main.getComponent(i)).setItem(Item.blank);
				} else {
					((ItemGUI) main.getComponent(i)).setItem(player.getInventory().get(i));
				}
				
			}
			
			// force a repaint
			repaint();
			
			// bring viewing pane back into focus
			viewing.requestFocusInWindow();
			
		}
		
	}
	
	/*
	 * The Main overview display
	 */
	private class MainGUI extends JPanel {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * MainGUI ctor()
		 */
		private MainGUI() {
			
			// set up the panel
			setPreferredSize(viewing.getPreferredSize());
			setLayout(new BorderLayout(0, 0));
			
			// set up the left side
			JPanel leftSide = new JPanel();
			leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
			PlayerGUI pPanel = new PlayerGUI(PLAYER_PANEL_WIDTH, JavaJesus.WINDOW_HEIGHT - NAME_BOX_HEIGHT - BUTTON_HEIGHT);
			pPanel.setSkinColor(player.getSkinColor());
			pPanel.setShirtColor(player.getShirtColor());
			pPanel.setHairColor(player.getHairColor());
			pPanel.setPantsColor(player.getPantsColor());
			pPanel.setGender(player.getType());
			leftSide.add(pPanel);
			leftSide.add(new JJPanel(JJStrings.PLAYER_NAME, PLAYER_PANEL_WIDTH, NAME_BOX_HEIGHT, player.getName()));
			
			// add the left side
			add(leftSide, BorderLayout.WEST);
			
			// create the right side
			JPanel rightSide = new JPanel();
			rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
			
			// status bar
			JPanel status = new JPanel(new GridLayout(3, 1));
			status.add(new JJPanel(JJStrings.STATUS_TOP, JavaJesus.WINDOW_WIDTH - PLAYER_PANEL_WIDTH, STATUS_BAR_HEIGHT));
			status.add(new JJPanel(JJStrings.STATUS_MIDDLE, JavaJesus.WINDOW_WIDTH - PLAYER_PANEL_WIDTH, STATUS_BAR_HEIGHT));
			status.add(new JJPanel(JJStrings.STATUS_BOTTOM, JavaJesus.WINDOW_WIDTH - PLAYER_PANEL_WIDTH, STATUS_BAR_HEIGHT));
			rightSide.add(status);
			
			// objective panel
			rightSide.add(new JJPanel(JJStrings.OVERVIEW_OBJ, JavaJesus.WINDOW_WIDTH - PLAYER_PANEL_WIDTH, OBJ_HEIGHT));
			
			// location panel
			rightSide.add(new JJPanel(JJStrings.OVERVIEW_LOC,
					JavaJesus.WINDOW_WIDTH - PLAYER_PANEL_WIDTH,
					JavaJesus.WINDOW_HEIGHT - BUTTON_HEIGHT - STATUS_BAR_HEIGHT
							- OBJ_HEIGHT));

			// add in the rightside
			add(rightSide, BorderLayout.CENTER);
		}
		
	}
	
	/*
	 * The faction display
	 */
	private class FactionGUI extends JPanel {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * FactionGUI ctor()
		 */
		private FactionGUI() {
			
			// set up the panel
			setPreferredSize(viewing.getPreferredSize());
			setLayout(new BorderLayout(0, 0));
			
			// add in the rightside
			add(new JJLabel("FACTION TODO"), BorderLayout.CENTER);
		}
		
	}
	
	/*
	 * The quests display
	 */
	private class QuestGUI extends JPanel {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * QuestGUI ctor()
		 */
		private QuestGUI() {
			
			// set up the panel
			setPreferredSize(viewing.getPreferredSize());
			setLayout(new BorderLayout(0, 0));
			
			// add in the rightside
			add(new JJLabel("QUEST TODO"), BorderLayout.CENTER);
		}
		
	}
	
	/*
	 * The map display
	 */
	private class MapGUI extends JPanel {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * MapGUI ctor()
		 */
		private MapGUI() {
			
			// set up the panel
			setPreferredSize(viewing.getPreferredSize());
			setLayout(new BorderLayout(0, 0));
			
			// add in the rightside
			add(new JJLabel("MAP TODO"), BorderLayout.CENTER);
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
		
		// for text placement
		private int xOffset, yOffset;
		private String message;
		private Font font = new Font(JavaJesus.FONT_NAME, 0, 30);
		
		/**
		 * Creates a customizable JPanel
		 * 
		 * @param path - the path to the image to render
		 * @param width - width of the panel
		 * @param height - height of the panel
		 */
		private JJPanel(String path, int width, int height) {
			
			// set the size
			setPreferredSize(new Dimension(width, height));
			
			// load the buffered image
			try {
				background = ImageIO.read(OverviewGUI.class.getResourceAsStream(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		/**
		 * Creates a customizable JPanel with text
		 * 
		 * @param path - the path to the image to render
		 * @param width - width of the panel
		 * @param height - height of the panel
		 * @param text - text to display
		 * @param xOffset - x offset from left
		 * @param yOffset - y offset from top
		 */
		private JJPanel(String path, int width, int height, String text) {
			this(path, width, height);
			
			// instance data
			this.message = text;
		}
		
		/**
		 * Creates a customizable JPanel with text
		 * 
		 * @param path - the path to the image to render
		 * @param width - width of the panel
		 * @param height - height of the panel
		 * @param text - text to display
		 * @param xOffset - x offset from left
		 * @param yOffset - y offset from top
		 * @param fontSize - the size of font to use
		 */
		private JJPanel(String path, int width, int height, String text, int xOffset, int yOffset, int fontSize) {
			this(path, width, height);
			
			// instance data
			this.message = text;
			this.font = new Font(JavaJesus.FONT_NAME, 0, fontSize);
			this.xOffset = xOffset;
			this.yOffset = yOffset;
		}
		
		/**
		 * @param message - new text to display
		 */
		public void setText(String message) {
			this.message = message;
		}
		
		/**
		 * @param g - graphics used to draw the image
		 */
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
			
			// render text
			if (message != null) {
				g.setFont(font);
				g.setColor(Color.WHITE);
				
				// center it
				FontMetrics fm = g.getFontMetrics();
				int xPos = getWidth() / 2 - fm.stringWidth(message) / 2;
				int yPos = getHeight() / 2 - fm.getHeight() / 2 + fm.getHeight();
				g.drawString(message, xPos + xOffset, yPos + yOffset);
			}
		}
		
	}
	
	/*
	 * JButton with modified attributes
	 */
	private class JJButton extends JButton implements ActionListener {
		
		// serialization
		private static final long serialVersionUID = 1L;
		
		// the backgrounds for the buttons
		private BufferedImage button_on, button_off;
		
		// base directory of each button
		private static final String DIR = "/VISUAL_DATA/GUI/BUTTONS/HUD_BUTTONS/";
		
		// extensions for each file type
		private static final String ON = "_on.png", OFF = "_off.png";
		
		// whether or not it is active
		private boolean active;

		/**
		 * JJButton ctor()
		 * @param s - text in the button
		 */
		private JJButton(String id, boolean useThisActionListener, boolean useBorders) {
			
			// load the backgrounds
			try {
				button_on = ImageIO.read(OverviewGUI.class.getResource(DIR + id + ON));
				button_off = ImageIO.read(OverviewGUI.class.getResource(DIR + id + OFF));
				
				// set the dimensions
				setPreferredSize(new Dimension(button_on.getWidth(), button_on.getHeight()));
				
				// file doesnt exist
			} catch (Exception e) {
				// add font
				setFont(new Font(JavaJesus.FONT_NAME, Font.PLAIN, 20));
				setText(id);
				
				// maximize the size
				setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
			}
			
			// remove the borders
			if (!useBorders) {
				setBorder(null);
			}
			
			// add action listener'
			if (useThisActionListener) {
				addActionListener(this);
			}
			
		}

		/**
		 * Changes the tabs based on what button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// action performed on a main tab button
			if (button_on != null) {

				// turn off all buttons
				overview.active = false;
				inventory.active = false;
				factions.active = false;
				quests.active = false;
				map.active = false;

				// change the viewed tab
				if (e.getSource() == overview) {
					cl.show(viewing, MAIN);
				} else if (e.getSource() == inventory) {
					cl.show(viewing, INVENTORY);
				} else if (e.getSource() == factions) {
					cl.show(viewing, FACTIONS);
				} else if (e.getSource() == quests) {
					cl.show(viewing, QUESTS);
				} else if (e.getSource() == map) {
					cl.show(viewing, MAP);
				}

				// turn on THIS button
				active = true;

				// grab focus
				viewing.requestFocusInWindow();
			}
			
		}
		
		/**
		 * Paints the background of the button
		 */
		@Override
		public void paintComponent(Graphics g) {
			
			// draw right state
			if (active) {
				g.drawImage(button_on, 0, 0, getWidth(), getHeight(), null);
			} else {

				// draw the off state or render text
				if (button_off != null) {
					g.drawImage(button_off, 0, 0, getWidth(), getHeight(), null);
				} else {
					super.paintComponent(g);
				}
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
		private JJLabel(String text) {
			super(text);
			
			// align to middle and set font
			setAlignmentX(Component.CENTER_ALIGNMENT);
			setFont(new Font(JavaJesus.FONT_NAME, Font.PLAIN, 25));
			
		}
		
	}
	
	/*
	 * JText Area with modified attributes
	 */
	private class JJTextArea extends JTextArea {
		
		// serialization
		private static final long serialVersionUID = 1L;
		
		// background of the text area
		private BufferedImage bg;
		
		// directory of the background panel
		private static final String DIR = "/VISUAL_DATA/GUI/PANELS/INVENTORY/item_description_bottom" + PNG;
		
		/**
		 * @param text - text to display
		 */
		private JJTextArea(String text) {
			super(text);
			
			// set up the description text area
			setEditable(false);
			setFont(new Font(JavaJesus.FONT_NAME, 0, 16));
			setLineWrap(true);
			setWrapStyleWord(true);
			setForeground(Color.WHITE);
			setBorder(new EmptyBorder(15, 15, 12, 4));
			
			// make an invisible background from the text
			setBackground(new Color(0, 0, 0, 0));
			
			// set the size
			setPreferredSize(new Dimension(LEFT_SIDE_WIDTH, BOTTOM_HEIGHT));
			
			// load the background
			try {
				bg = ImageIO.read(OverviewGUI.class.getResource(DIR));
				
			} catch(IOException e) {
				System.err.println("Couldn't load " + DIR);
			}
		}
		
		/**
		 * Draw the background
		 */
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
			
			// draw the text
			super.paintComponent(g);
		}
		
	}
	
	/**
	 * @return viewing panel of the overview
	 */
	public JPanel getView() {
		return viewing;
	}

	/**
	 * Updates the inventory screen
	 * @param e
	 */
	@Override
	public void focusGained(FocusEvent e) {
		invenPanel.update();
	}

	@Override
	public void focusLost(FocusEvent e) {
		
	}

}

