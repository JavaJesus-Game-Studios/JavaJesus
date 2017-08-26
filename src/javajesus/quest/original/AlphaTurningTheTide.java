package javajesus.quest.original;

import java.util.Iterator;
import java.util.List;

import javajesus.entities.Mob;
import javajesus.entities.monsters.Demon;
import javajesus.entities.monsters.EvilOrangutan;
import javajesus.entities.npcs.NPC;
import javajesus.entities.npcs.characters.Jesus;
import javajesus.level.sandbox.SandboxOriginalLevel;
import javajesus.quest.Quest;

public class AlphaTurningTheTide extends Quest{
	
	//The two lists of mobs to iterate through
	List<Mob> list1=giver.getLevel().getMobs();
	List<Mob> list2=SandboxOriginalLevel.alpha.getMobs();
	
	//Mob instance
	Mob m;
	
	//Iterates that will iterate through the lists
	Iterator<Mob> it1 = list1.iterator();
	Iterator<Mob> it2 = list2.iterator();
	
	private static final int ISDEAD = 0;


	public AlphaTurningTheTide(NPC giver){
		super(giver, "/WORLD_DATA/QUEST_DATA/ALPHA_TURNING_THE_TIDE.json", 1);
	}

	@Override
	public void update() {
		// if a demon is not found, assume true
				boolean killed = true;
				
				//If either a Jesus or an EvilOrangutan instance is killed, the quest updates
				while(it1.hasNext() && it2.hasNext()) {
					if (m instanceof Jesus && !m.isDead() || m instanceof EvilOrangutan && !m.isDead()) {
						killed = false;
						break;
					}
				}
			
				// update the objective
				objectives[ISDEAD] = killed;
	}
	
}
