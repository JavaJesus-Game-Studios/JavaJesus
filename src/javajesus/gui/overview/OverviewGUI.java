package javajesus.gui.overview;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javajesus.JavaJesus;
import javajesus.gui.ItemGUI;
import javajesus.gui.PlayerGUI;
import javajesus.items.Item;

/*
 * The Overview Menu of the Inventory Screen
 */
public class OverviewGUI extends JPanel implements KeyListener {

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
	private static final int NUM_ROWS = 5, NUM_COLS = 5;

	/**
	 * Overview screen ctor()
	 */
	public OverviewGUI() {

		// set up the panel
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH,
				JavaJesus.WINDOW_HEIGHT));
		addKeyListener(this);
		
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
		viewing.add(new InventoryGUI(), INVENTORY);

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
	private class InventoryGUI extends JPanel {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * InventoryGUI ctor()
		 */
		private InventoryGUI() {
			
			// set up the panel
			setPreferredSize(viewing.getPreferredSize());
			setLayout(new BorderLayout(0, 0));
			
			// the main panel contains the grid layout
			JPanel main = new JPanel(new GridLayout(NUM_ROWS, NUM_COLS));
			for (int i = 0; i < NUM_ROWS * NUM_COLS; i++) {
				main.add(new ItemGUI(8, 8, Item.apple));
			}
			
			// center pane that contains description and inventory
			JPanel center = new JPanel(new BorderLayout(0, 0));
			JJButton filler = new JJButton("Filler");
			filler.setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH / 3, filler.getPreferredSize().height));
			center.add(filler, BorderLayout.WEST);
			center.add(main, BorderLayout.CENTER);
			
			// the bottom panel contains actions
			JPanel bottom = new JPanel();
			bottom.add(new JJButton("A-Z"));
			bottom.add(new JJButton("ID"));
			bottom.add(new JJButton("Use"));
			bottom.add(new JJButton("Discard"));
			
			// add the components to the gui
			add(center, BorderLayout.CENTER);
			add(bottom, BorderLayout.SOUTH);
			
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
			
			// add left side
			add(new PlayerGUI(JavaJesus.WINDOW_WIDTH / 2, JavaJesus.HEIGHT), BorderLayout.CENTER);
			
			// create the right side
			JPanel rightSide = new JPanel();
			rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
			
			// add components to the right side
			rightSide.add(new JJLabel("Player Name Here"));
			rightSide.add(new JJLabel("Health: "));
			rightSide.add(new JJLabel("Stamina: "));
			rightSide.add(new JJLabel("Location: "));
			rightSide.add(new JJLabel("Alignment: "));
			
			// add in the rightside
			add(rightSide, BorderLayout.EAST);
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
		public JJButton(String s) {
			super(s);
			
			// add font
			setFont(new Font(JavaJesus.FONT_NAME, Font.PLAIN, 25));
			
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

}

