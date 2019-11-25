package javajesus.quest;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import editors.dialogue.DialogueDataBuilder;
import javajesus.DialogueHandler;
import javajesus.dataIO.JSONData;
import javajesus.entities.Player;
import javajesus.items.Item;

public abstract class Dialogue {
	// Representative of the nodes of the Dialogue Graph
	protected String currNode;

	// Whether or not dialogue has been completed
	private boolean convoCompleted;
	
	// whether or not the player has accepted the dialogue
	private boolean accepted;

	// the different states containing information of the quest
	protected HashMap<String, JSONObject> dialogueNodes;

	// instance of the player engaging in the dialogue
	private Player player;
	
	
	public Dialogue(String path) {
		// TODO Auto-generated constructor stub
		
		// load the data from the JSON file
		JSONObject data = JSONData.load(Dialogue.class.getResourceAsStream(path));
		
		this.dialogueNodes = new HashMap<>();
		JSONArray arr = (JSONArray) data.get(DialogueDataBuilder.DIALOGUE_SCENE);
		for (int i = 0; i < arr.size(); i++) {
			JSONObject obj = (JSONObject) arr.get(i);
			this.dialogueNodes.put((String) obj.get(DialogueDataBuilder.NODE_ID), obj);
		}


	}
	
	/**
	 * @return - the dialogue for the quest
	 */
	public final String getDialogue() {

		// update only if the player accepts the quest first
		if (accepted) {
			update();
		}

		// post dialogue text
		if (convoCompleted) {

			// do post quest logic
			selectOption(DialogueDataBuilder.KEY_END_TRIGGER);

			return (String) dialogueNodes.get(currNode).get(DialogueDataBuilder.KEY_END);

			// dialogue quest text
		} else {

			// get the dialogue based on the state
			JSONObject current = dialogueNodes.get(currNode);

			// return the actor text
			return (String) current.get(DialogueDataBuilder.KEY_ACTOR);
		}

	}

	
	/**
	 * @param num - which option to get (1, 2, 3)
	 * @return the text of that option
	 */
	public String getOption(int num) {

		// which key to look up
		String key;

		// get the correct key based on the num
		if (num == 1) {
			key = DialogueDataBuilder.KEY_RESPONSE1;
		} else if (num == 2) {
			key = DialogueDataBuilder.KEY_RESPONSE2;
		} else {
			key = DialogueDataBuilder.KEY_RESPONSE3;
		}

		// now return that option data
		return (String) dialogueNodes.get(currNode).get(key);

	}
	
	/**
	 * @param key - Use Keys found in QuestData
	 */
	public void selectOption(String key) {

		// now get the trigger data
		String raw = (String) dialogueNodes.get(currNode).get(key);

		// clean up the raw data
		raw = raw.trim().toUpperCase().replace(" ", "");

		// list of trigger commands
		ArrayList<String> triggers = new ArrayList<String>();

		// now parse the triggers
		while (raw.indexOf(",") != -1) {
			triggers.add(raw.substring(0, raw.indexOf(",")));
			raw = raw.substring(raw.indexOf(",") + 1);
		}

		// get the last trigger
		triggers.add(raw);

		// now do all the triggers
		for (String trig : triggers) {
			doAction(trig);
		}
	}
	
	/**
	 * @param trigger - key of the trigger action
	 */
	public void doAction(String trigger) {

		// starts a new quest
		if (trigger.contains("START")) {

			// get the ID of the quest
			int id = Integer.valueOf(trigger.substring(trigger.indexOf("_") + 1));

		} else if (trigger.contains("EXIT")) {
			convoCompleted = true;

			// reset the dialogue if it was not accepted
			if (!accepted) {
				currNode = "0";
				convoCompleted = false;
			}

			DialogueHandler.endDialogue();
		} else if (trigger.contains("ACCEPT")) {
			accepted = true;
		} else if (trigger.contains("TRIGGER")) {

			// get the ID of the event
			int id = Integer.valueOf(trigger.substring(trigger.indexOf("_") + 1));

			// trigger the event
			//TODO: Change how events are handled
			//EventFactory.createEvent(id, giver.getLevel());

		} else if (trigger.contains("GOTO")) {

			// get the state
			int startIndex = trigger.indexOf("_") + 1;

			// parse the number
			currNode = trigger.substring(startIndex);

			// skips the current quest
		} else if (trigger.contains("SKIP")) {

			// add an item to the player's inventory
		} else if (trigger.contains("ADD")) {

			// get the ID of the item to add
			int id = Integer.valueOf(trigger.substring(trigger.indexOf("_") + 1));

			// adds an item to the player's inventory
			player.addItem(Item.getItem(id));

			// unknown trigger
		} else {
			System.err.println("UNKNOWN TRIGGER: " + trigger);
		}

	}
	/**
	 * @return whether or not the player accepted the quest
	 */
	public boolean hasAccepted() {
		return accepted;
	}

	/**
	 * Updates the conditions for the quest
	 */
	public abstract void update();

	/**
	 * @return whether or not the quest is completed
	 */
}
