package javajesus.quest;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import editors.quest.QuestDataBuilder;
import javajesus.DialogueHandler;
import javajesus.dataIO.JSONData;
import javajesus.entities.Player;
import javajesus.entities.npcs.NPC;
import javajesus.items.Item;
import javajesus.quest.events.Event;
import javajesus.quest.factories.AlphaQuestFactory;

/*
 * A Quest is given by a NPC that requires a set of specific dialogue and action events completed
 */
public abstract class Quest {

	// the NPC who gave the quest
	protected NPC giver;

	// npc id
	protected int giverId;

	// The list of criteria with a boolean specified if the condition is met
	protected boolean[] objectives;

	// whether or not the player has accepted the quest
	private boolean accepted;

	// title of the quest
	protected String title;

	// different states of the quest
	protected String state;

	// whether or not dialogue has been completed
	private boolean finished;

	// the different states containing information of the quest
	protected HashMap<String, JSONObject> questParts;

	// instance of the player taking the quest
	private Player player;

	private boolean initialQuest;
	private String objectiveSummary;

	/**
	 * Quest ctor() Creates a quest object that loads information from a .json file
	 * 
	 * @param giver         - the NPC giving the quest
	 * @param path          - the path to the .json file to load
	 * @param numObjectives - the number of objectives to complete
	 */
	public Quest(String name, String path, int numObjectives) {

		// instance data
		this.state = "0";
		this.objectives = new boolean[numObjectives];

		// warn if objectives is zero
		if (numObjectives == 0) {
			System.err.println("WARNING QUEST " + path + " HAS 0 OBJECTIVES");
		}

		// the name of the file is the title
		this.title = name;

		// load the data from the JSON file
		JSONObject data = JSONData.load(Quest.class.getResourceAsStream(path));

		// intiial, objective, giver
		this.initialQuest = (boolean) data.get(QuestDataBuilder.INITIAL_QUEST);
		this.objectiveSummary = (String) data.get(QuestDataBuilder.KEY_OBJECTIVE);
		this.giverId = (int) data.get(QuestDataBuilder.NPC_ID);

		this.questParts = new HashMap<>();
		JSONArray arr = (JSONArray) data.get(QuestDataBuilder.QUEST_PARTS);
		for (int i = 0; i < arr.size(); i++) {
			JSONObject obj = (JSONObject) arr.get(i);
			this.questParts.put((String) obj.get(QuestDataBuilder.STATE_ID), obj);
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
		if (isCompleted()) {

			// do post quest logic
			selectOption(QuestDataBuilder.KEY_END_TRIGGER);

			return (String) questParts.get(state).get(QuestDataBuilder.KEY_END);

			// dialogue quest text
		} else {

			// get the dialogue based on the state
			JSONObject current = questParts.get(state);

			// return the giver text
			return (String) current.get(QuestDataBuilder.KEY_GIVER);
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
			key = QuestDataBuilder.KEY_RESPONSE1;
		} else if (num == 2) {
			key = QuestDataBuilder.KEY_RESPONSE2;
		} else {
			key = QuestDataBuilder.KEY_RESPONSE3;
		}

		// now return that option data
		return (String) questParts.get(state).get(key);

	}

	/**
	 * @param key - Use Keys found in QuestData
	 */
	public void selectOption(String key) {

		// now get the trigger data
		String raw = (String) questParts.get(state).get(key);

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

			// create and add the quest based on the ID
			Quest q = AlphaQuestFactory.makeQuest(id);
			q.giver.addQuest(q);

			// exits from dialogue
		} else if (trigger.contains("EXIT")) {
			finished = true;

			// reset the quest if it was not accepted
			if (!accepted) {
				state = "0";
				finished = false;
			}

			DialogueHandler.endDialogue();
		} else if (trigger.contains("ACCEPT")) {
			accepted = true;
			player.addQuest(this);
		} else if (trigger.contains("TRIGGER")) {

			// get the ID of the event
			int id = Integer.valueOf(trigger.substring(trigger.indexOf("_") + 1));

			// trigger the event
			Event.createEvent(id, giver.getLevel());

		} else if (trigger.contains("GOTO")) {

			// get the state
			int startIndex = trigger.indexOf("_") + 1;

			// parse the number
			state = trigger.substring(startIndex);

			// skips the current quest
		} else if (trigger.contains("SKIP")) {
			giver.nextQuest();

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
	 * @return whether or not dialogue has been finished
	 */
	public boolean isDialogueFinished() {
		return finished;
	}

	/**
	 * @param player taking the quest
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return whether or not the quest can be completed
	 */
	public boolean isCompletable() {
		return !giver.isDead();
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
	 * @return The summary of the objectives that appears in the overview screen
	 */
	public final String getSummary() {

		// get the summary based on the state
		String sum = (String) questParts.get(state).get(QuestDataBuilder.KEY_OBJECTIVE);

		return "Title: " + title + "\nGiver: " + giver.getName() + "\n" + sum;
	}

	/**
	 * @return whether or not the quest is completed
	 */
	public boolean isCompleted() {

		// return false if any are not completed
		for (int i = 0; i < objectives.length; i++) {
			if (!objectives[i]) {
				return false;
			}
		}

		// all objectives are true
		return true;
	}

}
