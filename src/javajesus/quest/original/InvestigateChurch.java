package javajesus.quest.original;

import javajesus.entities.Mob;
import javajesus.entities.monsters.Demon;
import javajesus.entities.monsters.Skeleton;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.buildings.AlphaCave;
import javajesus.quest.Quest;

/*
 * Quest to clear the alpha cave
 */
public class InvestigateChurch extends Quest {

	// first and only objective
	private static final int CAVECLEAR = 0;

	/**
	 * @param giver - npc who gives the quest
	 */
	public InvestigateChurch(NPC giver) {
		super(giver, "/WORLD_DATA/QUEST_DATA/Liberate_Church.json", 1);
	}

	@Override
	protected void update() {

		// if a demon is not found, assume true
		boolean killed = true;

		// search for any demons
		for (Mob m : AlphaCave.interior.getMobs()) {
			if ((m instanceof Demon || m instanceof Skeleton) && !m.isDead()) {
				killed = false;
				break;
			}
		}
		objectives[CAVECLEAR] = killed;
	}

}