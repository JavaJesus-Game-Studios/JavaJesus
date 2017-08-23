package javajesus.quest.original;

import javajesus.entities.npcs.NPC;
import javajesus.quest.Quest;

public class RescueKnights extends Quest {

	public RescueKnights(NPC giver) {
		super(giver, "/WORLD_DATA/QUEST_DATA/Rescue_Knights.json", 1);
	}

	@Override
	protected void update() {
		
	}

}
