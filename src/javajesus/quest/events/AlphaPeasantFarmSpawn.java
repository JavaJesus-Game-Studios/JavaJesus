package javajesus.quest.events;

import javajesus.entities.npcs.Peasant;
import javajesus.level.Level;
import javajesus.quest.original.LiberateFarm;
/**
 * 
 * @author shtevay
 *
 */
public class AlphaPeasantFarmSpawn extends Event{

	protected AlphaPeasantFarmSpawn(Level level) {
		super(level);
	}

	@Override
	protected void init(Level level) {
		Peasant scaredPeasant = new Peasant(level, 832, 112, 1);
		scaredPeasant.addQuest(new LiberateFarm(scaredPeasant));
	}

}
