package javajesus.quest.events;

import javajesus.entities.monsters.Demon;
import javajesus.level.Level;

public class VillageIncreasedDifficulty extends Event{
	
	/**
	 * 
	 * @param level level where even takes place
	 */
	public VillageIncreasedDifficulty(Level level) {
		super(level);
	}

	@Override
	protected void init(Level level) {
		level.add(new Demon(level, 1400, 520));
		level.add(new Demon(level, 1480, 424));
		level.add(new Demon(level, 1368, 376));
		level.add(new Demon(level, 1176, 512));
		level.add(new Demon(level, 1256, 648));
		level.add(new Demon(level, 1408, 680));
		level.add(new Demon(level, 1496, 696));

		
	}
	
}
