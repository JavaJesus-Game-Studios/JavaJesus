package editors.quest;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

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

public class DataView implements ActionListener {

	// buttons used
	private final JButton open, save, addNode, removeNode, createNode, modifyNode;

	// elements on the right side of the panel
	private final JLabel fileLabel;
	private final JJTextArea giverDialogue, objectiveSummary, response1, response2, response3, response1Triggers,
			response2Triggers, response3Triggers, endDialogue, endTriggers, npcGiver, currentState, previousState,
			futureState;
	private final JCheckBox initial;

	// gets the top level directory
	private static final String DIR = "res/WORLD_DATA/QUEST_DATA/";

	// name of the quest
	private String name = "New Quest";

	private static int id = 1;

	// extension
	private static final String JSON = ".json";

	// filepath
	private String filePath = new File(DIR + name + JSON).getPath();

	private JPanel panel;

	private QuestDataBuilder builder;

	private IDataLoaded iface;

	private HashMap<String, QuestNodeAdapter> nodeData;

	private CardLayout cardLayout;
	private JPanel overlay;

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
		removeNode.addActionListener(this);
		createNode = new JButton("Create This Part");
		createNode.addActionListener(this);
		fileLabel = new JLabel(name);
		modifyNode = new JButton("Modify Part");
		modifyNode.addActionListener(this);

		currentState = new JJTextArea("");
		currentState.setPreferredSize(new Dimension(15, 15));
		previousState = new JJTextArea("");
		previousState.setPreferredSize(new Dimension(75, 15));
		futureState = new JJTextArea("");
		futureState.setPreferredSize(new Dimension(50, 15));

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

		JPanel tempPanel = new JPanel();
		tempPanel.add(aligned(new JLabel("State ID:"), currentState));
		tempPanel.add(aligned(new JLabel("Incoming State ID:"), previousState));
		tempPanel.add(aligned(new JLabel("Outgoing State ID:"), futureState));
		rightSide.add(tempPanel);
		rightSide.add(aligned(new JLabel("State Objective Summary:"), objectiveSummary));
		rightSide.add(aligned(new JLabel("Giver Dialogue:"), giverDialogue));
		rightSide.add(aligned(new JLabel("Response 1:"), response1));
		rightSide.add(aligned(new JLabel("Response 1 Triggers:"), response1Triggers));
		rightSide.add(aligned(new JLabel("Response 2:"), response2));
		rightSide.add(aligned(new JLabel("Response 2 Triggers:"), response2Triggers));
		rightSide.add(aligned(new JLabel("Response 3:"), response3));
		rightSide.add(aligned(new JLabel("Response 3 Triggers:"), response3Triggers));
		rightSide.add(new JSeparator(SwingConstants.HORIZONTAL));
		rightSide.add(aligned(new JLabel("End Dialogue:"), endDialogue));
		rightSide.add(aligned(new JLabel("End Triggers:"), endTriggers));
		rightSide.add(new JSeparator(SwingConstants.HORIZONTAL));

		JPanel bottom = new JPanel();
		bottom.add(createNode);
		bottom.add(modifyNode);
		bottom.add(removeNode);
		// removeLayer.addActionListener(this);
		rightSide.add(bottom);

		// encapsulate in a scroll pane
		JScrollPane pane = new JScrollPane(overlay);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel plain = new JPanel();
		overlay.add(plain, "plain");
		overlay.add(rightSide, "main");

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
						npcGiver.setText(String.valueOf(data.get(QuestDataBuilder.NPC_ID)));

						builder.setQuestPartArray((JSONArray) data.get(QuestDataBuilder.QUEST_PARTS));
						id = 0;

