package editors.quest;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.graphstream.graph.Node;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javajesus.dataIO.JSONData;

public class DataView implements ActionListener, ItemListener {

	// buttons used
	private final JButton open, save, addNode, removeNode, createNode;

	// elements on the right side of the panel
	private final JLabel fileLabel;
	private final JJTextArea giverDialogue, objectiveSummary, response1, response2, response3, response1Triggers,
			response2Triggers, response3Triggers, endDialogue, endTriggers, npcGiver, currentState, previousState;
	private final JCheckBox initial;

	// ids of the buttons added to the quest tree
	private static int id;
	
	// gets the top level directory
	private static final String DIR = "res/WORLD_DATA/QUEST_DATA/";

	// name of the quest
	private String name = "New";

	// extension
	private static final String JSON = ".json";

	// filepath
	private String filePath = new File(DIR + name + JSON).getPath();

	private JPanel panel;
	
	private QuestDataBuilder builder;
	
	private IDataLoaded iface;
	
	private HashMap<String, QuestNodeAdapter> nodeData;

	/**
	 * @param width  - width of the panel
	 * @param height - height of the panel
	 */
	public DataView() {
		
		builder = new QuestDataBuilder();
		nodeData = new HashMap<>();

		open = new JButton("Open");
		open.addActionListener(this);
		save = new JButton("Save");
		save.addActionListener(this);
		addNode = new JButton("Add Part");
		addNode.addActionListener(this);
		removeNode = new JButton("Remove This Part");
		createNode = new JButton("Create This Part");
		createNode.addActionListener(this);
		fileLabel = new JLabel(name);

		currentState = new JJTextArea("");
		currentState.setPreferredSize(new Dimension(150, 15));
		previousState = new JJTextArea("");
		previousState.setPreferredSize(new Dimension(150, 15));

		giverDialogue = new JJTextArea("");
		objectiveSummary = new JJTextArea("");
		response1 = new JJTextArea("");
		response1Triggers = new JJTextArea("");
		response2 = new JJTextArea("");
		response2Triggers = new JJTextArea("");
		response3 = new JJTextArea("");
		response3Triggers = new JJTextArea("");
		endDialogue = new JJTextArea("");
		endTriggers = new JJTextArea("");
		npcGiver = new JJTextArea("");
		npcGiver.setPreferredSize(new Dimension(200, 15));

		initial = new JCheckBox("First NPC quest?");
		initial.addItemListener(this);

		make();
	}
	
	public void setDataLoaded(IDataLoaded iface) {
		this.iface = iface;
	}

