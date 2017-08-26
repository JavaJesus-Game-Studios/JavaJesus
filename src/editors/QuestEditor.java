package editors;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javajesus.dataIO.QuestData;

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
	private final JLabel stateLabel, fileLabel;
	private final JJTextArea giverDialogue, objectiveSummary, response1, response2, response3, response1Triggers,
	        response2Triggers, response3Triggers, endDialogue, endTriggers;
	
	// ids of the buttons added to the quest tree
	private static int id;
	
	// the last button clicked
	private JJButton last;
	
	// gets the top level directory
	private static final String DIR = "res/WORLD_DATA/QUEST_DATA/";
	
	// name of the quest
	private String name = "New";
	
	// extension
	private static final String JSON = ".json";
	
	// filepath
	private String filePath = new File(DIR + name + JSON).getPath();

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
		top.add(fileLabel = new JLabel(name));
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
		rightSide.add(new JLabel("Quest Summary:"));
		rightSide.add(new JScrollPane(objectiveSummary = new JJTextArea("N/A")));
		rightSide.add(new JLabel("Response 1:"));
		rightSide.add(new JScrollPane(response1 = new JJTextArea("N/A")));
		rightSide.add(new JLabel("Response 1 Triggers:"));
		rightSide.add(new JScrollPane(response1Triggers = new JJTextArea("1")));
		rightSide.add(new JLabel("Response 2:"));
		rightSide.add(new JScrollPane(response2 = new JJTextArea("N/A")));
		rightSide.add(new JLabel("Response 2 Triggers:"));
		rightSide.add(new JScrollPane(response2Triggers = new JJTextArea("2")));
		rightSide.add(new JLabel("Response 3:"));
		rightSide.add(new JScrollPane(response3 = new JJTextArea("N/A")));
		rightSide.add(new JLabel("Response 3 Triggers:"));
		rightSide.add(new JScrollPane(response3Triggers = new JJTextArea("3")));
		rightSide.add(new JLabel("End Dialogue:"));
		rightSide.add(new JScrollPane(endDialogue = new JJTextArea("N/A")));
		rightSide.add(new JLabel("End Triggers:"));
		rightSide.add(new JScrollPane(endTriggers = new JJTextArea("")));
		
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
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// save the data of the last button
		if (last != null) {
			last.save();
			
			// last is null
		} else {
			// set it to the first button
			last = (JJButton) ((Container) questTree.getComponent(0)).getComponent(0);
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
				
				// create the file chooser
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setCurrentDirectory(new File(DIR));

				// open the chooser
				if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

					// load the file!
					File file = fc.getSelectedFile();
					
					// add the extension if it isn't there
					if (!file.getPath().contains(JSON)) {
						
						// set file path
						filePath = file.getPath() + JSON;

						// set file name
						name = file.getName();

						// contains JSON extension
					} else {

						// file path
						filePath = file.getPath();

						// set the name
						name = file.getName().substring(0, file.getName().length() - 5);
					}
					
					// update the file label
					fileLabel.setText(name);
					
					// the number of layers to remove
					int layers = questTree.getComponentCount() - 1;
					
					// remove existing layers
					for (int i = 0; i < layers; i++) {
						removeLayer();
					}

					// load if the file exists
					if (file.exists()) {

						// load the JSON
						JSONArray data = null;
						try {
							data = QuestData.load(new FileInputStream(file));
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}

						// make sure it parsed
						if (data != null) {

							// get the number layers to add
							int numLayers = (int) (Math.log(data.size()) / Math.log(3));

							// add the layers
							for (int i = 0; i < numLayers; i++) {
								addLayer();
							}

							// iterate through all rows
							for (int i = 0; i < questTree.getComponentCount(); i++) {

								// the quest row
								Container row = (Container) questTree.getComponent(i);

								// iterate through columns
								for (int j = 0; j < row.getComponentCount(); j++) {

									// the JJButton child
									JJButton child = (JJButton) row.getComponent(j);

									// load the JSON
									child.loadJSON((JSONObject) data.remove(0));
								}
							}

							// now set the display to the first element
							((JJButton) ((Container) questTree.getComponent(0)).getComponent(0)).load();

						}
					}
				}
				
				// save the quest tree
			} else if (e.getSource() == save) {
				
				// save the current button
				last.save();
				
				// construct the JSON Array
				JSONArray array = new JSONArray();
				
				// iterate through all rows
				for (int i = 0; i < questTree.getComponentCount(); i++) {
					
					// the quest row
					Container row = (Container) questTree.getComponent(i);

					// iterate through columns
					for (int j = 0; j < row.getComponentCount(); j++) {

						// the JJButton child
						JJButton child = (JJButton) row.getComponent(j);

						// wrap it and add it to the array
						array.add(QuestData.wrap(child.giverText, child.objText, child.res1Text, child.res2Text,
						        child.res3Text, child.trig1, child.trig2, child.trig3, child.endText, child.endTrig));
					}
				}
				
				// now write it to the file
				QuestData.save(filePath, array);
				
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
		private String giverText, objText, res1Text, res2Text, res3Text, trig1, trig2, trig3, endText, endTrig;

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
			endText = endDialogue.getText();
			trig1 = response1Triggers.getText();
			trig2 = response2Triggers.getText();
			trig3 = response3Triggers.getText();
			endTrig = endTriggers.getText();
			
		}
		
		/**
		 * @param obj - the json object that contains the data
		 */
		private void loadJSON(JSONObject obj) {
			giverText = (String) obj.get(QuestData.KEY_GIVER);
			objText = (String) obj.get(QuestData.KEY_OBJECTIVE);
			res1Text = (String) obj.get(QuestData.KEY_RESPONSE1);
			res2Text = (String) obj.get(QuestData.KEY_RESPONSE2);
			res3Text = (String) obj.get(QuestData.KEY_RESPONSE3);
			trig1 = (String) obj.get(QuestData.KEY_TRIGGERS1);
			trig2 = (String) obj.get(QuestData.KEY_TRIGGERS2);
			trig3 = (String) obj.get(QuestData.KEY_TRIGGERS3);
			endText = (String) obj.get(QuestData.KEY_END);
			endTrig = (String) obj.get(QuestData.KEY_END_TRIGGER);
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
			response1Triggers.setText(trig1);
			response2Triggers.setText(trig2);
			response3Triggers.setText(trig3);
			endDialogue.setText(endText);
			endTriggers.setText(endTrig);
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
