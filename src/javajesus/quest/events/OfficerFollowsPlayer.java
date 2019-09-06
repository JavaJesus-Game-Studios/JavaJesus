package javajesus.quest.events;

import javajesus.entities.npcs.aggressive.SWATOfficer;
import javajesus.level.CharacterFactoryFactory;
import javajesus.level.Level;
import javajesus.quest.factories.AlphaCharacterFactory;
import javajesus.quest.factories.CharacterFactory;

public class OfficerFollowsPlayer extends Event{
	
	public OfficerFollowsPlayer(Level level) {
		super(level);
	}
	
	/**
	 * Event logic
	 */
	@Override
	protected void init(Level level) {
		
		CharacterFactory cf = CharacterFactoryFactory.make(level.getLevelId());
		
		SWATOfficer officer = (SWATOfficer) cf.make(AlphaCharacterFactory.OFFICER);
		officer.setFollowingPlayer(true);
	}

}
