package editors;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class QuestEditor extends JPanel implements ActionListener {

	// serialization
	private static final long serialVersionUID = 1L;
	
	// dimensions of the window
	private static final int WIDTH = 1000, HEIGHT = 800;
	
	// buttons used
	private final JButton open, save, addLayer, removeLayer;
	
	// container for the quest tree
	private final JPanel questTree;
	
	// elements on the right side of the panel
	private final JLabel stateLabel;
	private final JJTextArea giverDialogue, objectiveSummary, response1, response2, response3;
	
	// ids of the buttons added to the quest tree
	private static int id;
	
	// the last button clicked
	private JJButton last;

	/**
	 * First method called in the editor
	 * 
	 * @param args - run time arguments
	 */
	public static void main(String[] args) {
		
		// construct the window
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new QuestEditor(WIDTH, HEIGHT));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setTitle("Quest Editor for Java Jesus by Derek Jow");
		frame.setVisible(true);
		frame.toFront();
		
	}
	
	/**
	 * @param width - width of the panel
	 * @param height - height of the panel
	 */
	public QuestEditor(int width, int height) {
		
		// set the size
		setPreferredSize(new Dimension(width, height));
		
		// set up the container
		setLayout(new BorderLayout(0, 0));
		
		// add the top layer
		JPanel top = new JPanel();
		top.add(open = new JButton("Open"));
		open.addActionListener(this);
		top.add(save = new JButton("Save"));
		save.addActionListener(this);
		top.add(addLayer = new JButton("Add Layer"));
		addLayer.addActionListener(this);
		top.add(removeLayer = new JButton("Remove Layer"));
		removeLayer.addActionListener(this);
		add(top, BorderLayout.NORTH);
		
		// add the center container
		JPanel center = new JPanel(new BorderLayout(0, 0));
		
		// add the quest tree panel
		questTree = new JPanel();
		questTree.setLayout(new BoxLayout(questTree, BoxLayout.Y_AXIS));
		questTree.setPreferredSize(new Dimension(WIDTH * 2 / 3, HEIGHT));
		
		// start with base case
		JPanel first = new JPanel(new GridLayout(1, 1));
		first.add(new JJButton());
		questTree.add(first);
		
		// now incase the quest tree in a scroll pane
		center.add(new JScrollPane(questTree), BorderLayout.CENTER);
		
		// now add the information panels on the right side
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
		rightSide.add(stateLabel = new JLabel("STATE: N/A"));
		rightSide.add(new JLabel("Giver Dialogue:"));
		rightSide.add(new JScrollPane(giverDialogue = new JJTextArea("N/A")));
		rightSide.add(new JLabel("Objective Summary:"));
		rightSide.add(new JScrollPane(objectiveSummary = new JJTextArea("N/A")));
		rightSide.add(new JLabel("Response 1:"));
		rightSide.add(new JScrollPane(response1 = new JJTextArea("N/A")));
		rightSide.add(new JLabel("Response 2:"));
		rightSide.add(new JScrollPane(response2 = new JJTextArea("N/A")));
		rightSide.add(new JLabel("Response 3:"));
		rightSide.add(new JScrollPane(response3 = new JJTextArea("N/A")));
		
		// encapsulate in a scroll pane
		JScrollPane pane = new JScrollPane(rightSide);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		center.add(pane, BorderLayout.EAST);
		
		// now add the center
		add(center, BorderLayout.CENTER);
		
	}
	
	/**
	 * Adds another layer to the quest tree container
	 */
	private void addLayer() {
		
		// get the number of elements in the last row
		int last = ((Container) questTree.getComponent(questTree.getComponentCount() - 1)).getComponentCount();
		
		// now add the next row
		JPanel next = new JPanel(new GridLayout(1, last * 3));
		
		// fill it with JJButtons
		for (int i = 0; i < last * 3; i++) {
			next.add(new JJButton());
		}
		
		// now add the row to the quest Tree
		questTree.add(next);
		
		// now revalidate
		revalidate();
		
	}
	
	/**
	 * Removes the most recent layer from the quest tree container
	 */
	private void removeLayer() {
		
		// only remove if there is at least 2 components
		if (questTree.getComponentCount() > 1) {
			
			// get the number of components in the last row
			int last = ((Container) questTree.getComponent(questTree.getComponentCount() - 1)).getComponentCount();
			
			// free up last number IDs
			id -= last;

			// now remove the row from the quest Tree
			questTree.remove(questTree.getComponentCount() - 1);

			// now revalidate
			revalidate();
		}
		
	}
	
	/**
	 * Logic on button pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// save the data of the last button
		if (last != null) {
			last.save();
		}
		
		// quest tree button logic first
		if (e.getSource() instanceof JJButton) {
			
			// now set last to this one
			last = (JJButton) e.getSource();
			
			// load its data fields
			last.load();
			
			// buttons at top panel
		} else {
			
			// open a new quest page
			if (e.getSource() == open) {
				
				
				// save the quest tree
			} else if (e.getSource() == save) {
				
				
				// add a new layer to the quest tree
			} else if (e.getSource() == addLayer) {
				addLayer();
				
				// remove the recent layer
			} else if (e.getSource() == removeLayer) {
				removeLayer();
			}
			
		}
		
	}
	
	/*
	 * Added functionality to JButton
	 */
	private class JJButton extends JButton {
		
		// serialization
		private static final long serialVersionUID = 1L;
		
		// id of this button
		private int id;
		
		// text fields of the state
		private String giverText, objText, res1Text, res2Text, res3Text;

		/**
		 * JJButton ctor()
		 */
		private JJButton() {
			super(String.valueOf(QuestEditor.id));
			
			// set the id and increment it
			this.id = QuestEditor.id++;
			
			// add action listner to quest editor
			addActionListener(QuestEditor.this);
		}
		
		/**
		 * Saves the information from the information panel into
		 * this button's fields
		 */
		private void save() {
			
			giverText = giverDialogue.getText();
			objText = objectiveSummary.getText();
			res1Text = response1.getText();
			res2Text = response2.getText();
			res3Text = response3.getText();
			
		}
		
		/**
		 * Loads the information from the button's data fields
		 * into the the information panel
		 */
		private void load() {
			
			stateLabel.setText("State: " + id);
			giverDialogue.setText(giverText);
			objectiveSummary.setText(objText);
			response1.setText(res1Text);
			response2.setText(res2Text);
			response3.setText(res3Text);
		}
		
	}
	
	/*
	 * JJTextArea with added functionality
	 */
	private class JJTextArea extends JTextArea {
		
		// serialization
		private static final long serialVersionUID = 1L;

		/**
		 * @param text - text to display
		 */
		private JJTextArea(String text) {
			super(text);
			
			setLineWrap(true);
			setWrapStyleWord(true);
		}
		
	}

}
