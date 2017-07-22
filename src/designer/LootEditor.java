package designer;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
public class LootEditor extends JPanel {
	
	// serialization
	private static final long serialVersionUID = 1L;
	
	// dimensions of the frame
	public static final int WIDTH = 700, HEIGHT = 700;
	
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
		JPanel middle = new JPanel();
		middle.add(new JLabel("TODO"));
		
		// create the right side
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
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
	 * Helper method to load the tables
	 * @param panel - panel to add the tables to
	 */
	private void loadTables(JPanel panel) {
		
		for (int i = 0; i < 256; i++) {
			panel.add(new JButton(String.valueOf(i)));
		}
		
	}
	
	/**
	 * Helper method to load the item list
	 * @param panel - panel to add the items to
	 */
	private void loadItems(JPanel panel) {
		
		// iterate over all items
		for (int i = 0; i < Item.items.length; i++) {
			
			// no more items available
			if (Item.items[i] == null) {
				break;
			}
			
			// add the item panel
			JPanel temp = new JPanel();
			temp.add(new JButton("-"));
			temp.add(new JLabel(Item.items[i].getName()));
			temp.add(new JButton("+"));
			
			// now add the panel
			panel.add(temp);
			
		}
		
		
	}

}
