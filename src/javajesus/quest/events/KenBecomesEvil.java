package javajesus.quest.events;

import javajesus.entities.Mob;
import javajesus.entities.monsters.EvilOrangutan;
import javajesus.entities.npcs.aggressive.Orangutan;
import javajesus.level.Level;

public class KenBecomesEvil extends Event{

	public KenBecomesEvil(Level level) {
		super(level);
	}
	
	/**
	 * Event logic
	 */
	@Override
	protected void init(Level level) {
		
		// search for the Orangutan
		for (int i = 0; i < level.getMobs().size(); i++) {
			
			// mob at index i
			Mob m = level.getMobs().get(i);
			
			// check if it is an orangutan
			if (m instanceof Orangutan) {
				
				// remove the healthbar
				m.remove();
				
				// now remove it
				level.remove(m);
				
				//replace friendly Orangutan with the Evil Orangutan
				level.add(new EvilOrangutan(level, m.getX(), m.getY()));

				break;
			}
		}
	}

}
