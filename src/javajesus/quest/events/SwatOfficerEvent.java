package javajesus.quest.events;

import javajesus.entities.npcs.aggressive.SWATOfficer;
import javajesus.level.Level;
import javajesus.quest.FollowPlayerScript;
import javajesus.quest.original.OfficerDialogue;

/*
 * Spawns two swat officers, one of which has a quest for the player
 */
public class SwatOfficerEvent extends Event {
	
	/**
	 * @param level - level to do the event
	 */
	protected SwatOfficerEvent(Level level) {
		super(level);
	}

	/**
	 * Event logic
	 */
	protected void init(Level level) {
		
		// TODO put in correct coordinates and quest
		SWATOfficer officer = new  SWATOfficer(level, 1464, 16);
		officer.addQuest(new OfficerDialogue(officer));
		officer.setPath(new FollowPlayerScript(officer));
		
		// TODO put correct coordinates
		level.add(officer);
		level.add(new SWATOfficer(level, 1488, 16));
		
	}

}
