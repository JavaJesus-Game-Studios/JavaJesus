package javajesus.quest;

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

}
