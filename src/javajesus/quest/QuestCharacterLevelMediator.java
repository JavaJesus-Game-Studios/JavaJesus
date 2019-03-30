package javajesus.quest;

import javajesus.entities.npcs.NPC;
import javajesus.level.Level;
import javajesus.quest.factories.CharacterFactory;
import javajesus.quest.factories.QuestFactory;

public class QuestCharacterLevelMediator {
	
	private QuestFactory questFactory;
	private CharacterFactory charFactory;
	private Level level;
	
	public QuestCharacterLevelMediator(QuestFactory qf, CharacterFactory cf, Level level) {
		this.questFactory = qf;
		this.charFactory = cf;
		this.level = level;
	}
	
	public void initializeQuests() {
		Quest[] quests = questFactory.getQuests();
		System.out.println("Initializing Quests");
		for (Quest q: quests) {
			if (q != null) {
				NPC character = charFactory.make(q.giverId);
				q.setQuestFactory(questFactory);
				if (character != null) {
					q.giver = character;
					if (q.initialQuest) {
						character.addQuest(q);
					}
				}
			}
		}
	}

}
