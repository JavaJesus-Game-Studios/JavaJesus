package javajesus.quest.factories;

import javajesus.quest.Quest;
import javajesus.quest.original.AlphaMissingCow;
import javajesus.quest.original.AlphaTurningTheTide;
import javajesus.quest.original.InvestigateChurch;
import javajesus.quest.original.LiberateFarm;
import javajesus.quest.original.LiberateVillage;
import javajesus.quest.original.RescueKnights;
import javajesus.quest.original.TheEvilFox;

public class AlphaQuestFactory extends QuestFactory{

	// IDS used for getting quests
	private static final int EVIL_FOX = 0, LIBERATE_VILLAGE = 1, LIBERATE_FARM = 2, RESCUE_KNIGHTS = 3,
			LIBERATE_CHURCH = 4, MISSING_COW = 5, TURNTIDE = 6;

	protected void init() {
		// change this as number of quests increases
		quests = new Quest[10];

		quests[EVIL_FOX] = new TheEvilFox();
		/*quests[LIBERATE_VILLAGE] = new LiberateVillage();
		quests[LIBERATE_FARM] = new LiberateFarm();
		quests[RESCUE_KNIGHTS] = new RescueKnights();
		quests[LIBERATE_CHURCH] = new InvestigateChurch();
		quests[MISSING_COW] = new AlphaMissingCow();
		quests[TURNTIDE] = new AlphaTurningTheTide();*/
		quests[LIBERATE_VILLAGE] = new TheEvilFox();
		quests[LIBERATE_FARM] = new TheEvilFox();
		quests[RESCUE_KNIGHTS] = new TheEvilFox();
		quests[LIBERATE_CHURCH] = new TheEvilFox();
		quests[MISSING_COW] = new TheEvilFox();
		quests[TURNTIDE] = new TheEvilFox();
	}

}
