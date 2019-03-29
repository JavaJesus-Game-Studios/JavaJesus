package javajesus.quest.events;

import javajesus.entities.npcs.aggressive.Knight;
import javajesus.level.Level;
import javajesus.level.sandbox.SandboxOriginalLevel;
import javajesus.quest.original.RescueKnights;

public class KnightSpawner extends Event{

	public KnightSpawner(Level level) {
		super(level);
	}

	@Override
	protected void init(Level level) {
		Knight runner = new Knight(SandboxOriginalLevel.alpha, 1208, 1464);
		runner.addQuest(new RescueKnights());
		SandboxOriginalLevel.alpha.add(runner);
		
	}

}
