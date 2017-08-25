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
		for (Mob m: level.getMobs()) {
			if (m instanceof Orangutan) {
				//replace friendly Orangutan with the Evil Orangutan
				level.remove(m);
				level.add(new EvilOrangutan(level, 944, 192));
				break;
			}
		}
	}

}
