package javajesus.quest;

import java.util.ArrayList;

import javajesus.entities.npcs.NPC;

public class QuestLoader {
	
	public static boolean loaded = false;
	
	public static ArrayList<Quest> quests;
	
	public static void init() {
		loaded = true;
		
		//load all 'initial' quests here
	}
	
	public static void link() {
		if (!loaded) {
			init();
		}
		
		for (Quest q: quests) {
			if (q.initial) {
				q.giver.addQuest(q);
			}
		}
		
	}

}
