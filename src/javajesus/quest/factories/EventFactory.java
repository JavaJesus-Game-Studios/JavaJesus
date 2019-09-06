package javajesus.quest.factories;

import javajesus.level.Level;
import javajesus.quest.events.AlphaPeasantFarmSpawn;
import javajesus.quest.events.Event;
import javajesus.quest.events.KenBecomesEvil;
import javajesus.quest.events.KnightSpawner;
import javajesus.quest.events.OfficerFollowsPlayer;
import javajesus.quest.events.SwatOfficerEvent;
import javajesus.quest.events.VillageIncreasedDifficulty;

public class EventFactory {

	// constants for each event
	public static final int SWAT_EVENT = 0;
	public static final int VILLAGEINCREASEDDIFFICULTY = 1;
	public static final int ALPHAPEASANTFARMSPAWNER = 2;
	public static final int KNIGHTSPAWNER = 3;
	public static final int KENBECOMESEVIL = 4;
	public static final int OFFICER_FOLLOWS_PLAYER = 5;
	
	/**
	 * @param id    - trigger id for the event
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
		case OFFICER_FOLLOWS_PLAYER:
			return new OfficerFollowsPlayer(level);
		default:
			return null;
		}

	}

}
