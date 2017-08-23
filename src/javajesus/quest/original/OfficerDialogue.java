package javajesus.quest.original;

import javajesus.entities.npcs.NPC;
import javajesus.quest.Quest;

public class OfficerDialogue extends Quest {

	public OfficerDialogue(NPC giver) {
		super(giver, "/WORLD_DATA/QUEST_DATA/Officer_Quest.json", 1);
	}

	@Override
	protected void update() {
		
	}

}
