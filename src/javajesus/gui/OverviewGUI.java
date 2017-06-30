package javajesus.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javajesus.JavaJesus;
import javajesus.entities.Player;
import javajesus.items.Item;

/*
 * The Overview Menu of the Inventory Screen
 */
public class OverviewGUI extends JPanel implements KeyListener, FocusListener {

	// serialization
	private static final long serialVersionUID = 1L;

	// layout used to switch containers
	private CardLayout cl;

	// IDs of each inventory screen
	private static final String MAIN = "Main", INVENTORY = "Inventory", QUESTS = "Quests",
			FACTIONS = "Factions", MAP = "Map";
	
	// viewing panel that changes
	private JPanel viewing;

	// buttons at the top
	private JJButton overview, inventory, factions, quests, map;
	
	// Inventory modifiers
	private static final int NUM_ROWS = 5, NUM_COLS = 5, INVENTORY_SPACE = NUM_ROWS * NUM_COLS;
	
	// The player in the Overview GUI
	private Player player;
	
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
		addKeyListener(this);
		addFocusListener(this);
		
		// add the button panel to the top
		JPanel top = new JPanel();
		top.add(overview = new JJButton("Overview"));
		top.add(inventory = new JJButton("Inventory"));
		top.add(factions = new JJButton("Factions"));
		top.add(quests = new JJButton("Quests"));
		top.add(map = new JJButton("Map"));
		
		// set up the viewing panel
		viewing = new JPanel();
		viewing.setLayout(cl = new CardLayout(0, 0));
		viewing.addKeyListener(this);
		
		// add the components to the viewing panel
		viewing.add(new MainGUI(), MAIN);
		viewing.add(invenPanel = new InventoryGUI(), INVENTORY);
		viewing.add(new FactionGUI(), FACTIONS);
		viewing.add(new QuestGUI(), QUESTS);
		viewing.add(new MapGUI(), MAP);

		// add all components to the screen
		add(top, BorderLayout.NORTH);
		add(viewing, BorderLayout.CENTER);

	}
	
	/**
	 * Leaves the screen when i or escape is pressed
	 * @param e - key event
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		// toggles game display
		if (e.getKeyCode() == KeyEvent.VK_I || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			JavaJesus.displayGame();
		}
	}
	
	/*
	 * Inventory display
	 */
	private class InventoryGUI extends JPanel implements ActionListener, MouseListener {
		
		// serialization
		private static final long serialVersionUID = 1L;
		
		// bottom panel buttons
		private JJButton sort1, sort2, use, discard, equip;
		
		// actual inventory panel with grid layout
		private JPanel main;
		
		// selected item
		private ItemGUI selected;
		
		// selected descriptions
		private JJLabel name;
		private JTextArea description;

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
			leftSide.setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH / 3, leftSide.getPreferredSize().height));
			
			// add left side components
			leftSide.add(selected = new ItemGUI(Item.blank, 0));
			selected.setMaximumSize(new Dimension(Integer.MAX_VALUE, JavaJesus.WINDOW_HEIGHT / 2)); 
			leftSide.add(name = new JJLabel("Empty"));
			leftSide.add(description = new JTextArea("None"));
			
			// set up the description text area
			description.setEditable(false);
			description.setMaximumSize(new Dimension(Integer.MAX_VALUE, JavaJesus.WINDOW_HEIGHT / 3));
			description.setFont(new Font(JavaJesus.FONT_NAME, 0, 16));
			description.setLineWrap(true);
			description.setWrapStyleWord(true);
			
			// the main panel contains the grid layout
			main = new JPanel(new GridLayout(NUM_ROWS, NUM_COLS));
			for (int i = 0; i < INVENTORY_SPACE; i++) {
				
				// add the mouse listener to this inventory panel
				ItemGUI slot = new ItemGUI(Item.blank, i);
				slot.addMouseListener(this);
				
				// add the slot to the grid layout
				main.add(slot);
			}
			
			// center pane that contains description and inventory
			JPanel center = new JPanel(new BorderLayout(0, 0));
			
			// add the components of the center panel
			center.add(leftSide, BorderLayout.WEST);
			center.add(main, BorderLayout.CENTER);
			
			// the bottom panel contains actions
			JPanel bottom = new JPanel();
			bottom.add(sort1 = new JJButton("A-Z"));
			bottom.add(sort2 = new JJButton("ID"));
			bottom.add(use = new JJButton("Use"));
			bottom.add(equip = new JJButton("Equip"));
			bottom.add(discard = new JJButton("Discard"));
			
			// add action listeners
			sort1.addActionListener(this);
			sort2.addActionListener(this);
			use.addActionListener(this);
			equip.addActionListener(this);
			discard.addActionListener(this);
			
			// add the components to the gui
			add(center, BorderLayout.CENTER);
			add(bottom, BorderLayout.SOUTH);
			
		}

		/**
		 * Actions when the bottom buttons are pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// alphabet sort
			if (e.getSource() == sort1) {

				player.getInventory().sortItemsAlphabetically();
				
				// id sort
			} else if (e.getSource() == sort2) {
				
				player.getInventory().sortItemsByID();

				// use
			} else if (e.getSource() == use) {
				
				if (selected.getItem() != null) {
					player.getInventory().use(selected.getItem());
				}

				// equip
			} else if (e.getSource() == equip) {
				
				if (selected.getItem() != null) {
					player.equip(selected.getItem());
				}

				// discard
			} else if (e.getSource() == discard) {
				
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
			ItemGUI clicked = (ItemGUI) main.getComponent(((ItemGUI) e.getSource()).getId());
			
			// set the descriptors on the left side
			selected.setItem(clicked.getItem());
			name.setText(clicked.getItem().getName());
			description.setText(clicked.getItem().getDescription());

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
			
			// set up the player panel
			PlayerGUI pPanel = new PlayerGUI(JavaJesus.WINDOW_WIDTH / 2, JavaJesus.HEIGHT);
			pPanel.setSkinColor(player.getSkinColor());
			pPanel.setShirtColor(player.getShirtColor());
			
			// add player panel to screen
			add(pPanel, BorderLayout.CENTER);
			
			// create the right side
			JPanel rightSide = new JPanel();
			rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
			
			// add components to the right side
			rightSide.add(new JJLabel("Name: " + player.getName()));
			rightSide.add(new JJLabel("Health: " + player.getCurrentHealth()));
			rightSide.add(new JJLabel("Stamina: " + player.getCurrentStamina()));
			rightSide.add(new JJLabel("Location: TODO"));
			rightSide.add(new JJLabel("Alignment: Neutral"));
			
			// add in the rightside
			add(rightSide, BorderLayout.EAST);
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
	 * JButton with modified attributes
	 */
	private class JJButton extends JButton implements ActionListener {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * JJButton ctor()
		 * @param s - text in the button
		 */
		private JJButton(String s) {
			super(s);
			
			// add font
			setFont(new Font(JavaJesus.FONT_NAME, Font.PLAIN, 20));
			
			// add action listener
			addActionListener(this);
		}

		/**
		 * Changes the tabs based on what button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
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
			
			// grab focus
			viewing.requestFocusInWindow();
			
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

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
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

