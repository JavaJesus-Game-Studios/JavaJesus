package javajesus.quest.original;

import javajesus.entities.Mob;
import javajesus.entities.monsters.EvilFox;
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
	public TheEvilFox() {
		super("Evil Fox", "/WORLD_DATA/QUEST_DATA/TheEvilFox.json", 1);
	}
	
	@Override
	public void update() {
		
		// if fox is not found, assume it is killed
		boolean killed = true;
		
		// search for the fox
		for (Mob m: giver.getLevel().getMobs()) {
			if (m instanceof EvilFox && !m.isDead()) {
				killed = false;
			}
		}
		
		// update the objective
		objectives[KILL_FOX] = killed;
		
	}

}
