package javajesus.quest;

import java.io.File;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import javajesus.DialogueHandler;
import javajesus.dataIO.QuestData;
import javajesus.entities.Player;
import javajesus.entities.npcs.NPC;

/*
 * A Quest is given by a NPC that requires a set of specific dialogue and action events completed
 */
public abstract class Quest {

	// the NPC who gave the quest
	protected NPC giver;

	// The list of criteria with a boolean specified if the condition is met
	protected boolean[] objectives;

	// whether or not the player has accepted the quest
	private boolean accepted;
	
	// title of the quest
	protected String title;
	
	// different states of the quest
	protected int state;
	
	// whether or not dialogue has been completed
	private boolean finished;
	
	// the different states containing information of the quest
	protected final ArrayList<JSONObject> data;
	
	// instance of the player taking the quest
	private Player player;
	
	/**
	 * Quest ctor()
	 * Creates a quest object that loads information from a .json file
	 * 
	 * @param giver - the NPC giving the quest
	 * @param path - the path to the .json file to load
	 * @param numObjectives - the number of objectives to complete
	 */
	@SuppressWarnings("unchecked")
	public Quest(NPC giver, String path, int numObjectives) {
		
		// instance data
		this.giver = giver;
		this.state = 0;
		this.objectives = new boolean[numObjectives];
		
		// get the file from the path
		File file = new File(Quest.class.getResource(path).getFile());
		
		// the name of the file is the title
		this.title = file.getName().substring(0, file.getName().length() - 5);
		
		// load the data from the JSON file
		data = QuestData.load(file);
		
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
			
			return getEndDialogue();
			
			// dialogue quest text
		} else {

			// get the dialogue based on the state
			JSONObject current = data.get(state);
			
			// return the giver text
			return (String) current.get(QuestData.KEY_GIVER);
		}

	}
	
	/**
	 * @return dialogue at quest completion
	 */
	public abstract String getEndDialogue();
	
	/**
	 * @param num - which option to get (1, 2, 3)
	 * @return the text of that option
	 */
	public String getOption(int num) {
		
		// which key to look up
		String key;
		
		// get the correct key based on the num
		if (num == 1) {
			key = QuestData.KEY_RESPONSE1;
		} else if (num == 2) {
			key = QuestData.KEY_RESPONSE2;
		} else {
			key = QuestData.KEY_RESPONSE3;
		}
		
		// now return that option data
		return (String) data.get(state).get(key);
		
	}
	
	/**
	 * @param num - which option to select (1, 2, 3)
	 */
	public void selectOption(int num) {
		
		// which key to look up
		String key;
		
		// get the correct key based on the num
		if (num == 1) {
			key = QuestData.KEY_TRIGGERS1;
		} else if (num == 2) {
			key = QuestData.KEY_TRIGGERS2;
		} else {
			key = QuestData.KEY_TRIGGERS3;
		}
		
		// now get the trigger data
		String raw = (String) data.get(state).get(key);
		
		// clean up the raw data
		raw = raw.trim().toUpperCase().replace(" ", "");
		
		// list of trigger commands
		ArrayList<String> triggers = new ArrayList<String>();
		
		// now parse the triggers
		while(raw.indexOf(",") != -1) {
			triggers.add(raw.substring(0, raw.indexOf(",")));
			raw = raw.substring(raw.indexOf(",") + 1);
		}
		
		// get the last trigger
		triggers.add(raw);
		
		// now do all the triggers
		for (String trig: triggers) {
			doAction(trig);
		}
	}
	
	/**
	 * @param trigger - key of the trigger action
	 */
	public void doAction(String trigger) {
		
		// starts a new quest
		if (trigger.contains("START")) {
			
			// exits from dialogue
		} else if (trigger.contains("EXIT")) {
			finished = true;
			
			// reset the quest if it was not accepted
			if (!accepted) {
				state = 0;
				finished = false;
			}
			
			DialogueHandler.endDialogue();
		} else if (trigger.contains("ACCEPT")) {
			accepted = true;
			player.addQuest(this);
		} else if (trigger.contains("TRIGGER")) {
			
		} else if (trigger.contains("GOTO")){
			
			// get the state
			int startIndex = trigger.indexOf("_") + 1;
			
			// parse the number
			state = Integer.valueOf(trigger.substring(startIndex));
			
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
	protected abstract void update();
	
	/**
	 * @return The summary of the objectives that appears in the overview screen
	 */
	public final String getSummary() {
		
		// get the summary based on the state
		String sum = (String) data.get(state).get(QuestData.KEY_OBJECTIVE);
		
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