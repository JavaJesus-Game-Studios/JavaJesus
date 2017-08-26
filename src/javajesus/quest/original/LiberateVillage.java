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
		super(giver, "Liberate Village", "/WORLD_DATA/QUEST_DATA/Liberate_Village.json", 1);
	}
	
	@Override
	public void update() {
		
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

}
