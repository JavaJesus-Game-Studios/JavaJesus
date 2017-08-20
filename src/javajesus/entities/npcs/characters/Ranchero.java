package javajesus.entities.npcs.characters;

import javajesus.entities.Entity;
import javajesus.entities.npcs.NPC;
import javajesus.level.Level;
import javajesus.level.sandbox.SandboxOriginalLevel;
import javajesus.quest.original.LiberateVillage;

public class Ranchero extends NPC {

	public Ranchero(Level level, int x, int y) {
		super(level, "Ranchero", x, y, 1, 16, 16, 100, new int[] { 0xFF111111,
				0xFF000046, 0xFFEDC5AB }, 0, 34, "", 0);
		
		// sandbox unique quests
		if (level instanceof SandboxOriginalLevel) {
			
		//  give ranchero the liberate village quest
			addQuest(new LiberateVillage(this));
		}
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Entity.RANCHERO;
	}

}
