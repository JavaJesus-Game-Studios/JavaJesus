package javajesus.quest.events;

import javajesus.entities.npcs.aggressive.SWATOfficer;
import javajesus.level.CharacterFactoryFactory;
import javajesus.level.Level;
import javajesus.quest.factories.AlphaCharacterFactory;
import javajesus.quest.factories.CharacterFactory;

/*
 * Spawns two swat officers, one of which has a quest for the player
 */
public class SwatOfficerEvent extends Event {
	
	/**
	 * @param level - level to do the event
	 */
	public SwatOfficerEvent(Level level) {
		super(level);
	}

	/**
	 * Event logic
	 */
	protected void init(Level level) {
		
		// TODO put in correct coordinates and quest
		SWATOfficer officer2 = new  SWATOfficer(level, 1488, 16);
		
		CharacterFactory cf = CharacterFactoryFactory.make(level.getLevelId());
		
		SWATOfficer officer = (SWATOfficer) cf.make(AlphaCharacterFactory.OFFICER);
		
		// TODO put correct coordinates
		level.add(officer);
		level.add(officer2);

	}

}