						nodeData = new HashMap<>();
						iface.onLoaded(data);
					}
				}
			}

			// save the quest tree
		} else if (e.getSource() == save) {

			builder.setNPCFirstQuest(initial.isSelected());
			builder.setQuestGiver(Integer.valueOf(npcGiver.getText()));

			// now write it to the file
			JSONData.save(filePath, builder.build());

			// add a new layer to the quest tree
		} else if (e.getSource() == addNode) {
			currentState.setText(id + "");
			previousState.setText("");
			futureState.setText("");
			giverDialogue.setText("");
			response1.setText("");
			response2.setText("");
			response3.setText("");
			response1Triggers.setText("");
			response2Triggers.setText("");
			response3Triggers.setText("");
			endDialogue.setText("");
			endTriggers.setText("");

			modifyNode.setEnabled(false);
			createNode.setEnabled(true);
			removeNode.setEnabled(false);
			currentState.setEnabled(true);

			cardLayout.show(overlay, "main");

			// remove the recent layer
		} else if (e.getSource() == removeNode) {
			iface.onNodeRemoved(currentState.getText());
			nodeData.remove(currentState.getText());
			builder.removeQuestPart(currentState.getText());
			cardLayout.show(overlay, "plain");
		} else if (e.getSource() == createNode) {
			iface.onNodeCreated(currentState.getText(), previousState.getText(), futureState.getText());
			builder.addQuestPart(giverDialogue.getText(), response1.getText(), response2.getText(), response3.getText(),
					response1Triggers.getText(), response2Triggers.getText(), response3Triggers.getText(),
					endDialogue.getText(), endTriggers.getText(), currentState.getText(), previousState.getText(),
					objectiveSummary.getText(), futureState.getText());
			cardLayout.show(overlay, "plain");
		} else if (e.getSource() == modifyNode) {
			builder.modifyQuestPart(giverDialogue.getText(), response1.getText(), response2.getText(),
					response3.getText(), response1Triggers.getText(), response2Triggers.getText(),
					response3Triggers.getText(), endDialogue.getText(), endTriggers.getText(), currentState.getText(),
					previousState.getText(), objectiveSummary.getText(), futureState.getText());

			QuestNodeAdapter node = nodeData.get(currentState.getText());
			node.save();
			iface.onNodeModified(currentState.getText(), previousState.getText(), futureState.getText());
			cardLayout.show(overlay, "plain");
		}

	}

	/*
	 * Added functionality to JButton
	 */
	private class QuestNodeAdapter {

		private Node n;

		// text fields of the state
		private String giverText, res1Text, res2Text, res3Text, trig1, trig2, trig3, endText, endTrig, currState,
				prevState, objSummary, futState;

		public QuestNodeAdapter(Node n) {
			this.n = n;
			this.currState = n.getId();
		}

		private void update() {

			// button is empty
			if ((giverText + res1Text + res2Text + res3Text + trig1 + trig2 + trig3 + endText + endTrig + objSummary)
					.trim().isEmpty()) {
				n.setAttribute("ui.class", "incomplete");

				// button is not empty
			} else {

				boolean complete = false;

				try {
					complete = !(giverText.isEmpty() || res1Text.isEmpty() || res2Text.isEmpty() || res3Text.isEmpty()
							|| trig1.isEmpty() || trig2.isEmpty() || trig3.isEmpty() || objSummary.isEmpty());
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
			currState = currentState.getText();
			prevState = previousState.getText();
			objSummary = objectiveSummary.getText();
			futState = futureState.getText();

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
			currState = (String) obj.get(QuestDataBuilder.STATE_ID);
			prevState = (String) obj.get(QuestDataBuilder.PREV_STATE_ID);
			objSummary = (String) obj.get(QuestDataBuilder.KEY_OBJECTIVE);
			futState = (String) obj.get(QuestDataBuilder.FUT_STATE_ID);

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
			currentState.setText(currState);
			previousState.setText(prevState);
			objectiveSummary.setText(objSummary);
			futureState.setText(futState);
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

	public void nodeLoaded(Node n) {
		QuestNodeAdapter a = new QuestNodeAdapter(n);
		nodeData.put(n.getId(), a);
		currentState.setText(n.getId());
		a.save();
		id++;
	}

	public void select(String id) {
		QuestNodeAdapter node = nodeData.get(id);
		node.displayDataToPanel();
		cardLayout.show(overlay, "main");

		modifyNode.setEnabled(true);
		createNode.setEnabled(false);
		removeNode.setEnabled(true);
		currentState.setEnabled(false);
	}

	public void rootLoaded(Node n) {
		QuestNodeAdapter a = new QuestNodeAdapter(n);
		nodeData.put(n.getId(), a);
		a.displayDataToPanel();
		builder.addQuestPart(giverDialogue.getText(), response1.getText(), response2.getText(), response3.getText(),
				response1Triggers.getText(), response2Triggers.getText(), response3Triggers.getText(),
				endDialogue.getText(), endTriggers.getText(), currentState.getText(), previousState.getText(),
				objectiveSummary.getText(), futureState.getText());
	}

}
