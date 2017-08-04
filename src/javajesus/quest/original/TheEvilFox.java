package javajesus.quest.original;

import javajesus.entities.Mob;
import javajesus.entities.animals.Fox;
import javajesus.entities.npcs.NPC;
import javajesus.quest.Quest;

/*
 * A Sample Quest
 */
public class TheEvilFox extends Quest {
	
	// constants for the objectives
	private static final int KILL_FOX = 0;

	/**
	 * @param giver - the NPC who is giving the quest
	 */
	public TheEvilFox(NPC giver) {
		super(giver, "The Evil Fox", 1);
	}
	
	@Override
	protected void update() {
		
		// if fox is not found, assume it is killed
		boolean killed = true;
		
		// search for the fox
		for (Mob m: giver.getLevel().getMobs()) {
			if (m instanceof Fox && !m.isDead()) {
				killed = false;
			}
		}
		
		// update the objective
		objectives[KILL_FOX] = killed;
		
	}

	protected String preDialogue() {
		return "I can see the fox is spreading blasphemy";
	}

	protected String dialogue() {
		return "I need you to silence the Fox from spreading such vile heresy";
	}

	protected String postDialogue() {
		return "I can feel God's divine salvation blessing you. Go in Peace.";
	}
	
	/**
	 * Add a list of objectives to the summary
	 */
	@Override
	public String getSummary() {
		return super.getSummary() + "\nObjective: Kill the Fox";
	}

}
