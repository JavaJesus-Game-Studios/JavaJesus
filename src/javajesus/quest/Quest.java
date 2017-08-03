package javajesus.quest;

import javajesus.SoundHandler;
import javajesus.entities.npcs.NPC;

/*
 * A Quest is given by a NPC that requires a set of specific dialogue and action events completed
 */
public abstract class Quest {

	// the NPC who gave the quest
	protected NPC giver;

	// The list of criteria with a boolean specified if the condition is met
	protected boolean[] objectives;

	// whether or not it is finished
	protected boolean finished;
	
	// whether or not the player has accepted the quest
	private boolean accepted;
	
	// title of the quest
	protected String title;
	
	// different states of the quest
	protected int phase;
	
	// constants for determining dialogue
	private final int PRE = 0;
	
	/**
	 * @param giver - The NPC who gives the quest
	 * @param title - The name of the quest 
	 * @param parts - how many objectives are in this quest
	 * 
	 * There is one default objective that is set to false
	 */
	public Quest(NPC giver, String title, int objectives) {
		
		// instance data
		this.giver = giver;
		this.title = title;
		this.objectives = new boolean[objectives];
	}
	
	/**
	 * @return - the dialogue for the quest
	 */
	public final String getDialogue() {

		// quest just started
		if (phase == PRE) {

			// quest accepted sound effect
			SoundHandler.play(SoundHandler.levelup);

			// update the phase
			phase++;

			// return the first dialogue
			return preDialogue();

			// quest completed
		} else if (isCompleted()) {
			return postDialogue();

			// dialogue during the quest
		} else {
			return dialogue();
		}

	}
	
	/**
	 * @return whether or not the player accepted the quest
	 */
	public boolean hasAccepted() {
		return accepted;
	}
	
	/**
	 * Sets accepeted to true
	 */
	public void accept() {
		accepted = true;
	}
	
	/**
	 * Updates the conditions for the quest
	 */
	public abstract void update();

	/**
	 * @return The initial pre dialogue of the quest
	 */
	protected abstract String preDialogue();

	/**
	 * @return The dialoague during the quest
	 */
	protected abstract String dialogue();

	/**
	 * @return The post dialogue after the quest is completed
	 */
	protected abstract String postDialogue();
	
	/**
	 * @return The summary of the objectives that appears in the overview screen
	 */
	public String getSummary() {
		return "Title: " + title + "\nGiver: " + giver.getName();
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
