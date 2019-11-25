package editors.dialogue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.graphstream.graph.Node;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import editors.quest.IDataLoaded;
import javajesus.dataIO.JSONData;

public class DialogueUIComponents implements ActionListener {

	// buttons used
	private final JButton open, save, createNode, deleteNode, saveNode, updateNode, newScene;

	// elements on the right side of the panel
	private final JLabel fileLabel;
	private final JJTextArea actorDialogue, response1, response2, response3, response1Triggers,
			response2Triggers, response3Triggers, endDialogue, endTriggers, npcActor, currentNode, previousNode,
			nextNode, questID;

	// gets the top level directory
	private static final String DIR = "res/WORLD_DATA/QUEST_DATA/";

	// name of the dialogue scene
	private String name = "Create new Dialogue Scene or Modify Old Dialogue Scene";

	private static int id = 1;

	// extension
	private static final String JSON = ".json";
	private static final String ACTOR_PATH = "ACTOR_DIALOGUE/";
	private static final String QUEST_PATH = "QUEST_DIALOGUE/";

	// filepath
	private String filePath;

	private JPanel panel;

	private DialogueDataBuilder builder;

	private IDataLoaded iface;

	private HashMap<String, QuestNodeAdapter> nodeData;

	private CardLayout cardLayout;
	private JPanel overlay;

	private QuestNodeAdapter selectedNode;

	private final static String[] VALID_TRIGGERS = { "GOTO_\\d+", "ACCEPT", "EXIT", "SKIP", "START_\\d+",
			"TRIGGER_\\d+" };

