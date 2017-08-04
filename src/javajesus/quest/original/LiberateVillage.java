package javajesus.quest.original;

import java.awt.Rectangle;

import javajesus.entities.Mob;
import javajesus.entities.monsters.Demon;
import javajesus.entities.npcs.NPC;
import javajesus.quest.Quest;

public class LiberateVillage extends Quest {
	
	// constants for the objectives
	private static final int VILLAGE_CLEAR = 0;
	
	// bounds of the village
	private static final Rectangle villageBounds = new Rectangle(1184, 416, 400, 328);

	/**
	 * @param giver - the NPC who is giving the quest
	 */
	public LiberateVillage(NPC giver) {
		super(giver, "The Resistance", 1);
	}
	
	@Override
	protected void update() {
		
		// if a demon is not found, assume true
		boolean killed = true;
		
		// search for any demons
		for (Mob m: giver.getLevel().getMobs()) {
			if (m instanceof Demon && m.getBounds().intersects(villageBounds) && !m.isDead()) {
				killed = false;
				break;
			}
		}
		
		// update the objective
		objectives[VILLAGE_CLEAR] = killed;
		
	}

	protected String preDialogue() {
		return "The Demons are destroying our village!";
	}

	protected String dialogue() {
		return "I need you to clear the village so we can protect the citizens of the bay";
	}

	protected String postDialogue() {
		return "This a a good first step in fighting back against the Demon invasion. You have my thanks.";
	}
	
	/**
	 * Add a list of objectives to the summary
	 */
	@Override
	public String getSummary() {
		return super.getSummary() + "\nObjective: Clear the village south of the police barricade.";
	}

}
