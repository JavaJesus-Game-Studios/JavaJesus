package javajesus.quest;

import javajesus.entities.npcs.NPC;
import javajesus.level.CharacterFactoryFactory;
import javajesus.level.Level;
import javajesus.quest.factories.QuestFactory;

public class QuestLoader {

	public static void initializeQuests(Level level) {
		
		Quest[] quests = QuestFactory.getQuests();
		System.out.println("Initializing Quests");

		for (Quest q : quests) {
			if (q != null) {
				int id = q.getLevelId();
				if (id == level.getLevelId()) {
					NPC character = CharacterFactoryFactory.make(id).make(q.giverId);
					if (character != null) {
						q.load(character);
					}
				}
			}
		}
	}

}
