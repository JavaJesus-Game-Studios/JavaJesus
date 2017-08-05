package editors;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javajesus.items.Item;

/*
 * Creates a program to modify loot tables for
 * chests
 */
public class LootEditor extends JPanel implements ActionListener {

	// serialization
	private static final long serialVersionUID = 1L;

	// dimensions of the frame
	public static final int WIDTH = 700, HEIGHT = 700;

	// size of the cells in the table
	private static final int CELL_HEIGHT = 30;

	// gets the base directory
	private static final String BASE = "/WORLD_DATA/LOOT_DATA/";

	// gets the top level directory
	private static final String DIR = "res" + BASE;

	// list of items displayed in the current loot table
	private static ArrayList<Item> items;
	private static ArrayList<Integer> amounts;
	private static ArrayList<JJTextField> chances;

	// list of currently selected button
	private LootButton selected;

	// the panel in the middle
	private final JPanel middle, header;

	/**
	 * Main method
	 * 
	 * @param args - run time arguments
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Loot Editor for Java Jesus by Derek Jow");
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new LootEditor(WIDTH, HEIGHT));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.toFront();

	}

	/**
	 * Loot Editor ctor() Initializes basic information about the panel
	 * 
	 * @param width - width of the panel
	 * @param height - height of the panel
	 */
	public LootEditor(int width, int height) {

		// set the size and layout
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout(0, 0));

		// create the left side of the screen
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		leftSide.add(new JJLabel("Loot Tables"));
		loadTables(leftSide);

		// create the middle display
		middle = new JPanel();
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		header = new JPanel(new GridLayout(1, 3));
		header.add(new JJLabel("Item"));
		header.add(new JJLabel("Amount"));
		header.add(new JJLabel("Chance"));
		header.setMaximumSize(new Dimension(Integer.MAX_VALUE, CELL_HEIGHT));
		middle.add(header);

		// create the right side
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.add(new JJLabel("Item List"));
		loadItems(rightSide);

