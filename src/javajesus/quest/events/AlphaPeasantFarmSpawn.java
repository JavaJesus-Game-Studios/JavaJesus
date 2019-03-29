package javajesus.quest.events;

import javajesus.entities.npcs.Peasant;
import javajesus.level.Level;
import javajesus.quest.original.AlphaMissingCow;
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
		Peasant hiddenPeasant = new Peasant(level, 512, 296, 0);
		hiddenPeasant.addQuest(new AlphaMissingCow());
	}

}
