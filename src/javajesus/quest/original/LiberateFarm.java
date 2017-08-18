package javajesus.quest.original;

import javajesus.entities.npcs.NPC;
import javajesus.quest.Quest;

public class LiberateFarm extends Quest {

	public LiberateFarm(NPC giver) {
		super(giver, "/WORLD_DATA/QUEST_DATA/Liberate_Farm.json", 1);
	}

	@Override
	public String getEndDialogue() {
		return null;
	}

	@Override
	protected void update() {
		
	}

	@Override
	public void onFinish() {
		
	}

}