		// Add the components to the screen
		JScrollPane pane = new JScrollPane(leftSide);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(pane, BorderLayout.WEST);
		add(middle);
		pane = new JScrollPane(rightSide);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(pane, BorderLayout.EAST);
	}

	/**
	 * Sets the display in the middle
	 */
	private void update() {

		// remove all previous
		middle.removeAll();

		// add the labels
		middle.add(header);
		
		// now iterate through all iems
		for (int i = 0; i < items.size(); i++) {
			Item e = items.get(i);
			JPanel temp = new JPanel(new GridLayout(1, 3));
			temp.add(new JJLabel(e.getName()));
			temp.add(new JJLabel(String.valueOf(amounts.get(i))));
			temp.add(chances.get(i));
			temp.setMaximumSize(new Dimension(Integer.MAX_VALUE, CELL_HEIGHT));
			middle.add(temp);
		}

		// now revalidate the new items
		middle.revalidate();
		middle.repaint();
	}

	/**
	 * Helper method to load the tables
	 * 
	 * @param panel - panel to add the tables to
	 */
	private void loadTables(JPanel panel) {

		for (int i = 0; i < 127; i++) {
			panel.add(new LootButton(i));
		}

	}

	/**
	 * Helper method to load the item list
	 * 
	 * @param panel - panel to add the items to
	 */
	private void loadItems(JPanel panel) {

		// iterate over all items
		for (int i = 0; i < Item.getNumItems(); i++) {

			// no more items available
			if (Item.getItem(i) == null) {
				break;
			}

			// add the item panel
			JPanel temp = new JPanel(new BorderLayout());
			temp.add(new LootWeightButton(Item.getItem(i), LootWeightButton.REMOVE), BorderLayout.WEST);
			temp.add(new JJLabel(Item.getItem(i).getName()), BorderLayout.CENTER);
			temp.add(new LootWeightButton(Item.getItem(i), LootWeightButton.ADD), BorderLayout.EAST);

			// now add the panel
			panel.add(temp);

		}

	}

	/**
	 * Performed when a button is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// loot button clicked
		if (e.getSource() instanceof LootButton) {

			// get the button
			LootButton b = (LootButton) e.getSource();

			// set the items
			items = b.getItems();
			amounts = b.getAmounts();
			chances = b.getChances();
			this.selected = b;

			// loot weight item adjuster clicked
		} else if (e.getSource() instanceof LootWeightButton && selected != null) {

			// get the button
			LootWeightButton b = (LootWeightButton) e.getSource();

			// get the item to be modified
			Item item = b.getItem();

			// check if the item is in the list
			if (items.contains(item)) {

				// increment or decrement
				if (b.getId() == LootWeightButton.REMOVE) {

					// decrement the quantity or remove it
					if (amounts.get(items.indexOf(item)) > 1) {
						amounts.set(items.indexOf(item), amounts.get(items.indexOf(item)) - 1);
					} else {
						amounts.remove(items.indexOf(item));
						items.remove(item);
					}

					// increment
				} else {
					amounts.set(items.indexOf(item), amounts.get(items.indexOf(item)) + 1);
				}

				// add when not in the list
			} else if (b.getId() == LootWeightButton.ADD) {
				items.add(item);
				amounts.add(1);
				chances.add(new JJTextField(100));
			}

			// now update the save file
			save();
		}

		// now update the display
		if (items != null) {
			update();
		}
	}

	/**
	 * Save the loot table of the selected button to the save file
	 */
	private void save() {

		// can't save a table that isnt selected
		if (selected == null) {
			return;
		}

		// open the file
		File output = new File(DIR + selected.getId());

		// open the output stream
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(output))) {

			// now iterate through the item array
			for (int i = 0; i < items.size(); i++) {

				// write out the item data
				out.write(items.get(i).getId());
				out.write(amounts.get(i));
				out.write(Byte.parseByte(chances.get(i).getText()));

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * JButton with added functionality
	 */
	private class LootButton extends JButton {

		// serialization
		private static final long serialVersionUID = 1L;

		// unique ID of the button
		private int id;

		// list of items from this loot table
		private final ArrayList<Item> items = new ArrayList<Item>();
		private final ArrayList<Integer> amounts = new ArrayList<Integer>();
		private final ArrayList<JJTextField> chances = new ArrayList<JJTextField>();

		/**
		 * Creates a button with an associated ID
		 * 
		 * @param id - the id of the button
		 */
		private LootButton(int id) {
			super(String.valueOf(id));

			// instance data
			this.id = id;

			// add action listener
			addActionListener(LootEditor.this);

			// load the items
			load();
		}

		/**
		 * Loads the items into the array list
		 */
		private void load() {

			// get the right folder
			File input = new File(DIR + id);

			// load only if it exists
			if (input.exists()) {

				// create the input stream
				try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(input))) {

					// now load in the Items
					while (in.available() >= 3) {

						// load the item
						Item e = Item.getItem(in.read());

						// add it to the list
						items.add(e);
						amounts.add(in.read());
						chances.add(new JJTextField(in.read()));
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * @return list of items in this loot table
		 */
		public ArrayList<Item> getItems() {
			return items;
		}

		/**
		 * @return list of items in this loot table
		 */
		public ArrayList<Integer> getAmounts() {
			return amounts;
		}

		/**
		 * @return the chance for each item
		 */
		public ArrayList<JJTextField> getChances() {
			return chances;
		}

		/**
		 * @return id of this table
		 */
		public int getId() {
			return id;
		}

	}

	/*
	 * JButton with added functionality
	 */
	private class LootWeightButton extends JButton {

		// serialization
		private static final long serialVersionUID = 1L;

		// different IDs for the button
		private static final int REMOVE = 0, ADD = 1;

		// unique Item associated with this button
		private Item item;

		// whether or not this button adds or removes
		private int id;

		/**
		 * Creates a button with an associated ID
		 * 
		 * @param id - the id of the button
		 */
		private LootWeightButton(Item item, int id) {

			// set the string
			if (id == REMOVE) {
				this.setText("-");
			} else {
				this.setText("+");
			}

			// instance data
			this.item = item;
			this.id = id;

			// add action listener
			addActionListener(LootEditor.this);
		}

		/**
		 * @return item associated with this button
		 */
		private Item getItem() {
			return item;
		}

		/**
		 * @return id associated with this button
		 */
		private int getId() {
			return id;
		}

	}

	/*
	 * JLabel that is auto centered
	 */
	private class JJLabel extends JLabel {

		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * @param text - text to display
		 */
		public JJLabel(String text) {
			super(text, SwingConstants.CENTER);

			// auto center
			setAlignmentX(Component.CENTER_ALIGNMENT);
		}

	}
	
	/*
	 * JText Field with added functionality
	 */
	private class JJTextField extends JTextField implements DocumentListener {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * @param chance - chance displayed
		 */
		private JJTextField(int chance) {
			super(String.valueOf(chance));
			
			getDocument().addDocumentListener(this);
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			
			try {
				Integer.parseInt(getText());
				save();
			} catch (NumberFormatException exc) {	
				System.err.println("Value not byte!");
			}
			
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			try {
				Integer.parseInt(getText());
				save();
			} catch (NumberFormatException exc) {	
				System.err.println("Value not byte!");
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			try {
				Integer.parseInt(getText());
				save();
			} catch (NumberFormatException exc) {	
				System.err.println("Value not byte!");
			}
		}
	}

}
