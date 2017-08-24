package javajesus.quest.events;

import javajesus.entities.npcs.aggressive.Knight;
import javajesus.level.Level;
import javajesus.quest.original.RescueKnights;

public class KnightSpawner extends Event{

	public KnightSpawner(Level level) {
		super(level);
	}

	@Override
	protected void init(Level level) {
		Knight runner = new Knight(level, 0, 0);
		runner.addQuest(new RescueKnights(runner));
		
	}

}
