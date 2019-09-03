package javajesus.quest.factories;

import javajesus.quest.Quest;
import javajesus.quest.original.AlphaMissingCow;
import javajesus.quest.original.AlphaTurningTheTide;
import javajesus.quest.original.InvestigateChurch;
import javajesus.quest.original.LiberateFarm;
import javajesus.quest.original.LiberateVillage;
import javajesus.quest.original.OfficerDialogue;
import javajesus.quest.original.RescueKnights;
import javajesus.quest.original.TheEvilFox;

public final class QuestFactory {

	// IDS used for getting quests
	private static final int EVIL_FOX = 0, LIBERATE_VILLAGE = 1, LIBERATE_FARM = 2, RESCUE_KNIGHTS = 3,
			LIBERATE_CHURCH = 4, MISSING_COW = 5, TURNTIDE = 6, OFFICER_DIALOGUE = 7;

	private static Quest[] quests;
	private static boolean initialized;

	public static Quest[] getQuests() {

		if (!initialized) {
			init();
		}

		return quests;
	}

	// register quests here or they won't appear in game
	private static void init() {
		// change this as number of quests increases
		quests = new Quest[10];

		quests[EVIL_FOX] = new TheEvilFox();
		quests[LIBERATE_VILLAGE] = new LiberateVillage();
		quests[LIBERATE_FARM] = new LiberateFarm();
		quests[RESCUE_KNIGHTS] = new RescueKnights();
		quests[LIBERATE_CHURCH] = new InvestigateChurch();
		quests[MISSING_COW] = new AlphaMissingCow();
		quests[TURNTIDE] = new AlphaTurningTheTide();
		quests[OFFICER_DIALOGUE] = new OfficerDialogue();

		initialized = true;
	}

	/**
	 * @param id    - the id of the quest to create
	 * @param giver - the NPC who gives the quest
	 */
	public static Quest makeQuest(int id) {

		if (!initialized) {
			init();
		}

		return quests[id];
	}

}
