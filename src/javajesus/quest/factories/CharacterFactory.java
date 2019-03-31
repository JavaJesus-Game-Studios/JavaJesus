package javajesus.quest.factories;

import javajesus.entities.Entity;
import javajesus.entities.npcs.Gaucho;
import javajesus.entities.npcs.NPC;
import javajesus.entities.npcs.Panchombre;
import javajesus.entities.npcs.aggressive.Companion;
import javajesus.entities.npcs.characters.Bautista;
import javajesus.entities.npcs.characters.Daughter;
import javajesus.entities.npcs.characters.Jesus;
import javajesus.entities.npcs.characters.Jobs;
import javajesus.entities.npcs.characters.Kobe;
import javajesus.entities.npcs.characters.LordHillsborough;
import javajesus.entities.npcs.characters.Octavius;
import javajesus.entities.npcs.characters.Ranchero;
import javajesus.entities.npcs.characters.Son;
import javajesus.entities.npcs.characters.Wife;
import javajesus.entities.npcs.characters.Zorra;
import javajesus.level.Level;

public abstract class CharacterFactory {
	
	protected static final int UNIQUE_OFFSET = 256;

	protected static NPC[] npcs = new NPC[256];
	
	public abstract void setNonUniqueCharacters(Level level);

	public final boolean created(int id) {
		NPC npc = make(id);
		return npc != null;
	}

	public boolean set(Level level, int id, short x, short y) {
		switch (id) {
		case Entity.BAUTISTA:
			npcs[Entity.BAUTISTA] = new Bautista(level, x, y);
			return true;
		case Entity.DAUGHTER:
			npcs[Entity.DAUGHTER] = new Daughter(level, x, y);
			return true;
		case Entity.JESUS:
			npcs[Entity.JESUS] = new Jesus(level, x, y);
			return true;
		case Entity.JOBS:
			npcs[Entity.JOBS] = new Jobs(level, x, y);
			return true;
		case Entity.KOBE:
			npcs[Entity.KOBE] = new Kobe(level, x, y);
			return true;
		case Entity.LORD_HILLSBOROUGH:
			npcs[Entity.LORD_HILLSBOROUGH] = new LordHillsborough(level, x, y);
			return true;
		case Entity.OCTAVIUS:
			npcs[Entity.OCTAVIUS] = new Octavius(level, x, y);
			return true;
		case Entity.RANCHERO:
			npcs[Entity.RANCHERO] = new Ranchero(level, x, y);
			return true;
		case Entity.SON:
			npcs[Entity.SON] = new Son(level, x, y);
			return true;
		case Entity.WIFE:
			npcs[Entity.WIFE] = new Wife(level, x, y);
			return true;
		case Entity.ZORRA:
			npcs[Entity.ZORRA] = new Zorra(level, x, y);
			return true;
		case Entity.COMPANION:
			npcs[Entity.COMPANION] = new Companion(level, x, y, null);
			return true;
		case Entity.GAUCHO:
			npcs[Entity.GAUCHO] = new Gaucho(level, x, y);
			return true;
		case Entity.PANCHOMBRE:
			npcs[Entity.PANCHOMBRE] = new Panchombre(level, x, y);
			return true;

		}
		return false;
	}

	public NPC make(int id) {
		if (id >= 0) {
			return npcs[id];
		} else {
			return null;
		}
	}

}