	/**
	 * @param width  - width of the panel
	 * @param height - height of the panel
	 */
	public DialogueUIComponents() {

		builder = new DialogueDataBuilder();
		nodeData = new HashMap<>();

		newScene = new JButton("New Scene");
		newScene.addActionListener(this);
		open = new JButton("Open Scene");
		open.addActionListener(this);
		save = new JButton("Save Scene");
		save.addActionListener(this);
		createNode = new JButton("Create Node");
		createNode.addActionListener(this);
		deleteNode = new JButton("Delete Node");
		deleteNode.addActionListener(this);
		saveNode = new JButton("Save Node");
		saveNode.addActionListener(this);
		fileLabel = new JLabel(name);
		updateNode = new JButton("Update Node");
		updateNode.addActionListener(this);

		currentNode = new JJTextArea("");
		currentNode.setPreferredSize(new Dimension(15, 15));
		previousNode = new JJTextArea("");
		previousNode.setPreferredSize(new Dimension(75, 15));
		nextNode = new JJTextArea("");
		nextNode.setPreferredSize(new Dimension(50, 15));

		actorDialogue = new JJTextArea("");
		response1 = new JJTextArea("");
		response1Triggers = new JJTextArea("");
		response2 = new JJTextArea("");
		response2Triggers = new JJTextArea("");
		response3 = new JJTextArea("");
		response3Triggers = new JJTextArea("");
		endDialogue = new JJTextArea("");
		endTriggers = new JJTextArea("");
		npcActor = new JJTextArea("");
		npcActor.setPreferredSize(new Dimension(60, 15));
		questID = new JJTextArea("");
		questID.setPreferredSize(new Dimension(60, 15));

		cardLayout = new CardLayout(0, 0);
		overlay = new JPanel(cardLayout);

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
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		JPanel fileTitle = new JPanel();
		JPanel fileControls = new JPanel();
		fileTitle.add(fileLabel);
		fileControls.add(newScene);
		fileControls.add(open);
		fileControls.add(save);
		top.add(fileTitle);
		top.add(fileControls);
		panel.add(top, BorderLayout.NORTH);

		// now add the information panels on the right side
		JPanel creationTools = new JPanel();
		creationTools.setLayout(new BoxLayout(creationTools, BoxLayout.Y_AXIS));
		
		JPanel inputFields = new JPanel();
		inputFields.setLayout(new BoxLayout(inputFields, BoxLayout.Y_AXIS));
		
		JPanel nodeControls = new JPanel();
		nodeControls.add(createNode);
		nodeControls.add(saveNode);
		nodeControls.add(updateNode);
		nodeControls.add(deleteNode);
		creationTools.add(nodeControls);
		
		
		JPanel idData = new JPanel();
		idData.add(new JLabel("Actor ID:"));
		idData.add(npcActor);
		idData.add(new JLabel("Quest ID:"));
		idData.add(questID);
		inputFields.add(idData);
		inputFields.add(new JSeparator(SwingConstants.HORIZONTAL));

		JPanel nodeIDData = new JPanel();
		nodeIDData.add(aligned(new JLabel("Node ID:"), currentNode));
		nodeIDData.add(aligned(new JLabel("Incoming Node ID:"), previousNode));
		nodeIDData.add(aligned(new JLabel("Outgoing Node ID:"), nextNode));
		inputFields.add(nodeIDData);
		inputFields.add(aligned(new JLabel("Actor Line:"), actorDialogue));
		inputFields.add(aligned(new JLabel("Player Response 1:"), response1));
		inputFields.add(aligned(new JLabel("Response 1 Triggers:"), response1Triggers));
		inputFields.add(aligned(new JLabel("Player Response 2:"), response2));
		inputFields.add(aligned(new JLabel("Response 2 Triggers:"), response2Triggers));
		inputFields.add(aligned(new JLabel("Player Response 3:"), response3));
		inputFields.add(aligned(new JLabel("Response 3 Triggers:"), response3Triggers));
		inputFields.add(new JSeparator(SwingConstants.HORIZONTAL));
		inputFields.add(aligned(new JLabel("End Dialogue:"), endDialogue));
		inputFields.add(aligned(new JLabel("End Triggers:"), endTriggers));
		inputFields.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		// encapsulate in a scroll pane
		JScrollPane pane = new JScrollPane(overlay);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		creationTools.add(pane);

		JPanel plain = new JPanel();
		overlay.add(plain, "plain");
		overlay.add(inputFields, "main");

		// now add the center
		panel.add(creationTools, BorderLayout.CENTER);

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

		// open new quest page
		if (e.getSource() == newScene) {

			String name = (String) JOptionPane.showInputDialog(panel, "Please enter a file name:", "New Dialogue Scene Creation",
					JOptionPane.PLAIN_MESSAGE, null, null, "New Scene");
			
			if (name != null) {
				// update the file label]
				this.name = name;
				fileLabel.setText(name);
				filePath = new File(DIR + name + JSON).getPath();
				
				this.clearFields();
				npcActor.setText("0");
				questID.setText("0");

				id = 0;

				builder = new DialogueDataBuilder();
				nodeData = new HashMap<>();
				
				cardLayout.show(overlay, "plain");
				
				iface.onLoaded(null);
			}

		}
		// load a new quest page
		else if (e.getSource() == open)

		{

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

						npcActor.setText(String.valueOf(data.get(DialogueDataBuilder.ACTOR_ID)));
						questID.setText(String.valueOf(data.get(DialogueDataBuilder.QUEST_ID)));

						builder.setDialogueArray((JSONArray) data.get(DialogueDataBuilder.DIALOGUE_SCENE));
						id = 0;
						
						nodeData = new HashMap<>();
						iface.onLoaded(data);
						cardLayout.show(overlay, "main");
					}
				}
			}

			// save the quest tree
		} else if (e.getSource() == save) {

			builder.setActor(Integer.valueOf(npcActor.getText()));
			builder.setQuestID(Integer.valueOf(questID.getText()));
			
			// If the quest is null, it's actor dialogue, otherwise its quest dialogue
			if(Integer.valueOf(questID.getText()) == -1) 
				filePath = new File(DIR + ACTOR_PATH + name + JSON).getPath();
			else
				filePath = new File(DIR + QUEST_PATH + name + npcActor.getText() + JSON).getPath();
			
			// now write it to the file
			JSONData.save(filePath, builder.build());

			// add a new layer to the quest tree
		} else if (e.getSource() == createNode) {
			currentNode.setText(id + "");
			previousNode.setText("");
			nextNode.setText("");
			actorDialogue.setText("");
			response1.setText("");
			response2.setText("");
			response3.setText("");
			response1Triggers.setText("");
			response2Triggers.setText("");
			response3Triggers.setText("");
			endDialogue.setText("");
			endTriggers.setText("");

			updateNode.setEnabled(false);
			saveNode.setEnabled(true);
			deleteNode.setEnabled(false);
			currentNode.setEnabled(true);

			cardLayout.show(overlay, "main");

			// remove the recent layer
		} else if (e.getSource() == deleteNode) {
			iface.onNodeRemoved(currentNode.getText());
			nodeData.remove(currentNode.getText());
			builder.removeNodeFromDialogue(currentNode.getText());
			cardLayout.show(overlay, "plain");
		} else if (e.getSource() == saveNode) {
			this.outgoingErrorCheck();
			iface.onNodeCreated(currentNode.getText(), previousNode.getText(), nextNode.getText());
			cardLayout.show(overlay, "plain");
		} else if (e.getSource() == updateNode) {
			QuestNodeAdapter node = nodeData.get(currentNode.getText());
			this.outgoingErrorCheck();
			node.fieldTextToNode();
			node.colorByError();
			builder.updateNodeInDialogue(actorDialogue.getText(), response1.getText(), response2.getText(),
					response3.getText(), response1Triggers.getText(), response2Triggers.getText(),
					response3Triggers.getText(), endDialogue.getText(), endTriggers.getText(), currentNode.getText(),
					previousNode.getText(), nextNode.getText());
			String cur = currentNode.getText(), prev = previousNode.getText(), fut = nextNode.getText();
			this.clearFields();
			iface.onNodeModified(cur, prev, fut);
			cardLayout.show(overlay, "plain");
		}

	}

	private void clearFields() {

		actorDialogue.setText("");
		response1.setText("");
		response2.setText("");
		response3.setText("");
		response1Triggers.setText("");
		response2Triggers.setText("");
		response3Triggers.setText("");
		endDialogue.setText("");
		endTriggers.setText("");
		currentNode.setText("");
		previousNode.setText("");
		nextNode.setText("");
	}

	private void outgoingErrorCheck() {

		// auto update outgoing ID
		ArrayList<String> outgoing = new ArrayList<String>();

		String trig1 = response1Triggers.getText();
		if (trig1.toUpperCase().contains("GOTO_")) {
			String next = trig1.substring(trig1.indexOf("GOTO_") + 5);
			outgoing.add(next);
		}

		String trig2 = response2Triggers.getText();
		if (trig2.toUpperCase().contains("GOTO_")) {
			String next = trig2.substring(trig2.indexOf("GOTO_") + 5);
			outgoing.add(next);
		}

		String trig3 = response3Triggers.getText();
		if (trig3.toUpperCase().contains("GOTO_")) {
			String next = trig3.substring(trig3.indexOf("GOTO_") + 5);
			outgoing.add(next);
		}

		String nexNode = nextNode.getText();
		if (outgoing.size() > 0) {
			boolean complete = true;
			for (String next : outgoing) {
				if (!nexNode.contains(next)) {
					complete = false;
					break;
				}
			}
			if (!complete) {
				nexNode = outgoing.get(0);
				for (int i = 1; i < outgoing.size(); i++) {
					nexNode += "," + outgoing.get(i);
				}
				nextNode.setText(nexNode);
			}
		}
	}

	/*
	 * Added functionality to JButton
	 */
	private class QuestNodeAdapter {

		private Node n;

		// text fields of the state
		private String giverText = "", res1Text = "", res2Text = "", res3Text = "", trig1 = "", trig2 = "", trig3 = "",
				endText = "", endTrig = "", currNode = "", prevNode = "", objSummary = "", nexNode = "";

		public QuestNodeAdapter(Node n) {
			this.n = n;
			this.currNode = n.getId();
		}

		private void setSelected() {
			n.setAttribute("ui.class", "selected");
		}

		public boolean triggerErrorCheck(String triggerText) {

			// clean up the raw data
			String raw = triggerText.trim().toUpperCase().replace(" ", "");

			// list of trigger commands
			ArrayList<String> triggers = new ArrayList<String>();

			// now parse the triggers
			while (raw.indexOf(",") != -1) {
				triggers.add(raw.substring(0, raw.indexOf(",")));
				raw = raw.substring(raw.indexOf(",") + 1);
			}

			// get the last trigger
			triggers.add(raw);

			boolean all_match = true;
			for (String keyword : triggers) {
				boolean match = false;
				for (String pattern : VALID_TRIGGERS) {
					if (keyword.matches(pattern)) {
						match = true;
						break;
					}
				}
				if (!match) {
					all_match = false;
					break;
				}
			}
			return all_match;
		}

		private void colorByError() {

			// completeness check

			boolean complete = true;

			if (giverText.isEmpty() || res1Text.isEmpty() || res2Text.isEmpty() || res3Text.isEmpty() || trig1.isEmpty()
					|| trig2.isEmpty() || trig3.isEmpty()) {
				complete = false;
			}

			if ((trig1 + trig2 + trig3).toUpperCase().contains("ACCEPT")) {
				if (endText.isEmpty() || endTrig.isEmpty() || objSummary.isEmpty()) {
					complete = false;
				}
			}

			if (!triggerErrorCheck(trig1) || !triggerErrorCheck(trig2) || !triggerErrorCheck(trig3)) {
				complete = false;
			}

			if (complete) {
				n.setAttribute("ui.class", "complete");
			} else {
				n.setAttribute("ui.class", "incomplete");
			}

		}

		/**
		 * Saves the information from the information panel into this button's fields
		 */
		private void fieldTextToNode() {

			giverText = actorDialogue.getText();
			res1Text = response1.getText();
			res2Text = response2.getText();
			res3Text = response3.getText();
			endText = endDialogue.getText();
			trig1 = response1Triggers.getText();
			trig2 = response2Triggers.getText();
			trig3 = response3Triggers.getText();
			endTrig = endTriggers.getText();
			currNode = currentNode.getText();
			prevNode = previousNode.getText();
			nexNode = nextNode.getText();

		}

		/**
		 * @param obj - the json object that contains the data
		 */
		private void loadJSON(JSONObject obj) {

			giverText = (String) obj.get(DialogueDataBuilder.KEY_ACTOR);
			res1Text = (String) obj.get(DialogueDataBuilder.KEY_RESPONSE1);
			res2Text = (String) obj.get(DialogueDataBuilder.KEY_RESPONSE2);
			res3Text = (String) obj.get(DialogueDataBuilder.KEY_RESPONSE3);
			trig1 = (String) obj.get(DialogueDataBuilder.KEY_TRIGGERS1);
			trig2 = (String) obj.get(DialogueDataBuilder.KEY_TRIGGERS2);
			trig3 = (String) obj.get(DialogueDataBuilder.KEY_TRIGGERS3);
			endText = (String) obj.get(DialogueDataBuilder.KEY_END);
			endTrig = (String) obj.get(DialogueDataBuilder.KEY_END_TRIGGER);
			currNode = (String) obj.get(DialogueDataBuilder.NODE_ID);
			prevNode = (String) obj.get(DialogueDataBuilder.PREV_NODE_ID);
			nexNode = (String) obj.get(DialogueDataBuilder.NEXT_NODE_ID);

			if (giverText == null) {
				giverText = "";
			}
			if (res1Text == null) {
				res1Text = "";
			}
			if (res2Text == null) {
				res2Text = "";
			}
			if (res3Text == null) {
				res3Text = "";
			}
			if (trig1 == null) {
				trig1 = "";
			}
			if (trig2 == null) {
				trig2 = "";
			}
			if (trig3 == null) {
				trig3 = "";
			}
			if (endText == null) {
				endText = "";
			}
			if (endTrig == null) {
				endTrig = "";
			}
			if (currNode == null) {
				currNode = "";
			}
			if (prevNode == null) {
				prevNode = "";
			}
			if (objSummary == null) {
				objSummary = "";
			}
			if (nexNode == null) {
				nexNode = "";
			}

			colorByError();
		}

		/**
		 * Loads the information from the button's data fields into the the information
		 * panel
		 */
		private void displayDataToPanel() {

			actorDialogue.setText(giverText);
			response1.setText(res1Text);
			response2.setText(res2Text);
			response3.setText(res3Text);
			response1Triggers.setText(trig1);
			response2Triggers.setText(trig2);
			response3Triggers.setText(trig3);
			endDialogue.setText(endText);
			endTriggers.setText(endTrig);
			currentNode.setText(currNode);
			previousNode.setText(prevNode);
			nextNode.setText(nexNode);
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

	public void nodeLoaded(Node n, JSONObject obj) {

		QuestNodeAdapter a = new QuestNodeAdapter(n);
		nodeData.put(n.getId(), a);

		a.loadJSON(obj);
		id++;
	}

	public void nodeLoaded(Node n, String incoming, String outgoing) {
		QuestNodeAdapter a = new QuestNodeAdapter(n);
		nodeData.put(n.getId(), a);

		currentNode.setText(n.getId());
		previousNode.setText(incoming);
		nextNode.setText(outgoing);

		a.fieldTextToNode();
		a.colorByError();
		builder.addNodeToDialogue(actorDialogue.getText(), response1.getText(), response2.getText(), response3.getText(),
				response1Triggers.getText(), response2Triggers.getText(), response3Triggers.getText(),
				endDialogue.getText(), endTriggers.getText(), currentNode.getText(), previousNode.getText(), nextNode.getText());
		clearFields();
		
		System.out.println(n.getId());
		id++;
	}

	public void addIncomingEdgeToNode(String id, String incoming) {

		QuestNodeAdapter a = nodeData.get(id);
		a.displayDataToPanel();

		String updated = "";

		if (previousNode.getText().isEmpty() || previousNode.getText().equals(incoming)) {
			updated = incoming;
		} else {

			ArrayList<String> edges = new ArrayList<>();
			edges.add(incoming);
			for (String inc : previousNode.getText().split(",")) {
				if (!edges.contains(inc)) {
					edges.add(inc);
				}
			}
			updated = edges.get(0);
			for (int i = 1; i < edges.size(); i++) {
				updated += "," + edges.get(i);
			}
		}

		previousNode.setText(updated);
		a.fieldTextToNode();
		a.colorByError();
		builder.updateNodeInDialogue(actorDialogue.getText(), response1.getText(), response2.getText(), response3.getText(),
				response1Triggers.getText(), response2Triggers.getText(), response3Triggers.getText(),
				endDialogue.getText(), endTriggers.getText(), currentNode.getText(), previousNode.getText(), nextNode.getText());
		this.clearFields();
	}

	public void select(String id) {
		QuestNodeAdapter node = selectedNode = nodeData.get(id);
		node.displayDataToPanel();
		node.setSelected();
		cardLayout.show(overlay, "main");

		updateNode.setEnabled(true);
		saveNode.setEnabled(false);
		deleteNode.setEnabled(true);
		currentNode.setEnabled(false);
	}

	public void deselect() {
		if (selectedNode != null)
			selectedNode.colorByError();
	}

	public void rootLoaded(Node n) {
		QuestNodeAdapter a = new QuestNodeAdapter(n);
		nodeData.put(n.getId(), a);
		a.displayDataToPanel();
		builder.addNodeToDialogue(actorDialogue.getText(), response1.getText(), response2.getText(), response3.getText(),
				response1Triggers.getText(), response2Triggers.getText(), response3Triggers.getText(),
				endDialogue.getText(), endTriggers.getText(), currentNode.getText(), previousNode.getText(), nextNode.getText());
	}

}
