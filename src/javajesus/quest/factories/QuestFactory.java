package javajesus.quest.factories;

import javajesus.quest.Quest;

public abstract class QuestFactory {
	
	protected Quest[] quests;
	
	public QuestFactory() {
		init();
	}
	
	public Quest[] getQuests() {
		return quests;
	}
	
	protected abstract void init() ;
	
	/**
	 * @param id    - the id of the quest to create
	 * @param giver - the NPC who gives the quest
	 */
	public Quest makeQuest(int id) {
		return quests[id];
	}

}
