package javajesus.quest.original;

import javajesus.entities.npcs.NPC;
import javajesus.quest.Quest;

public class LiberateChurch extends Quest {

	public LiberateChurch(NPC giver) {
		super(giver, "/WORLD_DATA/QUEST_DATA/Liberate_Church.json", 1);
	}

	@Override
	public String getEndDialogue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish() {
		
	}

}
