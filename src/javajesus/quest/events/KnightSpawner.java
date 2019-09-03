package javajesus.quest.events;

import javajesus.entities.npcs.aggressive.Knight;
import javajesus.level.CharacterFactoryFactory;
import javajesus.level.Level;
import javajesus.level.LevelFactory;
import javajesus.quest.factories.AlphaCharacterFactory;

public class KnightSpawner extends Event{

	public KnightSpawner(Level level) {
		super(level);
	}

	@Override
	protected void init(Level level) {
		
		Level target = LevelFactory.get(LevelFactory.ALPHA);
		Knight runner = (Knight) CharacterFactoryFactory.make(target.getLevelId()).make(AlphaCharacterFactory.KNIGHT);
		//runner.addQuest(new RescueKnights());
		target.add(runner);
		
	}

}
