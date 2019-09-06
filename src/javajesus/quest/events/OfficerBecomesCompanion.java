package javajesus.quest.events;

import javajesus.entities.npcs.aggressive.SWATOfficer;
import javajesus.level.CharacterFactoryFactory;
import javajesus.level.Level;
import javajesus.quest.FollowPlayerScript;
import javajesus.quest.factories.AlphaCharacterFactory;
import javajesus.quest.factories.CharacterFactory;

public class OfficerBecomesCompanion extends Event{
	
	public OfficerBecomesCompanion(Level level) {
		super(level);
	}
	
	/**
	 * Event logic
	 */
	@Override
	protected void init(Level level) {
		
		CharacterFactory cf = CharacterFactoryFactory.make(level.getLevelId());
		
		SWATOfficer officer = (SWATOfficer) cf.make(AlphaCharacterFactory.OFFICER);
		
		officer.setPath(new FollowPlayerScript(officer));
	}

}