	private void make() {
		panel = new JPanel();

		// set up the container
		panel.setLayout(new BorderLayout(0, 0));

		// add the top layer
		JPanel top = new JPanel();
		top.add(open);
		top.add(save);
		top.add(addNode);

		top.add(fileLabel);
		panel.add(top, BorderLayout.NORTH);

		// now add the information panels on the right side
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));

		JPanel rightTop = new JPanel();
		rightTop.add(new JLabel("NPC Giver ID:"));
		rightTop.add(aligned(npcGiver, initial));
		rightSide.add(rightTop);
		rightSide.add(new JSeparator(SwingConstants.HORIZONTAL));

		rightSide.add(aligned(new JLabel("Giver Dialogue:"), giverDialogue));
		rightSide.add(aligned(new JLabel("Quest Summary:"), objectiveSummary));
		rightSide.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		rightSide.add(aligned(aligned(new JLabel("State ID:"), currentState),
				aligned(new JLabel("Prev State ID:"), previousState)));
		rightSide.add(aligned(new JLabel("Response 1:"), response1));
		rightSide.add(aligned(new JLabel("Response 1 Triggers:"), response1Triggers));
		rightSide.add(aligned(new JLabel("Response 2:"), response2));
		rightSide.add(aligned(new JLabel("Response 2 Triggers:"), response2Triggers));
		rightSide.add(aligned(new JLabel("Response 3:"), response3));
		rightSide.add(aligned(new JLabel("Response 3 Triggers:"), response3Triggers));
		rightSide.add(aligned(new JLabel("End Dialogue:"), endDialogue));
		rightSide.add(aligned(new JLabel("End Triggers:"), endTriggers));
		rightSide.add(new JSeparator(SwingConstants.HORIZONTAL));

		JPanel bottom = new JPanel();
		bottom.add(createNode);
		bottom.add(removeNode);
		// removeLayer.addActionListener(this);
		rightSide.add(bottom);

		// encapsulate in a scroll pane
		JScrollPane pane = new JScrollPane(rightSide);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// now add the center
		panel.add(pane, BorderLayout.CENTER);

	}

	public Component aligned(Component first, Component second) {
		JPanel p = new JPanel(new BorderLayout(5, 5));
		p.add(first, BorderLayout.WEST);
		p.add(second, BorderLayout.CENTER);
		p.setBorder(new EmptyBorder(3, 3, 3, 3));
		return p;
	}

	public Component getComponent(int width, int height) {
		// set the size
		panel.setPreferredSize(new Dimension(width, height));

		return panel;
	}

	/**
	 * Logic on button pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// open a new quest page
		if (e.getSource() == open) {

			// create the file chooser
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setCurrentDirectory(new File(DIR));

			// open the chooser
			if (fc.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {

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

				// load if the file exists
				if (file.exists()) {

					// load the JSON
					JSONObject data = null;

					try {
						data = JSONData.load(new FileInputStream(file));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					// make sure it parsed
					if (data != null) {
						
						initial.setSelected((boolean) data.get(QuestDataBuilder.INITIAL_QUEST)); 
						npcGiver.setText((String) data.get(QuestDataBuilder.NPC_ID));
						objectiveSummary.setText((String) data.get(QuestDataBuilder.KEY_OBJECTIVE));
						
						nodeData = new HashMap<>();
						iface.onLoaded(data);
					}
				}
			}

			// save the quest tree
		} else if (e.getSource() == save) {

			builder.setNPCFirstQuest(initial.isSelected());
			builder.setObjectiveText(objectiveSummary.getText());
			builder.setQuestGiver(Integer.valueOf(npcGiver.getText()));

			// now write it to the file
			JSONData.save(filePath, builder.build());

			// add a new layer to the quest tree
		} else if (e.getSource() == addNode) {
			// addLayer();

			// remove the recent layer
		} else if (e.getSource() == removeNode) {
			// removeLayer();
		} else if (e.getSource() == createNode) {
			iface.onNodeCreated(currentState.getText(), previousState.getText());
		}

	}

	/*
	 * Added functionality to JButton
	 */
	private class QuestNodeAdapter {
		
		private Node n;

		// id of this button
		private int currState, prevState;

		// text fields of the state
		private String giverText, res1Text, res2Text, res3Text, trig1, trig2, trig3, endText, endTrig;
		
		public QuestNodeAdapter(Node n) {
			this.n = n;
		}

		private void update() {

			// button is empty
			if ((giverText + res1Text + res2Text + res3Text + trig1 + trig2 + trig3 + endText + endTrig)
					.trim().isEmpty()) {
				n.setAttribute("ui.class", "incomplete");

				// button is not empty
			} else {

				boolean complete = false;

				try {
					complete = !(giverText.isEmpty() || res1Text.isEmpty() || res2Text.isEmpty() || res3Text.isEmpty()
							|| trig1.isEmpty() || trig2.isEmpty() || trig3.isEmpty());
				} catch (NullPointerException e) {
				}

				if (complete) {
					n.setAttribute("ui.class", "complete");
				} else {
					n.setAttribute("ui.class", "incomplete");
				}

			}

		}

		/**
		 * Saves the information from the information panel into this button's fields
		 */
		private void save() {

			giverText = giverDialogue.getText();
			res1Text = response1.getText();
			res2Text = response2.getText();
			res3Text = response3.getText();
			endText = endDialogue.getText();
			trig1 = response1Triggers.getText();
			trig2 = response2Triggers.getText();
			trig3 = response3Triggers.getText();
			endTrig = endTriggers.getText();
			currState = Integer.valueOf(currentState.getText());
			prevState = Integer.valueOf(previousState.getText());

			update();

		}

		/**
		 * @param obj - the json object that contains the data
		 */
		private void loadJSON(JSONObject obj) {
			
			giverText = (String) obj.get(QuestDataBuilder.KEY_GIVER);
			res1Text = (String) obj.get(QuestDataBuilder.KEY_RESPONSE1);
			res2Text = (String) obj.get(QuestDataBuilder.KEY_RESPONSE2);
			res3Text = (String) obj.get(QuestDataBuilder.KEY_RESPONSE3);
			trig1 = (String) obj.get(QuestDataBuilder.KEY_TRIGGERS1);
			trig2 = (String) obj.get(QuestDataBuilder.KEY_TRIGGERS2);
			trig3 = (String) obj.get(QuestDataBuilder.KEY_TRIGGERS3);
			endText = (String) obj.get(QuestDataBuilder.KEY_END);
			endTrig = (String) obj.get(QuestDataBuilder.KEY_END_TRIGGER);
			currState = Integer.valueOf((String) obj.get(QuestDataBuilder.STATE_ID));
			prevState = Integer.valueOf((String) obj.get(QuestDataBuilder.PREV_STATE_ID));

			update();
		}

		/**
		 * Loads the information from the button's data fields into the the information
		 * panel
		 */
		private void displayDataToPanel() {
			giverDialogue.setText(giverText);
			response1.setText(res1Text);
			response2.setText(res2Text);
			response3.setText(res3Text);
			response1Triggers.setText(trig1);
			response2Triggers.setText(trig2);
			response3Triggers.setText(trig3);
			endDialogue.setText(endText);
			endTriggers.setText(endTrig);
			currentState.setText(currentState + "");
			previousState.setText(prevState + "");
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

			this.setPreferredSize(new Dimension(300, 50));
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItemSelectable() == initial) {
			boolean selected = e.getStateChange() == ItemEvent.SELECTED;
		}

	}
	
	public void nodeLoaded(Node n, JSONObject obj) {
		
		QuestNodeAdapter a = new QuestNodeAdapter(n);
		nodeData.put(n.getId(), a);
		
		a.loadJSON(obj);
	}
	
	public void nodeLoaded(Node n) {
		QuestNodeAdapter a = new QuestNodeAdapter(n);
		nodeData.put(n.getId(), a);
		
		a.save();
	}

}
