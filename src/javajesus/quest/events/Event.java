package javajesus.quest.events;

import javajesus.level.Level;

/*
 * An Event consists of a list of specific actions that constitute a situation in the game
 */
public abstract class Event {

	// constants for each event
	public static final int SWAT_EVENT = 0;
	public static final int VILLAGEINCREASEDDIFFICULTY = 1;
	public static final int ALPHAPEASANTFARMSPAWNER = 2;
	public static final int KNIGHTSPAWNER = 3;
	public static final int KENBECOMESEVIL = 4;
	
	/**
	 * @param level - level where the actions are committed
	 */
	protected Event(Level level) {
		init(level);
	}
	
	/**
	 * @param level - level where actions are committed
	 */
	protected abstract void init(Level level);

	/**
	 * @param id - trigger id for the event
	 * @param level - level it occurs on
	 * @return the event instance
	 */
	public static Event createEvent(int id, Level level) {

		// get the event based on the trigger ID
		switch (id) {
		case SWAT_EVENT:
			return new SwatOfficerEvent(level);
		case VILLAGEINCREASEDDIFFICULTY:
			return new VillageIncreasedDifficulty(level);
		case ALPHAPEASANTFARMSPAWNER:
			return new AlphaPeasantFarmSpawn(level);
		case KNIGHTSPAWNER:
			return new KnightSpawner(level);
		case KENBECOMESEVIL:
			return new KenBecomesEvil(level);
		default:
			return null;
		}

	}

}
