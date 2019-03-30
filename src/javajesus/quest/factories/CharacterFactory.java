package javajesus.quest.factories;

import javajesus.entities.Entity;
import javajesus.entities.npcs.Gaucho;
import javajesus.entities.npcs.NPC;
import javajesus.entities.npcs.Panchombre;
import javajesus.entities.npcs.Peasant;
import javajesus.entities.npcs.aggressive.Companion;
import javajesus.entities.npcs.aggressive.Knight;
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

public class CharacterFactory {

	private NPC[] npcs;

	public CharacterFactory() {
		this.npcs = new NPC[256];
	}

	public NPC setAndMake(Level level, int id, short x, short y) {
		switch (id) {
		case Entity.BAUTISTA:
			return npcs[Entity.BAUTISTA] = new Bautista(level, x, y);
		case Entity.DAUGHTER:
			return npcs[Entity.DAUGHTER] = new Daughter(level, x, y);
		case Entity.JESUS:
			return npcs[Entity.JESUS] = new Jesus(level, x, y);
		case Entity.JOBS:
			return npcs[Entity.JOBS] = new Jobs(level, x, y);
		case Entity.KNIGHT:
			return npcs[Entity.KNIGHT] = new Knight(level, x, y);
		case Entity.KOBE:
			return npcs[Entity.KOBE] = new Kobe(level, x, y);
		case Entity.LORD_HILLSBOROUGH:
			return npcs[Entity.LORD_HILLSBOROUGH] = new LordHillsborough(level, x, y);
		case Entity.OCTAVIUS:
			return npcs[Entity.OCTAVIUS] = new Octavius(level, x, y);
		case Entity.PEASANT:
			return npcs[Entity.PEASANT] = new Peasant(level, x, y, Peasant.MALE);
		case Entity.RANCHERO:
			return npcs[Entity.RANCHERO] = new Ranchero(level, x, y);
		case Entity.SON:
			return npcs[Entity.SON] = new Son(level, x, y);
		case Entity.WIFE:
			return npcs[Entity.WIFE] = new Wife(level, x, y);
		case Entity.ZORRA:
			return npcs[Entity.ZORRA] = new Zorra(level, x, y);
		case Entity.COMPANION:
			return npcs[Entity.COMPANION] = new Companion(level, x, y, null);
		case Entity.GAUCHO:
			return npcs[Entity.GAUCHO] = new Gaucho(level, x, y);
		case Entity.PANCHOMBRE:
			return npcs[Entity.PANCHOMBRE] = new Panchombre(level, x, y);
		
		}
		return null;
	}

	public NPC make(int id) {
		switch (id) {
		case Entity.BAUTISTA:
			return npcs[Entity.BAUTISTA];
		case Entity.DAUGHTER:
			return npcs[Entity.DAUGHTER];
		case Entity.JESUS:
			return npcs[Entity.JESUS];
		case Entity.JOBS:
			return npcs[Entity.JOBS];
		case Entity.KNIGHT:
			return npcs[Entity.KNIGHT];
		case Entity.KOBE:
			return npcs[Entity.KOBE];
		case Entity.LORD_HILLSBOROUGH:
			return npcs[Entity.LORD_HILLSBOROUGH];
		case Entity.OCTAVIUS:
			return npcs[Entity.OCTAVIUS];
		case Entity.PEASANT:
			return npcs[Entity.PEASANT];
		case Entity.RANCHERO:
			return npcs[Entity.RANCHERO];
		case Entity.SON:
			return npcs[Entity.SON];
		case Entity.WIFE:
			return npcs[Entity.WIFE];
		case Entity.ZORRA:
			return npcs[Entity.ZORRA];
		case Entity.COMPANION:
			return npcs[Entity.COMPANION];
		case Entity.GAUCHO:
			return npcs[Entity.GAUCHO];
		case Entity.PANCHOMBRE:
			return npcs[Entity.PANCHOMBRE];
		
		}
		return null;
	}

}
