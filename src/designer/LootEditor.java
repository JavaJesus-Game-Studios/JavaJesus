package designer;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	
	// gets the base directory
	private static final String BASE = "/WORLD_DATA/LOOT_DATA/";

	// gets the top level directory
	private static final String DIR = "res" + BASE;
	
	// list of items displayed in the current loot table
	private static ArrayList<Item> items;
	
	// list of currently selected button
	private LootButton selected;
	
	// the panel in the middle
	private final JPanel middle, header;
	
	// the chance modifier of each item
	private int chance = 100;
	
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
	 * Loot Editor ctor()
	 * Initializes basic information about the panel
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
		leftSide.add(new JLabel("Loot Tables"));
		loadTables(leftSide);
		
		// create the middle display
		middle = new JPanel();
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		header = new JPanel();
		header.add(new JLabel("Item"));
		header.add(new JLabel("Amount"));
		middle.add(header);
		
		// create the right side
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.add(new JLabel("Item List"));
		loadItems(rightSide);
		
		//Add the components to the screen
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
		for (Item e: items) {
			JPanel temp = new JPanel();
			temp.add(new JLabel(e.getName()));
			temp.add(new JLabel(String.valueOf(e.getQuantity())));
			middle.add(temp);
		}
		
		// now revalidate the new items
		middle.revalidate();
	}
	
	/**
	 * Helper method to load the tables
	 * @param panel - panel to add the tables to
	 */
	private void loadTables(JPanel panel) {
		
		for (int i = 0; i < 127; i++) {
			panel.add(new LootButton(i));
		}
		
	}
	
	/**
	 * Helper method to load the item list
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
			JPanel temp = new JPanel();
			temp.add(new LootWeightButton(Item.getItem(i), LootWeightButton.REMOVE));
			temp.add(new JLabel(Item.getItem(i).getName()));
			temp.add(new LootWeightButton(Item.getItem(i), LootWeightButton.ADD));
			
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
			this.selected = b;
			this.chance = b.getChance();
			
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
					if (item.getQuantity() > 1) {
						item.remove();
					} else {
						items.remove(item);
					}
					
					// increment
				} else {
					item.add();
				}
				
				// add when not in the list
			} else if (b.getId() == LootWeightButton.ADD) {
				items.add(item);
			}

			// now update the save file
			save();
		}
		
		// now update the display
		update();
	}
	
	/**
	 * Save the loot table of the selected button
	 * to the save file
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
			
			// first write the chance byte
			out.write(chance);
			
			// now iterate through the item array
			for (Item e: items) {
				
				// write out the item data
				out.write(e.getId());
				out.write(e.getQuantity());
				
			}
			
		}  catch (IOException e) {
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
		
		// the chance of each item appearing
		private int chance;
		
		// list of items from this loot table
		private final ArrayList<Item> items = new ArrayList<Item>();
		
		/**
		 * Creates a button with an
		 * associated ID
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
					
					// read the chance byte
					chance = in.read();
					
					// now load in the Items
					while (in.available() >= 2) {
						
						// load the item
						Item e = Item.getItem(in.read());
						e.setQuanity(in.read());
						
						// add it to the list
						items.add(e);
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
		 * @return the chance for each item
		 */
		public int getChance() {
			return chance;
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
		 * Creates a button with an
		 * associated ID
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

}
