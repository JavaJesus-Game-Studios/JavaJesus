package javajesus.quest.original;

import javajesus.entities.Mob;
import javajesus.entities.monsters.Demon;
import javajesus.entities.monsters.Skeleton;
import javajesus.level.LevelFactory;
import javajesus.level.interior.Interior;
import javajesus.quest.Quest;

/*
 * Quest to clear the alpha cave
 */
public class InvestigateChurch extends Quest {

	// first and only objective
	private static final int CAVECLEAR = 0;
	
	private Interior interior;

	/**
	 * @param giver - npc who gives the quest
	 */
	public InvestigateChurch() {
		super("Investigate Church", "/WORLD_DATA/QUEST_DATA/Liberate_Church.json", 1);
		
		interior = (Interior) LevelFactory.get(LevelFactory.ALPHA_CAVE_INTERIOR);
	}

	@Override
	public void update() {

		// if a demon is not found, assume true
		boolean killed = true;

		// search for any demons
		for (Mob m : interior.getMobs()) {
			if ((m instanceof Demon || m instanceof Skeleton) && !m.isDead()) {
				killed = false;
				break;
			}
		}
		objectives[CAVECLEAR] = killed;
	}

}