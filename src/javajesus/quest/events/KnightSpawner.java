package javajesus.quest.events;

import javajesus.entities.npcs.aggressive.Knight;
import javajesus.level.CharacterFactoryFactory;
import javajesus.level.Level;
import javajesus.quest.factories.AlphaCharacterFactory;

public class KnightSpawner extends Event{

	public KnightSpawner(Level level) {
		super(level);
	}

	@Override
	protected void init(Level level) {
		Knight runner = (Knight) CharacterFactoryFactory.make(level.getName()).make(AlphaCharacterFactory.KNIGHT);
		//runner.addQuest(new RescueKnights());
		level.add(runner);
		
	}

}
